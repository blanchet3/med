package ca.ulaval.glo4002.med.uat.context;

import ca.ulaval.glo4002.med.applicationServices.shared.locator.ServiceLocator;
import ca.ulaval.glo4002.med.context.ContextBase;
import ca.ulaval.glo4002.med.core.patients.PatientRepository;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionFactory;
import ca.ulaval.glo4002.med.uat.fakes.FakePrescriptionFactory;
import ca.ulaval.glo4002.med.uat.fakes.InMemoryPatientRepository;

public class UatContext extends ContextBase {

    public void reinitialize() {
        ServiceLocator.reset();
        apply();
    }

    @Override
    protected void registerServices() {
        ServiceLocator.getInstance().register(PatientRepository.class, new InMemoryPatientRepository());
        ServiceLocator.getInstance().register(PrescriptionFactory.class, new FakePrescriptionFactory());
    }

    @Override
    protected void applyFillers() {
    }

}
