package org.wcs.tripgather.mapper;

import org.springframework.stereotype.Component;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.dto.EventDTO;
import org.wcs.tripgather.dto.UserDTO;
import org.wcs.tripgather.model.Category;
import org.wcs.tripgather.model.Event;
import org.wcs.tripgather.model.User;
import org.wcs.tripgather.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    private final CategoryRepository categoryRepository;

    public EventMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocalisation(event.getLocalisation());
        eventDTO.setFromDate(event.getFromDate());
        eventDTO.setToDate(event.getToDate());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setGender(event.getGender());
        eventDTO.setStartRegistration(event.getStartRegistration());
        eventDTO.setEndRegistration(event.getEndRegistration());
        eventDTO.setPrice(event.getPrice());
        if (eventDTO.getOwner() != null && eventDTO.getOwner().getId() != null) {
            User owner = new User();
            owner.setId(eventDTO.getOwner().getId());
            event.setOwner(owner);
        }
        eventDTO.setCreatedAt(event.getCreatedAt());
        eventDTO.setUpdatedAt(event.getUpdatedAt());
        eventDTO.setMaxParticipant(event.getMaxParticipant());
        eventDTO.setImgUrl(event.getImgUrl());
        if (eventDTO.getCategories() != null && !eventDTO.getCategories().isEmpty()) {
            List<Category> categories = eventDTO.getCategories().stream()
                    .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId()).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            event.setCategories(categories);
        }

        return eventDTO;
    }


    public Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocalisation(eventDTO.getLocalisation());
        event.setFromDate(eventDTO.getFromDate());
        event.setToDate(eventDTO.getToDate());
        event.setStatus(eventDTO.getStatus());
        event.setGender(eventDTO.getGender());
        event.setStartRegistration(eventDTO.getStartRegistration());
        event.setEndRegistration(eventDTO.getEndRegistration());
        event.setPrice(eventDTO.getPrice());
        event.setMaxParticipant(eventDTO.getMaxParticipant());
        event.setImgUrl(eventDTO.getImgUrl());
        if (eventDTO.getOwner() != null && eventDTO.getOwner().getId() != null) {
            User owner = new User();
            owner.setId(eventDTO.getOwner().getId());
            event.setOwner(owner);
        }
        if (eventDTO.getCategories() != null && !eventDTO.getCategories().isEmpty()) {
            List<Category> categories = eventDTO.getCategories().stream()
                    .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Category not found for ID: " + categoryDTO.getId())))
                    .collect(Collectors.toList());
            event.setCategories(categories);
        }

        return event;
    }

}
