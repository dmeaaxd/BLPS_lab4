package ru.danmax.app.delegates.client;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.dto.AuthorizationDTO;
import ru.danmax.app.entity.Client;
import ru.danmax.app.service.ClientService;

@Named("authorization")
@RequiredArgsConstructor
public class AuthorizationDelegate implements JavaDelegate {
    private final ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");

        Client client = clientService.auth(AuthorizationDTO.builder()
                .username(username)
                .password(password)
                .build());

        delegateExecution.setVariable("clientId", client.getId());
    }
}
