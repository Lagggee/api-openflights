package com.openflights.openflights_api;

import com.openflights.openflights_api.exceptions.AeroportoNaoEncontradoException;
import com.openflights.openflights_api.models.Aeroporto;
import com.openflights.openflights_api.repository.aeroportoRepository;
import com.openflights.openflights_api.services.aeroportoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class aeroportoServiceTest {

    @Mock
    private aeroportoRepository aeroportoRepository;

    @InjectMocks
    private aeroportoServiceImpl aeroportoService;

    private static final String IATA_EXISTENTE = "GRU";
    private static final String IATA_INEXISTENTE = "ZZZ";

    @BeforeEach
    void setup() {

        Aeroporto mockAeroporto = new Aeroporto(1, "Aeroporto Guarulhos", IATA_EXISTENTE, "São Paulo", "BR", -23.435555, -46.473056, 750.0);

  
        when(aeroportoRepository.findByCodigoIata(IATA_EXISTENTE))
                .thenReturn(Optional.of(mockAeroporto));
        when(aeroportoRepository.findByCodigoIata(IATA_INEXISTENTE))
                .thenReturn(Optional.empty());
    }


    @Test
    void buscarAeroportoPorIataInexistente_deveLancarExcecao() {
   
        assertThrows(AeroportoNaoEncontradoException.class, () -> {
            aeroportoService.findByIata(IATA_INEXISTENTE);
        });

    
        verify(aeroportoRepository, times(1)).findByCodigoIata(IATA_INEXISTENTE);
    }

 
    @Test
    void buscarAeroportoPorIataExistente_deveRetornarDTO() {
      
        var dto = aeroportoService.findByIata(IATA_EXISTENTE);

   
        assertNotNull(dto);
        assertEquals(IATA_EXISTENTE, dto.getCodigoIata());
        assertEquals("Aeroporto Guarulhos", dto.getNomeAeroporto());
    }


}
