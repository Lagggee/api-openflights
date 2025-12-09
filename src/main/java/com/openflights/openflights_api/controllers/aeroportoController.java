package com.openflights.openflights_api.controllers;

import com.openflights.openflights_api.dto.aeroportoDTO;
import com.openflights.openflights_api.services.aeroportoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/v1/aeroportos") 
public class aeroportoController {

    private final aeroportoService aeroportoService;

    public aeroportoController(aeroportoService aeroportoService) {
        this.aeroportoService = aeroportoService;
    }

    @GetMapping
    public ResponseEntity<List<aeroportoDTO>> getAllAeroportos() {
        List<aeroportoDTO> aeroportos = aeroportoService.findAll();
        return ResponseEntity.ok(aeroportos); // Retorna 200 OK
    }

  
    @GetMapping("/{iata}")
    public ResponseEntity<aeroportoDTO> getAeroportoByIata(@PathVariable String iata) {
      
        aeroportoDTO aeroporto = aeroportoService.findByIata(iata);
        return ResponseEntity.ok(aeroporto);
    }


    @PostMapping

    public ResponseEntity<aeroportoDTO> createAeroporto(@RequestBody @Valid aeroportoDTO aeroportoDTO) {
        aeroportoDTO novoAeroporto = aeroportoService.save(aeroportoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoAeroporto); 
    }

   
    @PutMapping("/{iata}")
    public ResponseEntity<aeroportoDTO> updateAeroporto(
            @PathVariable String iata,
            @RequestBody @Valid aeroportoDTO aeroportoDTO) {
        
   
        aeroportoDTO aeroportoAtualizado = aeroportoService.update(iata, aeroportoDTO);
        return ResponseEntity.ok(aeroportoAtualizado); 
    }


    @DeleteMapping("/{iata}")

    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void deleteAeroporto(@PathVariable String iata) {
   
        aeroportoService.delete(iata);
    }
}