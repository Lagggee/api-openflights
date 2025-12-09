package com.openflights.openflights_api.loader;

import com.openflights.openflights_api.models.Aeroporto;
import com.openflights.openflights_api.repository.aeroportoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class AeroportoDataLoader implements CommandLineRunner {

    private final aeroportoRepository aeroportoRepository;

    public AeroportoDataLoader(aeroportoRepository aeroportoRepository) {
        this.aeroportoRepository = aeroportoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (aeroportoRepository.count() == 0) {
            System.out.println("Iniciando a carga de dados do arquivo CSV...");
            carregarDadosAeroportos();
            System.out.println("Carga de dados concluída. Total de registros: " + aeroportoRepository.count());
        }
    }

    private void carregarDadosAeroportos() {

        ClassPathResource resource = new ClassPathResource("airports.csv");
        List<Aeroporto> aeroportos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
       
                String[] parts = line.split(";");

                if (parts.length >= 8) { 
                    try {
                        Aeroporto aeroporto = new Aeroporto();
                        
                        aeroporto.setIdAeroporto(Integer.parseInt(parts[0]));
                        aeroporto.setNomeAeroporto(parts[1]);
                        aeroporto.setCodigoIata(parts[2]);
                        aeroporto.setCidade(parts[3]);
                        aeroporto.setCodigoPaisIso(parts[4]);
                        aeroporto.setLatitude(Double.parseDouble(parts[5]));
                        aeroporto.setLongitude(Double.parseDouble(parts[6]));
                        
                        aeroporto.setAltitude(Double.parseDouble(parts[7])); 
                        
                        aeroportos.add(aeroporto);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter número na linha: " + line);
                       
                    }
                }
            }
           
            aeroportoRepository.saveAll(aeroportos); 

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro fatal ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}