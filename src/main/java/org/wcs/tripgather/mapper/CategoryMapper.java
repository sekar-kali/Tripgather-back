package org.wcs.tripgather.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.dto.EventDTO;
import org.wcs.tripgather.model.Category;
import org.wcs.tripgather.repository.EventRepository;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final EventRepository eventRepository;  // Injecter EventRepository

    // Injection du EventRepository via le constructeur
    @Autowired
    public CategoryMapper(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setImg(category.getImg());
        categoryDTO.setColor(category.getColor());

        long eventCount = eventRepository.countByCategoriesId(category.getId());
        categoryDTO.setEventCount(eventCount);

        return categoryDTO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setImg(categoryDTO.getImg());
        category.setColor(categoryDTO.getColor());

        return category;
    }
}


