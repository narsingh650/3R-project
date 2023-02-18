package com.serviceworker.serviceImpl;

import com.serviceworker.dto.CategoryDto;
import com.serviceworker.model.Category;
import com.serviceworker.repository.CategoryRepository;
import com.serviceworker.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category map = mapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(map);
        return this.mapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> findAll = categoryRepository.findAll();
        List<CategoryDto> allCategory = findAll.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return allCategory;
    }


}
