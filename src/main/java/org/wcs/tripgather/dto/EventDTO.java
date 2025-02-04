package org.wcs.tripgather.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.wcs.tripgather.model.EventStatus;
import org.wcs.tripgather.model.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {

    private Long id;
    private String title;
    private EventStatus status;
    private LocalDateTime createdAt;
    private String localisation;
    private LocalDate startRegistration;
    private LocalDate endRegistration;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer maxParticipant;
    private Gender gender;
    private String price;
    private String description;
    private LocalDateTime updatedAt;
    private List<String> imgUrl;
    private List<CategoryDTO> categories;
    private UserDTO owner;
    private List<UserDTO> members;

    //Getters et Setters
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

    public List<String> getImgUrl() { return imgUrl; }
    public void setImgUrl(List<String> imgUrl) { this.imgUrl = imgUrl; }

    public List<CategoryDTO> getCategories() { return categories; }
    public void setCategories(List<CategoryDTO> categories) { this.categories = categories; }

    public UserDTO getOwner() { return owner; }
    public void setOwner(UserDTO owner) { this.owner = owner; }

    public List<UserDTO> getMembers() {
        return members;
    }

    public void setMembers(List<UserDTO> members) {
        this.members = members;
    }
}
