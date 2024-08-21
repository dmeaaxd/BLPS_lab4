package ru.danmax.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.danmax.dto.interfaces.FieldIsEmptyCheck;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO implements FieldIsEmptyCheck {
    private String username;
    private String email;
    private String password;

    @Override
    public boolean isFieldEmpty(){
        if (username == null || username.isEmpty()) return true;
        if (password == null || password.isEmpty()) return true;
        if (email == null || email.isEmpty()) return true;
        return false;
    }
}
