package ca.ulaval.glo4002.med.core.prescriptions;

import java.util.Date;

public class Prescription {

    private PrescriptionIdentifier identifier;
    private String din;
    private Date date;
    private String physician;
    private int renewals;

    public Prescription(PrescriptionIdentifier identifier, String din, Date date, String physician, int renewals) {
        this.identifier = identifier;
        this.din = din;
        this.date = date;
        this.physician = physician;
        this.renewals = renewals;
    }

    public PrescriptionIdentifier getIdentifier() {
        return identifier;
    }

    public String getDin() {
        return din;
    }

    public Date getDate() {
        return date;
    }

    public String getPhysician() {
        return physician;
    }

    public int getRenewals() {
        return renewals;
    }

}
