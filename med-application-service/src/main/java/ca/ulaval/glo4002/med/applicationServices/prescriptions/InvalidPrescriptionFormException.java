package ca.ulaval.glo4002.med.applicationServices.prescriptions;

import ca.ulaval.glo4002.med.applicationServices.ApplicationException;

public class InvalidPrescriptionFormException extends ApplicationException {

    public InvalidPrescriptionFormException() {
        super("PRES001", "The prescription form was invalid");
    }

    private static final long serialVersionUID = 1L;
}
