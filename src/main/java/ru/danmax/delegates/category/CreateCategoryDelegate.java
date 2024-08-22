package ru.danmax.delegates.category;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.service.CategoryService;

@Named("createCategory")
@RequiredArgsConstructor
public class CreateCategoryDelegate implements JavaDelegate {
    private final CategoryService categoryService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String name = (String) delegateExecution.getVariable("categoryName");
        categoryService.create(name);
    }
}
