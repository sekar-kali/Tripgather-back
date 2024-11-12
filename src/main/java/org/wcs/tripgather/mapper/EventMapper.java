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
        eventDTO.setStartRegistration(event.getStartRegistration());
        eventDTO.setEndRegistration(event.getEndRegistration());
        eventDTO.setPrice(event.getPrice());
        eventDTO.setOwner(event.getOwner());
        eventDTO.setCreatedAt(event.getCreatedAt());
        eventDTO.setUpdatedAt(event.getUpdatedAt());
        eventDTO.setMaxParticipant(event.getMaxParticipant());
        eventDTO.setImgUrl(event.getImgUrl());
        if (event.getCategories() != null && !event.getCategories().isEmpty()) {
            eventDTO.setCategoryName(event.getCategories().stream()
                    .map(categories -> {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setId(categories.getId());
                        categoryDTO.setName(categories.getName());
                        return categoryDTO;
                    })
                    .collect(Collectors.toList()));
        }
            return eventDTO;
        }
    }



        public Event convertToEntity(EventDTO eventDTO)

}
