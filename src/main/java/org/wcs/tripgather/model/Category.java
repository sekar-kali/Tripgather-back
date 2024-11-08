package org.wcs.tripgather.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private String img;

    @ManyToMany(mappedBy = "categories")
    private List<User> users;

    @ManyToMany(mappedBy = "categories")
    private List<Event> events;

    //GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
