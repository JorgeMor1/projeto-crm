package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.Cargos;

public class CargoResponseDTO {
    private Long idCargo;
    private String nomeCargo;

    public CargoResponseDTO(Long idCargo, String nomeCargo) {
        this.idCargo = idCargo;
        this.nomeCargo = nomeCargo;
    }

    public static  CargoResponseDTO cargoResponseDTO (Cargos cargos){
        return new CargoResponseDTO(cargos.getCargo_id(), cargos.getNomeCargo());
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
