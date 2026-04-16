package io.github.JorgeMor1.infra.mapper;

import io.github.JorgeMor1.dto.ErrorResponse;
import io.github.JorgeMor1.exception.BusinessException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class InternalServiceExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(InternalServiceExceptionMapper.class);

    @Context
    UriInfo uriInfo;
    @Override
    public Response toResponse(Exception exception){

        LOG.error("Erro inesperado na aplicação", exception);

        ErrorResponse error = new ErrorResponse(
                500,
                "Internal Server Error",
                "Ocorreu um erro inesperado",
                uriInfo.getPath()
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}
