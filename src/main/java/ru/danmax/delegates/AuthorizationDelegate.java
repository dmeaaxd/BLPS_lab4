package ru.danmax.delegates;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.dto.AuthorizationDTO;
import ru.danmax.service.ClientService;

@Named("authorization")
@RequiredArgsConstructor
public class AuthorizationDelegate implements JavaDelegate {
    private final ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");

        clientService.auth(AuthorizationDTO.builder()
                .username(username)
                .password(password)
                .build());
    }
}
