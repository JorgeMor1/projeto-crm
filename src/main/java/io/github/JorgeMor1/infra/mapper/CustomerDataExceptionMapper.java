package io.github.JorgeMor1.infra.mapper;

import io.github.JorgeMor1.dto.ErroResponse;
import io.github.JorgeMor1.exception.CustomerDataException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomerDataExceptionMapper implements ExceptionMapper<CustomerDataException> {
    @Override
    public Response toResponse(CustomerDataException ex){
        ErroResponse erro = new ErroResponse(
                Response.Status.CONFLICT.getStatusCode(), "DADO_EXISTENTE",
                ex.getMessage()
        );

    return Response.status(Response.Status.CONFLICT)
            .entity(erro)
            .build();
} }
