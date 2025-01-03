package org.wcs.tripgather.mapper;

import org.springframework.stereotype.Component;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.dto.EventDTO;
import org.wcs.tripgather.model.Category;
import org.wcs.tripgather.model.Event;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    public EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocalisation(event.getLocalisation());
        eventDTO.setFromDate(event.getFromDate());
        eventDTO.setToDate(event.getToDate());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setMixte(event.isMixte());
        eventDTO.setStartRegistration(event.getStartRegistration());
        eventDTO.setEndRegistration(event.getEndRegistration());
        eventDTO.setPrice(event.getPrice());
        eventDTO.setOwner(event.getOwner());
        eventDTO.setCreatedAt(event.getCreatedAt());
        eventDTO.setUpdatedAt(event.getUpdatedAt());
        eventDTO.setMaxParticipant(event.getMaxParticipant());
        eventDTO.setImgUrl(event.getImgUrl());
        if (event.getCategories() != null) {
            eventDTO.setCategories(event.getCategories().stream().filter(category -> category.getName() != null).map(category -> {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setId(category.getId());
                        categoryDTO.setName(category.getName());
                        return categoryDTO;
                    })
                    .collect(Collectors.toList()));
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
        event.setMixte(eventDTO.isMixte());
        event.setStartRegistration(eventDTO.getStartRegistration());
        event.setEndRegistration(eventDTO.getEndRegistration());
        event.setPrice(eventDTO.getPrice());
        event.setOwner(eventDTO.getOwner());
        event.setCreatedAt(eventDTO.getCreatedAt());
        event.setUpdatedAt(eventDTO.getUpdatedAt());
        event.setMaxParticipant(eventDTO.getMaxParticipant());
        event.setImgUrl(eventDTO.getImgUrl());
        return event;
    }
}
