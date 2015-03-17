package ca.ulaval.glo4002.med.applicationServices;

public abstract class ApplicationException extends RuntimeException {

    private String code;

    public ApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private static final long serialVersionUID = 1L;
}
