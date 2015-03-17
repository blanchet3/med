package ca.ulaval.glo4002.med.uat.steps.fixtures;

import java.util.Date;

import ca.ulaval.glo4002.med.applicationServices.prescriptions.PrescriptionForm;

public class PrescriptionFormBuilder {

    private PrescriptionForm form;

    public PrescriptionFormBuilder() {
        this.form = new PrescriptionForm();
    }

    public PrescriptionFormBuilder withDin(String din) {
        form.din = din;
        return this;
    }

    public PrescriptionFormBuilder withPhysician(String physician) {
        form.physician = physician;
        return this;
    }

    public PrescriptionFormBuilder withRenewals(int renewals) {
        form.renewals = renewals;
        return this;
    }

    public PrescriptionFormBuilder withDate(Date date) {
        form.date = date;
        return this;
    }

    public PrescriptionForm build() {
        return form;
    }

    public static PrescriptionFormBuilder createValidForm() {
        return new PrescriptionFormBuilder().
                withDin("din2335").
                withPhysician("md2834").
                withDate(new Date()).
                withRenewals(2);
    }

}
