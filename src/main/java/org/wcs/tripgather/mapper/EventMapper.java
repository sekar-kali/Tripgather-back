package org.wcs.tripgather.mapper;

import org.springframework.stereotype.Component;
import org.wcs.tripgather.dto.EventDTO;
import org.wcs.tripgather.model.Event;

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
        eventDTO.setCategories(event.getCategories());

        return eventDTO;

        public Article convertToEntity(ArticleCreateDTO articleCreateDTO) {
            Article article = new Article();
            article.setTitle(articleCreateDTO.getTitle());
            article.setContent(articleCreateDTO.getContent());

            return article;
        }

        public Event convertToEntity(EventDTO eventDTO)

}
