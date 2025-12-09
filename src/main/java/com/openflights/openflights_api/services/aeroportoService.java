package com.openflights.openflights_api.services;

import com.openflights.openflights_api.dto.aeroportoDTO;
import com.openflights.openflights_api.models.Aeroporto;
import java.util.List;

public interface aeroportoService {
    
    List<aeroportoDTO> findAll();
    
    aeroportoDTO findByIata(String iata);
    
    aeroportoDTO save(aeroportoDTO aeroportoDTO);
    
    aeroportoDTO update(String iata, aeroportoDTO aeroportoDTO);
    
    void delete(String iata);
    
}