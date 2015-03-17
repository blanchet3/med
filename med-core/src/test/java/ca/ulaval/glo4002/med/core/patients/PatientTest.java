package ca.ulaval.glo4002.med.core.patients;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.med.core.prescriptions.Prescription;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionIdentifier;

public class PatientTest {

    private static final PatientIdentifier IDENTIFIER = new PatientIdentifier("234");
    private Patient patient;

    @Before
    public void createPatient() {
        patient = new Patient(IDENTIFIER);
    }

    @Test
    public void canAddAPrescriptionToAPatient() {
        PrescriptionIdentifier prescriptionIdentifier = PrescriptionIdentifier.create();
        Prescription prescription = mock(Prescription.class);
        willReturn(prescriptionIdentifier).given(prescription).getIdentifier();

        patient.addPrescription(prescription);

        assertTrue(patient.hasPrescription(prescriptionIdentifier));
    }

}
