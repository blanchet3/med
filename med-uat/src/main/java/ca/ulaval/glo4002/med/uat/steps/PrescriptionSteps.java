package ca.ulaval.glo4002.med.uat.steps;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.ulaval.glo4002.med.applicationServices.prescriptions.PrescriptionApplicationService;
import ca.ulaval.glo4002.med.applicationServices.prescriptions.PrescriptionForm;
import ca.ulaval.glo4002.med.applicationServices.shared.ServiceLocator;
import ca.ulaval.glo4002.med.core.patients.Patient;
import ca.ulaval.glo4002.med.core.patients.PatientIdentifier;
import ca.ulaval.glo4002.med.core.patients.PatientRepository;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionFactory;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionIdentifier;
import ca.ulaval.glo4002.med.uat.fakes.FakePrescriptionFactory;
import ca.ulaval.glo4002.med.uat.steps.PrescriptionSteps.PrescriptionStepsState;
import ca.ulaval.glo4002.med.uat.steps.fixtures.PrescriptionFormBuilder;
import ca.ulaval.glo4002.med.uat.steps.state.StatefulStep;
import ca.ulaval.glo4002.med.uat.steps.state.StepState;

import com.jayway.restassured.http.ContentType;

public class PrescriptionSteps extends StatefulStep<PrescriptionStepsState> {

    protected PrescriptionStepsState getInitialState() {
        return new PrescriptionStepsState();
    }

    @Given("an existing patient")
    public void givenAnExistingPatient() {
        state().patient = new Patient(new PatientIdentifier("1234"));
        PatientRepository patientRepository = getPatientRepository();
        patientRepository.persist(state().patient);
    }

    @Given("a prescription with missing information")
    public void givenAPrescriptionWithMissingInformation() {
        state().prescriptionForm = new PrescriptionFormBuilder().build();
    }

    @Given("a valid prescription form")
    public void givenAValidPrescriptionForm() {
        state().prescriptionForm = PrescriptionFormBuilder.createValidForm().build();
    }

    @When("I add this prescription to the patient's record")
    public void whenIAddThisPrescriptionToThePatientsRecord() {
        PrescriptionApplicationService service = new PrescriptionApplicationService();
        service.addPrescription(state().patient.getIdentifier(), state().prescriptionForm);
    }

    @When("I submit the form to add to the patient's record")
    public void whenISubmitTheFormToAddToThePatientsRecord() {
        state().request.contentType(ContentType.JSON).
                body(state().prescriptionForm).
                pathParam("patientIdentifier", state().patient.getIdentifier().number);

        state().response = state().request.when().post("/patients/{patientIdentifier}/prescriptions");
    }

    @Then("I receive an error")
    public void thenIReceiveAnError() {
        state().response.then().assertThat().statusCode(Status.BAD_REQUEST.getStatusCode());
    }

    @Then("the error code is \"PRES001\"")
    public void thenTheErrorCodeIsPRES001() {
        state().response.then().assertThat().body("code", equalTo("PRES001"));
    }

    @Then("I receive an answer saying it was created succesfully")
    public void thenIReceiveAnAnswerSayingItWasCreatedSuccesfully() {
        state().response.then().assertThat().statusCode(Status.CREATED.getStatusCode());
    }

    @Then("the prescription is stored")
    public void thenThePrescriptionIsStored() {
        Patient patient = getPatientRepository().findByIdentifier(state().patient.getIdentifier());
        PrescriptionIdentifier lastPrescriptionIdentifier = getPrescriptionFactory().lastCreatedIdentifier;

        assertTrue("The patient should have the prescription", patient.hasPrescription(lastPrescriptionIdentifier));
    }

    private PatientRepository getPatientRepository() {
        return ServiceLocator.getInstance().resolve(PatientRepository.class);
    }

    private FakePrescriptionFactory getPrescriptionFactory() {
        return (FakePrescriptionFactory) ServiceLocator.getInstance().resolve(PrescriptionFactory.class);
    }

    public class PrescriptionStepsState extends StepState {
        public Patient patient;
        public PrescriptionForm prescriptionForm;
    }

}
