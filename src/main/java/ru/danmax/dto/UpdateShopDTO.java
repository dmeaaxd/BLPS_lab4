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
public class UpdateShopDTO implements FieldIsEmptyCheck {

    private Long shopId;
    private String name;
    private String description;
    private Long categoryId;

    @Override
    public boolean isFieldEmpty() {
        if (shopId == null) return true;
        if (name == null || name.isEmpty()) return true;
        if (description == null || description.isEmpty()) return true;
        if (categoryId == null) return true;
        return false;
    }

    public boolean isAllFieldsEmpty() {
        return ((name == null || name.isEmpty()) && (description == null || description.isEmpty()) && (categoryId == null));
    }
}
