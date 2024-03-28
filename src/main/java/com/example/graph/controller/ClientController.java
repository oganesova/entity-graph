package com.example.graph.controller;

import com.example.graph.dto.ClientDto;
import com.example.graph.entity.Client;
import com.example.graph.repository.ClientRepository;
import com.example.graph.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/fillDB")
    public String fillDataBase() {
        clientService.generateDB();
        return "Amount clients: " + clientService.countClients();
    }

    @GetMapping()
    public ResponseEntity<List<ClientDto>> findClientsByNameContaining(@RequestParam String clientName) {
        List<Client> clients = clientService.findByNameContaining(clientName);
        List<ClientDto> clientDtos = clients.stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(clientDtos);
    }

}
