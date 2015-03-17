package ca.ulaval.glo4002.med.interfaces.rest.resources;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.med.applicationServices.prescriptions.InvalidPrescriptionFormException;
import ca.ulaval.glo4002.med.applicationServices.prescriptions.PrescriptionApplicationService;
import ca.ulaval.glo4002.med.applicationServices.prescriptions.PrescriptionForm;
import ca.ulaval.glo4002.med.core.patients.PatientIdentifier;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionIdentifier;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionResourceTest {

    private static final InvalidPrescriptionFormException INVALID_PRESCRIPTION_FORM_EXCEPTION = new InvalidPrescriptionFormException();

    private static final PrescriptionIdentifier VALID_PRESCRIPTION_IDENTIFIER = PrescriptionIdentifier.create();

    private static final PatientIdentifier PATIENT_IDENTIFIER = new PatientIdentifier("1235");

    @Mock
    private PrescriptionApplicationService service;

    @InjectMocks
    private PrescriptionResource resource;

    @Test
    public void addingAPrescriptionShouldFowardToTheService() {
        PrescriptionForm form = givenAValidForm();

        resource.addPrescription(PATIENT_IDENTIFIER, form);

        verify(service).addPrescription(PATIENT_IDENTIFIER, form);
    }

    @Test
    public void addingAPrescriptionShouldReturnACreatedStatusCodeIfItWasSuccessful() {
        PrescriptionForm form = givenAValidForm();

        Response response = resource.addPrescription(PATIENT_IDENTIFIER, form);

        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void addingAPrescriptionShouldReturnThePrescriptionLocationIfItWasSuccessful() {
        PrescriptionForm form = givenAValidForm();

        Response response = resource.addPrescription(PATIENT_IDENTIFIER, form);

        assertEquals(
                URI.create("/patients/" + PATIENT_IDENTIFIER.describe() + "/prescriptions/" + VALID_PRESCRIPTION_IDENTIFIER.describe()),
                response.getHeaders().getFirst("Location"));
    }

    @Test(expected = InvalidPrescriptionFormException.class)
    public void addingAPrescriptionShouldThrowAnException() {
        PrescriptionForm form = givenAnInvalidForm();

        resource.addPrescription(PATIENT_IDENTIFIER, form);
    }

    private PrescriptionForm givenAValidForm() {
        PrescriptionForm form = new PrescriptionForm();
        willReturn(VALID_PRESCRIPTION_IDENTIFIER).given(service).addPrescription(any(PatientIdentifier.class),
                any(PrescriptionForm.class));
        return form;
    }

    private PrescriptionForm givenAnInvalidForm() {
        PrescriptionForm form = new PrescriptionForm();
        willThrow(INVALID_PRESCRIPTION_FORM_EXCEPTION).given(service).addPrescription(any(PatientIdentifier.class),
                any(PrescriptionForm.class));
        return form;
    }
}
