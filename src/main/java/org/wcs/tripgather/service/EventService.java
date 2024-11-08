package org.wcs.tripgather.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wcs.tripgather.mapper.EventMapper;
import org.wcs.tripgather.repository.CategoryRepository;
import org.wcs.tripgather.repository.EventRepository;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public EventService(
            EventRepository eventRepository,
            EventMapper eventMapper,
            CategoryRepository categoryRepository
            ) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.categoryRepository = categoryRepository;
    }

}

