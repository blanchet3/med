package ca.ulaval.glo4002.med.context;

import ca.ulaval.glo4002.med.applicationServices.shared.ServiceLocator;
import ca.ulaval.glo4002.med.core.patients.Patient;
import ca.ulaval.glo4002.med.core.patients.PatientIdentifier;
import ca.ulaval.glo4002.med.core.patients.PatientRepository;
import ca.ulaval.glo4002.med.core.prescriptions.GespharPrescriptionFactory;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionFactory;
import ca.ulaval.glo4002.med.persistence.InMemoryPatientRepository;

public class DemoContext extends ContextBase {

    @Override
    protected void registerServices() {
        ServiceLocator.getInstance().register(PatientRepository.class, new InMemoryPatientRepository());
        ServiceLocator.getInstance().register(PrescriptionFactory.class, new GespharPrescriptionFactory());
    }

    @Override
    protected void applyFillers() {
        PatientRepository repository = ServiceLocator.getInstance().resolve(PatientRepository.class);
        repository.persist(new Patient(new PatientIdentifier("1234")));
        repository.persist(new Patient(new PatientIdentifier("5678")));
        repository.persist(new Patient(new PatientIdentifier("a123")));
        repository.persist(new Patient(new PatientIdentifier("b987")));
    }

}
