package com.openflights.openflights_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "aeroporto")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Aeroporto {

    @Id
    @Column(name = "id_aeroporto")
    private Integer id_aeroporto;

    @Column(name = "nome_aeroporto", nullable = false)
    private String nome_aeroporto;

    @Column(name = "codigo_iata", length = 3, unique = true, nullable = false)
    private String codigo_iata;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "codigo_pais_iso", length = 2, nullable = false)
    private String codigo_pais_iso;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "altitude", nullable = false)
    private Double altitude;

}
