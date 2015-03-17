package ca.ulaval.glo4002.med.interfaces.rest.resources;

public class RestError {

    public String code;
    public String message;

    public RestError(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
