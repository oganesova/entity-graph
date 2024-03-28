package com.example.graph.service;
import com.example.graph.entity.Client;
import com.example.graph.entity.EmailAddress;
import com.example.graph.repository.ClientRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void generateDB(){
        List<Client> clients = create2000Clients();
        for (Client client : clients) {
            clientRepository.save(client);
        }
    }
    public long countClients() {
        return clientRepository.count();
    }

    public List<Client> findByNameContaining(String userName){
        return clientRepository.findByFullNameContaining(userName);
    }
    public List<Client> create2000Clients() {
        List<Client> clients = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 2000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String sufixTel = String.valueOf(i);
            String telephone = "+375290000000";

            List<EmailAddress>emailAddresses= Arrays.asList(
                    new EmailAddress((firstName + lastName).toLowerCase() + "1" + i + "@gmail.com"),
                    new EmailAddress((firstName + lastName).toLowerCase() + "2" + i + "@gmail.com"));

            telephone = telephone.substring(0, telephone.length()-sufixTel.length()) + sufixTel;
            Client client = new Client(
                    firstName + " " + lastName,
                    telephone,
                    emailAddresses
            );

            for (EmailAddress emailAddress:emailAddresses) {
                emailAddress.setClient(client);
            }

            clients.add(client);
        }
        return clients;
    }
}