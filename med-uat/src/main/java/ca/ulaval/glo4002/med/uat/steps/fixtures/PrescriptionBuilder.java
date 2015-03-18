package ca.ulaval.glo4002.med.uat.steps.fixtures;

import java.util.Date;

import ca.ulaval.glo4002.med.applicationServices.prescriptions.PrescriptionForm;
import ca.ulaval.glo4002.med.applicationServices.shared.locator.ServiceLocator;
import ca.ulaval.glo4002.med.core.prescriptions.Prescription;
import ca.ulaval.glo4002.med.core.prescriptions.PrescriptionFactory;

public class PrescriptionBuilder {

    private PrescriptionForm form;

    public PrescriptionBuilder() {
        this.form = new PrescriptionForm();
    }

    public PrescriptionBuilder withDin(String din) {
        form.din = din;
        return this;
    }

    public PrescriptionBuilder withPhysician(String physician) {
        form.physician = physician;
        return this;
    }

    public PrescriptionBuilder withRenewals(int renewals) {
        form.renewals = renewals;
        return this;
    }

    public PrescriptionBuilder withDate(Date date) {
        form.date = date;
        return this;
    }

    public PrescriptionForm buildForm() {
        return form;
    }

    public Prescription buildPrescription() {
        PrescriptionFactory factory = ServiceLocator.getInstance().resolve(PrescriptionFactory.class);
        return factory.create(form.din, form.date, form.physician, form.renewals);
    }

    public static PrescriptionBuilder createValid() {
        return createValid(2);
    }

    public static PrescriptionBuilder createValid(int initialRenewals) {
        return new PrescriptionBuilder().
                withDin("din2335").
                withPhysician("md2834").
                withDate(new Date()).
                withRenewals(initialRenewals);
    }

}
