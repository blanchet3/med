package ca.ulaval.glo4002.med.interfaces.rest.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.med.applicationServices.prescriptions.InvalidPrescriptionFormException;

public class InvalidPrescriptionErrorMapperTest {

    private InvalidPrescriptionErrorMapper mapper;

    @Before
    public void createMapper() {
        mapper = new InvalidPrescriptionErrorMapper();
    }

    @Test
    public void createsRestErrorWithTheExceptionsCode() {
        InvalidPrescriptionFormException exception = new InvalidPrescriptionFormException();
        Response response = mapper.toResponse(exception);

        assertEquals(exception.getCode(), ((RestError) response.getEntity()).code);
    }

    @Test
    public void createsABadRequest() {
        Response response = mapper.toResponse(new InvalidPrescriptionFormException());

        assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
