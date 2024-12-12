package org.wcs.tripgather.service;

import org.springframework.stereotype.Service;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.mapper.CategoryMapper;
import org.wcs.tripgather.model.Category;
import org.wcs.tripgather.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return categoryMapper.convertToDTO(category);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.convertToEntity(categoryDTO);
        return categoryMapper.convertToDTO(category);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Catégorie non trouvée, l'Id " + id + " n'existe pas."));
        if (categoryDTO.getName() != null && !categoryDTO.getName().equals(category.getName())) {
            category.setName(categoryDTO.getName());
        }
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.convertToDTO(updatedCategory);
    }

    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }
}
