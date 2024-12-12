package org.wcs.tripgather.service;


import org.springframework.stereotype.Service;
import org.wcs.tripgather.exception.UserNotFoundException;
import org.wcs.tripgather.mapper.EventMapper;
import org.wcs.tripgather.model.*;
import org.wcs.tripgather.repository.*;
import org.wcs.tripgather.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    public EventService(
            EventRepository eventRepository,
            EventMapper eventMapper,
            CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
       return eventRepository.findAll()
               .stream()
               .map(eventMapper::convertToDTO)
               .collect(Collectors.toList());
    }

    public EventDTO getEventById(long id) {
        Event event = eventRepository.findById(id).orElseThrow(()
        ->new UserNotFoundException("Le voyage avec l'id " + id + " n'a pas été trouvé"));
        if (event == null) {
            return null;
        }
        return eventMapper.convertToDTO(event);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventMapper.convertToEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.convertToDTO(savedEvent);
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) {
            throw new RuntimeException("Event not found");
        }

        Event eventToUpdate = optionalEvent.get();
        eventToUpdate.setTitle(eventDTO.getTitle());
        eventToUpdate.setDescription(eventDTO.getDescription());
        eventToUpdate.setLocalisation(eventDTO.getLocalisation());
        eventToUpdate.setFromDate(eventDTO.getFromDate());
        eventToUpdate.setToDate(eventDTO.getToDate());
        eventToUpdate.setStatus(eventDTO.getStatus());
        eventToUpdate.setStartRegistration(eventDTO.getStartRegistration());
        eventToUpdate.setEndRegistration(eventDTO.getEndRegistration());
        eventToUpdate.setPrice(eventDTO.getPrice());
        eventToUpdate.setOwner(eventDTO.getOwner());
        eventToUpdate.setMaxParticipant(eventDTO.getMaxParticipant());
        eventToUpdate.setMixte(eventDTO.isMixte());
        eventToUpdate.setImgUrl(eventDTO.getImgUrl());

        Event updatedEvent = eventRepository.save(eventToUpdate);
        return eventMapper.convertToDTO(updatedEvent);
    }

    public boolean deleteEvent(Long id) {
     Event event = eventRepository.findById(id).orElse(null);
     if (event == null) {
        return false;
     }

     eventRepository.delete(event);
        return false;
    }

}

