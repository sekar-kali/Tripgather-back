package org.wcs.tripgather.service;


import org.springframework.stereotype.Service;
import org.wcs.tripgather.exception.UserNotFoundException;
import org.wcs.tripgather.mapper.EventMapper;
import org.wcs.tripgather.model.*;
import org.wcs.tripgather.repository.*;
import org.wcs.tripgather.dto.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public EventService(
            EventRepository eventRepository,
            EventMapper eventMapper,
            UserRepository userRepository,
            CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }


    public EventDTO getEventById(long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le voyage avec l'id " + id + " n'a pas été trouvé"));
        return eventMapper.convertToDTO(event);
    }


    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventMapper.convertToEntity(eventDTO);
        if (eventDTO.getOwner() != null && eventDTO.getOwner().getId() != null) {
            User owner = userRepository.findById(eventDTO.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("L'utilisateur avec l'id " + eventDTO.getOwner().getId() + " n'a pas été trouvé"));
            event.setOwner(owner);
        }
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
        if (eventDTO.getMaxParticipant() != null) eventToUpdate.setMaxParticipant(eventDTO.getMaxParticipant());
        if (eventDTO.getGender() != null) eventToUpdate.setGender(eventDTO.getGender());
        if (eventDTO.getImgUrl() != null) eventToUpdate.setImgUrl(eventDTO.getImgUrl());
        if (eventDTO.getOwner() != null && eventDTO.getOwner().getId() != null) {
            User owner = userRepository.findById(eventDTO.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("L'utilisateur avec l'id " + eventDTO.getOwner().getId() + " n'a pas été trouvé"));
            eventToUpdate.setOwner(owner);
        }
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
        return true;
    }

    public List<EventDTO> getFilteredEvents(String localisation, String genderStr,String title, String fromDate, String toDate) {
        Gender gender = genderStr != null ? Gender.valueOf(genderStr) : null;
        List<Event> filteredEvents = eventRepository.findFilteredEvents(localisation, gender,title, fromDate, toDate);
        return filteredEvents.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO addMemberToEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Événement non trouvé"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if (!event.getMembers().contains(user)) {
            event.getMembers().add(user);
            eventRepository.save(event);
        }

        return eventMapper.convertToDTO(event);
    }
}


