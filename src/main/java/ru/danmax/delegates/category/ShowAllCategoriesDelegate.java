package ru.danmax.delegates.category;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.entity.Category;
import ru.danmax.service.CategoryService;

import java.util.List;

@Named("showAllCategories")
@RequiredArgsConstructor
public class ShowAllCategoriesDelegate implements JavaDelegate {
    private final CategoryService categoryService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Category> categories = categoryService.getAll();

        for (Category category : categories) {
            delegateExecution.setVariable("category_" + category.getId(), category.getName());
        }
    }
}
