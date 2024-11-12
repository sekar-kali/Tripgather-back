package org.wcs.tripgather.dto;

import java.util.List;


public class CategoryDTO {


    private Long id;
    private String name;
    private List<EventDTO> events;
    public String img;


    //Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<EventDTO> getEvents() { return events; }
    public void setEvents(List<EventDTO> eventTitle) { this.events = eventTitle; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

}
