package io.github.JorgeMor1.infra.mapper;

import io.github.JorgeMor1.dto.ErrorResponse;
import io.github.JorgeMor1.exception.ConflictException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {
    @Context
    UriInfo uriInfo;
    public Response toResponse(ConflictException exception){
        ErrorResponse error = new ErrorResponse(
                    409,
                    "Conflict",
                    exception.getMessage(),
                    uriInfo.getPath()
        );
        return Response.status(Response.Status.CONFLICT)
                .entity(error)
                .build();
    }
}
