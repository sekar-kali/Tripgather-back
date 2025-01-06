package org.wcs.tripgather.service;


import org.springframework.stereotype.Service;
import org.wcs.tripgather.exception.UserNotFoundException;
import org.wcs.tripgather.mapper.EventMapper;
import org.wcs.tripgather.model.*;
import org.wcs.tripgather.repository.*;
import org.wcs.tripgather.dto.*;

import java.time.LocalDateTime;
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

        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        Event savedEvent = eventRepository.save(event);
        return eventMapper.convertToDTO(savedEvent);
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event eventToUpdate = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event Not Found"));

        if (eventDTO.getTitle() != null) eventToUpdate.setTitle(eventDTO.getTitle());
        if (eventDTO.getDescription() != null) eventToUpdate.setDescription(eventDTO.getDescription());
        if (eventDTO.getLocalisation() != null) eventToUpdate.setLocalisation(eventDTO.getLocalisation());
        if (eventDTO.getFromDate() != null) eventToUpdate.setFromDate(eventDTO.getFromDate());
        if (eventDTO.getToDate() != null) eventToUpdate.setToDate(eventDTO.getToDate());
        if (eventDTO.getStatus() != null) eventToUpdate.setStatus(eventDTO.getStatus());
        if (eventDTO.getStartRegistration() != null) eventToUpdate.setStartRegistration(eventDTO.getStartRegistration());
        if (eventDTO.getEndRegistration() != null) eventToUpdate.setEndRegistration(eventDTO.getEndRegistration());
        if (eventDTO.getPrice() != null) eventToUpdate.setPrice(eventDTO.getPrice());
        if (eventDTO.getOwner() != null) eventToUpdate.setOwner(eventDTO.getOwner());
        if (eventDTO.getMaxParticipant() != null) eventToUpdate.setMaxParticipant(eventDTO.getMaxParticipant());
        if (eventDTO.isMixte() != null) eventToUpdate.setMixte(eventDTO.isMixte());
        if (eventDTO.getImgUrl() != null) eventToUpdate.setImgUrl(eventDTO.getImgUrl());

        eventToUpdate.setUpdatedAt(LocalDateTime.now());
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

