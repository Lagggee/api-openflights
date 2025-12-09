package com.openflights.openflights_api.services;

import com.openflights.openflights_api.dto.aeroportoDTO;
import com.openflights.openflights_api.exceptions.AeroportoNaoEncontradoException;
import com.openflights.openflights_api.models.Aeroporto;
import com.openflights.openflights_api.repository.aeroportoRepository;
import com.openflights.openflights_api.services.aeroportoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class aeroportoServiceImpl implements aeroportoService {

    private final aeroportoRepository aeroportoRepository;


    public aeroportoServiceImpl(aeroportoRepository aeroportoRepository) {
        this.aeroportoRepository = aeroportoRepository;
    }


    private aeroportoDTO toDTO(Aeroporto aeroporto) {

        return new aeroportoDTO(
            aeroporto.getId_aeroporto(),
            aeroporto.getNome_aeroporto(),
            aeroporto.getCodigo_iata(),
            aeroporto.getCidade(),
            aeroporto.getCodigo_pais_iso(),
            aeroporto.getLatitude(),
            aeroporto.getLongitude(),
            aeroporto.getAltitude()
        );
    }
    
    private Aeroporto toEntity(aeroportoDTO dto) {
        Aeroporto aeroporto = new Aeroporto();

        aeroporto.setId_aeroporto(dto.getIdAeroporto()); 
        aeroporto.setNome_aeroporto(dto.getNomeAeroporto());
        aeroporto.setCodigo_iata(dto.getCodigoIata());
        aeroporto.setCidade(dto.getCidade());
        aeroporto.setCodigo_pais_iso(dto.getCodigoPaisIso());
        aeroporto.setLatitude(dto.getLatitude());
        aeroporto.setLongitude(dto.getLongitude());
        aeroporto.setAltitude(dto.getAltitude());
        return aeroporto;
    }

    @Override
    public List<aeroportoDTO> findAll() {
     
        return aeroportoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public aeroportoDTO findByIata(String iata) {
       
        Aeroporto aeroporto = aeroportoRepository.findByCodigoIata(iata)
                .orElseThrow(() -> new AeroportoNaoEncontradoException(iata));
        
        return toDTO(aeroporto);
    }

    @Override
    public aeroportoDTO save(aeroportoDTO aeroportoDTO) {

        if (aeroportoRepository.existsByCodigoIata(aeroportoDTO.getCodigoIata())) {
          
            throw new IllegalArgumentException("Aeroporto com IATA " + aeroportoDTO.getCodigoIata() + " já existe.");
        }
        
    
        Aeroporto aeroporto = toEntity(aeroportoDTO);
        
    
        Aeroporto savedAeroporto = aeroportoRepository.save(aeroporto);
        
        return toDTO(savedAeroporto);
    }

    @Override
    public aeroportoDTO update(String iata, aeroportoDTO aeroportoDTO) {
      
        Aeroporto existingAeroporto = aeroportoRepository.findByCodigoIata(iata)
                .orElseThrow(() -> new AeroportoNaoEncontradoException(iata));

        existingAeroporto.setNome_aeroporto(aeroportoDTO.getNomeAeroporto());
        existingAeroporto.setCidade(aeroportoDTO.getCidade());
        existingAeroporto.setCodigo_pais_iso(aeroportoDTO.getCodigoPaisIso());
        existingAeroporto.setLatitude(aeroportoDTO.getLatitude());
        existingAeroporto.setLongitude(aeroportoDTO.getLongitude());
        existingAeroporto.setAltitude(aeroportoDTO.getAltitude());

        Aeroporto updatedAeroporto = aeroportoRepository.save(existingAeroporto);
        
        return toDTO(updatedAeroporto);
    }

    @Override
    public void delete(String iata) {
     
        Aeroporto aeroporto = aeroportoRepository.findByCodigoIata(iata)
                .orElseThrow(() -> new AeroportoNaoEncontradoException(iata));
        
       
        aeroportoRepository.delete(aeroporto);
    }
}
