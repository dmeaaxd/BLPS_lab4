package ru.danmax.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.danmax.dto.AuthorizationDTO;
import ru.danmax.dto.RegistrationDTO;
import ru.danmax.entity.Role;
import ru.danmax.entity.Client;
import ru.danmax.repository.RoleRepository;
import ru.danmax.repository.ClientRepository;
import ru.danmax.utils.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;
    private RoleRepository roleRepository;

    public void auth(AuthorizationDTO authorizationDTO) throws Exception {
        if (authorizationDTO.isFieldEmpty()){
            throw new Exception("Fields cannot be empty");
        }

        Client client = clientRepository.findByUsername(authorizationDTO.getUsername())
                .orElseThrow(() -> new Exception("User not found"));


        BCryptPasswordEncoder passwordEncoder = BCryptPasswordEncoder.getInstance();
        if (!passwordEncoder.matches(authorizationDTO.getPassword(), client.getPassword())){
            throw new Exception("Incorrect user data");
        }
    }

    public void register(RegistrationDTO registrationDTO) throws Exception{
        if (registrationDTO.isFieldEmpty()){
            throw new Exception("Fields cannot be empty");
        }

        if (clientRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new Exception("Username is already taken");
        }

        if (clientRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new Exception("Email is already taken");
        }

        BCryptPasswordEncoder passwordEncoder = BCryptPasswordEncoder.getInstance();

        Client client = Client.builder()
                .username(registrationDTO.getUsername())
                .email(registrationDTO.getEmail())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .build();

        Role role = roleRepository.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        client.setRoles(roles);

        clientRepository.save(client);
    }
}
