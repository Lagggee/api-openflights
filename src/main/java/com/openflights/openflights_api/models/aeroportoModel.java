package com.openflights.openflights_api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
    
@Data
@NoArgsConstructor
@AllArgsConstructor
public class aeroportoModel {

    private Integer id_aeroporto;

    private String nome_aeroporto;

    private String codigo_iata;

    private String cidade;

    private String codigo_pais_iso;

    private Double latitude;

    private Double longitutde;

    private Double altitude;

}
