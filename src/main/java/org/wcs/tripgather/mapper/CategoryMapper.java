package org.wcs.tripgather.mapper;

import org.springframework.stereotype.Component;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.dto.EventDTO;
import org.wcs.tripgather.model.Category;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setImg(category.getImg());
        categoryDTO.setColor(category.getColor());

        if (category.getEvents() != null) {
            categoryDTO.setEvents(category.getEvents().stream().map(event -> {
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTitle(event.getTitle());
                eventDTO.setDescription(event.getDescription());
                eventDTO.setImgUrl(event.getImgUrl());
                eventDTO.setLocalisation(event.getLocalisation());
                eventDTO.setMixte(event.isMixte());
                eventDTO.setOwner(event.getOwner());
                eventDTO.setPrice(event.getPrice());
                eventDTO.setFromDate(event.getFromDate());
                eventDTO.setToDate(event.getToDate());
                eventDTO.setStartRegistration(event.getStartRegistration());
                eventDTO.setEndRegistration(event.getEndRegistration());
                return eventDTO;
            }).collect(Collectors.toList()));
        }
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
