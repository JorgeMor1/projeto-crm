package io.github.JorgeMor1.exception;

import io.github.JorgeMor1.dto.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Context
    UriInfo uriInfo;
    @Override
    public Response toResponse(ResourceNotFoundException exception) {

        ErrorResponse error = new ErrorResponse(
                404,
                "Resource not found",
                exception.getMessage(),
                uriInfo.getPath()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
