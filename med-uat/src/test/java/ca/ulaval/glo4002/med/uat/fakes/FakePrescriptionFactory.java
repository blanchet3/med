package ca.ulaval.glo4002.med.uat.fakes;

import java.util.Date;

import ca.ulaval.glo4002.med.core.prescriptions.Prescription;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionFactory;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionIdentifier;

public class FakePrescriptionFactory implements PrescriptionFactory {

    public PrescriptionIdentifier lastCreatedIdentifier;

    @Override
    public Prescription create(String din, Date date, String physician, int renewals) {
        PrescriptionIdentifier identifier = PrescriptionIdentifier.create();
        lastCreatedIdentifier = identifier;
        return new Prescription(identifier, din, date, physician, renewals);
    }

}
