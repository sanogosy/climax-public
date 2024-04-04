package com.climax.fileprocess.service.impl;

import com.climax.fileprocess.domain.Client;
import com.climax.fileprocess.domain.dto.ClientDTO;
import com.climax.fileprocess.repository.ClientRepository;
import com.climax.fileprocess.service.FileProcessService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FileProcessServiceImpl implements FileProcessService {

    private final ClientRepository clientRepository;


    public FileProcessServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public InputStream convertFileToStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ClientDTO> readStreamAndSave(InputStream inputStream) {
        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.EXCEL.withDelimiter(';').withHeader("nom", "prenom", "identifiant", "profession", "revenu")
                );
        ) {

            List<ClientDTO> clientDTOArrayList = new ArrayList<ClientDTO>();
            List<CSVRecord> records = csvParser.getRecords();
            int nbr = 0;
            for (CSVRecord csvRecord : records){
                nbr = nbr+1;
                Map<String, String> csvMap = csvRecord.toMap();

                Client client = new Client();
                client.setNom_client(csvMap.get("nom").toString());
                client.setPrenom_client(csvMap.get("prenom").toString());
                client.setIdentifiant(csvMap.get("identifiant").toString());
                client.setClient_revenu(csvMap.get("revenu").toString());
                client.setProfession_client(csvMap.get("profession").toString());

                Client newClient = clientRepository.save(client);
                if (newClient.getId() != null) {

                    ClientDTO clientDTO = new ClientDTO();
                    clientDTO.setNom_client(newClient.getNom_client());
                    clientDTO.setPrenom_client(newClient.getPrenom_client());
                    clientDTO.setIdentifiant(client.getIdentifiant());
                    clientDTO.setClient_revenu(client.getClient_revenu());
                    clientDTO.setProfession_client(client.getProfession_client());

                    clientDTOArrayList.add(clientDTO);
                }
                else {
                    String content = csvRecord.get("nom").toString();

                }
            }

            return clientDTOArrayList;
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ClientDTO> readFileTxtAndProcess(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<ClientDTO> clientDTOArrayList = new ArrayList<ClientDTO>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                String [] res = line.split(",");

                Client client = new Client();
                client.setNom_client(res[0]);
                client.setPrenom_client(res[1]);
                client.setIdentifiant(res[2]);
                client.setProfession_client(res[3]);
                client.setClient_revenu(res[4]);

                Client newClient = clientRepository.save(client);
                if (newClient.getId() != null) {

                    ClientDTO clientDTO = new ClientDTO();
                    clientDTO.setNom_client(newClient.getNom_client());
                    clientDTO.setPrenom_client(newClient.getPrenom_client());
                    clientDTO.setIdentifiant(client.getIdentifiant());
                    clientDTO.setProfession_client(client.getProfession_client());
                    clientDTO.setClient_revenu(client.getClient_revenu());

                    clientDTOArrayList.add(clientDTO);
                }
                else {

                }
            }
        } catch(Exception e) {

        }
        finally {
            br.close();
        }

        return clientDTOArrayList;
    }

    @Override
    public List<ClientDTO> readMultipartFileAndSaveClient(MultipartFile file) {
        return null;
    }

    @Override
    public List<ClientDTO> readFileAndSaveClient(File file) {
        InputStream inputStream = convertFileToStream(file);
        readStreamAndSave(inputStream);
        return null;
    }

    @Override
    public List<ClientDTO> readTxtFileAndProcess(File file) throws IOException {
         return readFileTxtAndProcess(file);
    }
}
