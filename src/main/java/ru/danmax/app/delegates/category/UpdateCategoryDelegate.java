package ru.danmax.app.delegates.category;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.service.CategoryService;

@Named("updateCategory")
@RequiredArgsConstructor
public class UpdateCategoryDelegate implements JavaDelegate {
    private final CategoryService categoryService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("categoryId");
        String name = (String) delegateExecution.getVariable("categoryName");
        categoryService.update(id, name);
    }
}
