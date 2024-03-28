package com.example.graph.dto;

import com.example.graph.entity.Client;
import com.example.graph.entity.EmailAddress;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClientDto {
    private Long id;
    private String fullName;
    private String mobileNumber;
    private List<String> emailAddresses;
    public ClientDto(Long id, String fullName, String mobileNumber, List<String> emailAddresses) {
        this.id = id;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailAddresses = emailAddresses;
    }
    public static ClientDto fromEntity(Client client) {
        List<String> emails = client.getEmailAddresses().stream()
                .map(EmailAddress::getEmail)
                .collect(Collectors.toList());
        return new ClientDto(client.getId(), client.getFullName(), client.getMobileNumber(), emails);
    }
}
