package com.openflights.openflights_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class aeroportoDTO {

    private Integer idAeroporto; 

    @NotBlank(message = "O nome do aeroporto não pode ser vazio.")
    private String nome_aeroporto;

    @NotBlank(message = "O código IATA é obrigatório.")
    private String codigo_iata;

    @NotBlank(message = "A cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "O código ISO do país é obrigatório.")
    private String codigo_pais_iso;

    @NotNull(message = "A latitude é obrigatória.")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória.")
    private Double longitude;

    @NotNull(message = "A altitude é obrigatória.")
    private Double altitude;
}
