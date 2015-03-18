package ca.ulaval.glo4002.med.interfaces.rest.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.med.applicationServices.prescriptions.InvalidPrescriptionFormException;

@Provider
public class InvalidPrescriptionErrorMapper implements ExceptionMapper<InvalidPrescriptionFormException> {

    @Override
    public Response toResponse(InvalidPrescriptionFormException exception) {
        return Response.status(Status.BAD_REQUEST).entity(new RestError(exception.getCode(), exception.getMessage())).build();
    }

}
