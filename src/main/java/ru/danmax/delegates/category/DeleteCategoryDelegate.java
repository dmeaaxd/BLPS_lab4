package ru.danmax.delegates.category;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.service.CategoryService;

@Named("deleteCategory")
@RequiredArgsConstructor
public class DeleteCategoryDelegate implements JavaDelegate {
    private final CategoryService categoryService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("categoryId");
        categoryService.delete(id);
    }
}
