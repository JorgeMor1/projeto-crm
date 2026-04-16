package io.github.JorgeMor1.infra.mapper;

import io.github.JorgeMor1.dto.ErrorResponse;
import io.github.JorgeMor1.exception.BadRequestException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    @Context
    UriInfo uriInfo;
    @Override
    public Response toResponse(BadRequestException exception){
        ErrorResponse error = new ErrorResponse(
                400,
                "Bad Request",
                exception.getMessage(),
                uriInfo.getPath()
        );
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}
