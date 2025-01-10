package org.wcs.tripgather.mapper;

import org.springframework.stereotype.Component;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.dto.EventDTO;
import org.wcs.tripgather.model.Category;
import org.wcs.tripgather.model.Event;
import org.wcs.tripgather.model.Gender;
import org.wcs.tripgather.repository.CategoryRepository;
import org.wcs.tripgather.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    public EventMapper(CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
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
                        categoryDTO.setColor(category.getColor());
                        categoryDTO.setImg(category.getImg());
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
        event.setGender(eventDTO.getGender());
        event.setMixte(eventDTO.isMixte());
        event.setStartRegistration(eventDTO.getStartRegistration());
        event.setEndRegistration(eventDTO.getEndRegistration());
        event.setPrice(eventDTO.getPrice());
        event.setOwner(eventDTO.getOwner());
        event.setMaxParticipant(eventDTO.getMaxParticipant());
        event.setImgUrl(eventDTO.getImgUrl());
        if (eventDTO.getCategories() != null && !eventDTO.getCategories().isEmpty()) {
            List<Category> categories = new ArrayList<>();
            for (CategoryDTO categoryDTO : eventDTO.getCategories()) {
                if (categoryDTO.getId() != null) {
                    Category category = categoryRepository.findById(categoryDTO.getId()).orElse(null);
                    if (category != null) {
                        categories.add(category);
                    } else {
                        return null;
                    }
                }
            }
            event.setCategories(categories);
        }
        return event;
    }

    public List<EventDTO> getFilteredEvents(String localisation, Gender gender,String title, String fromDate, String toDate) {
        List<Event> filteredEvents = eventRepository.findFilteredEvents(localisation, gender,title, fromDate, toDate);  // Utilisation de l'instance
        return filteredEvents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
