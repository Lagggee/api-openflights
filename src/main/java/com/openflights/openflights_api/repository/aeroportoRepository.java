package com.openflights.openflights_api.repository;

import com.openflights.openflights_api.models.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface aeroportoRepository extends JpaRepository<Aeroporto, Integer> {

    Optional<Aeroporto> findByCodigoIata(String codigoIata);
    
    boolean existsByCodigoIata(String codigoIata);
}