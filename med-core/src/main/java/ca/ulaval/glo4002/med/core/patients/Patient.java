package ca.ulaval.glo4002.med.core.patients;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.med.core.prescriptions.Prescription;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionIdentifier;

public class Patient {

    private final PatientIdentifier identifier;
    private final List<Prescription> prescriptions = new ArrayList<>();

    public Patient(PatientIdentifier identifier) {
        this.identifier = identifier;
    }

    public PatientIdentifier getIdentifier() {
        return identifier;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public boolean hasPrescription(PrescriptionIdentifier prescriptionIdentifier) {
        return prescriptions.stream().anyMatch(x -> x.getIdentifier().equals(prescriptionIdentifier));
    }

}
