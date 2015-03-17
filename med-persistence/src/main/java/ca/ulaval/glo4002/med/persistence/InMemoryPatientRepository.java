package ca.ulaval.glo4002.med.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.med.core.patients.Patient;
import ca.ulaval.glo4002.med.core.patients.PatientIdentifier;
import ca.ulaval.glo4002.med.core.patients.PatientRepository;

public class InMemoryPatientRepository implements PatientRepository {

    private List<Patient> patients = new ArrayList<>();

    @Override
    public void persist(Patient patient) {
        patients.add(patient);
    }

    @Override
    public Patient findByIdentifier(PatientIdentifier patientIdentifier) {
        return patients.stream().filter(x -> x.getIdentifier().equals(patientIdentifier)).findFirst().get();
    }

}
