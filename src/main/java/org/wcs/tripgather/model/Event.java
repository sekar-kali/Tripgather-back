package org.wcs.tripgather.model;

import jakarta.persistence.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private LocalDate startRegistration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate endRegistration;

    @Column(nullable = false)
    private LocalDate fromDate;

    @Column(nullable = false)
    private LocalDate toDate;

    @Column(nullable = false)
    private Integer maxParticipant;

    @Column(nullable = false)
    private Boolean isMixte;

    @Column(nullable = false)
    private String price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer owner;

    @ElementCollection
    private List<String> imgUrl;

    @ManyToMany
    @JoinTable(
            name = "event_category",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;



    //GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public LocalDate getStartRegistration() { return startRegistration; }
    public void setStartRegistration(LocalDate startRegistration) { this.startRegistration = startRegistration; }

    public LocalDate getEndRegistration() { return endRegistration; }
    public void setEndRegistration(LocalDate endRegistration) { this.endRegistration = endRegistration; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public Integer getMaxParticipant() { return maxParticipant; }
    public void setMaxParticipant(Integer maxParticipant) { this.maxParticipant = maxParticipant; }

    public Boolean isMixte() { return isMixte; }
    public void setMixte(Boolean mixte) { isMixte = mixte; }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Integer getOwner() { return owner; }
    public void setOwner(Integer owner) { this.owner = owner; }

    public List<String> getImgUrl() { return imgUrl; }
    public void setImgUrl(List<String> imgUrl) { this.imgUrl = imgUrl; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

}