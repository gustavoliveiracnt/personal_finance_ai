package com.personal.finance.service;

import com.personal.finance.entity.Category;
import com.personal.finance.repository.CategoryRepository;
import com.personal.finance.utils.ToolsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public Category findOrCreate(String categoryName) {
        String normalizedName = ToolsUtil.normalize(categoryName);
        return repository.findByNameIgnoreCase(normalizedName)
                .orElseGet(() -> create(normalizedName));
    }

    private Category create(String categoryName) {
        Category category = Category.builder()
                .name(categoryName.trim())
                .build();

        return repository.save(category);
    }
}
