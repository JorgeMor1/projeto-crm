package io.github.JorgeMor1.infra.mapper;

import io.github.JorgeMor1.dto.ErroResponse;
import io.github.JorgeMor1.exception.CargoNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CargoNotFoundExceptionMapper implements ExceptionMapper<CargoNotFoundException> {
    @Override
    public Response toResponse(CargoNotFoundException ex) {
        ErroResponse erro = new ErroResponse(
                Response.Status.NOT_FOUND.getStatusCode(),
                "CARGO_NAO_ENCONTRADO",
                ex.getMessage()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(erro)
                .build();
    }
}
