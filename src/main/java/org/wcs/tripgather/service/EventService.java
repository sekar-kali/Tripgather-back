package org.wcs.tripgather.service;


import org.springframework.stereotype.Service;
import org.wcs.tripgather.exception.UserNotFoundException;
import org.wcs.tripgather.mapper.EventMapper;
import org.wcs.tripgather.model.*;
import org.wcs.tripgather.repository.*;
import org.wcs.tripgather.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CategoryRepository categoryRepository;


    public EventService(
            EventRepository eventRepository,
            EventMapper eventMapper,
            CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::convertToDTO).collect(Collectors.toList()).reversed();
    }

    public EventDTO getEventById(long id) {
        Event event = eventRepository.findById(id).orElseThrow(()
        ->new UserNotFoundException("Le voyage avec l'id " + id + " n'a pas été trouvé"));
        if (event == null) {
            return null;
        }
        return eventMapper.convertToDTO(event);
    }

    public EventDTO createEvent(EventCreateDTO eventCreateDTO) {
        Event event = eventMapper.convertToEntity(eventCreateDTO);

        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDate.now());
    }

}

