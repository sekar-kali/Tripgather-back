package org.wcs.tripgather.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CategoryDTO {


    private Long id;
    private String img;
    private String color;
    private Long eventCount;


    @NotBlank(message = "Le nom de la categorie ne peut pas être vide")
    @Size(min = 2, message = "Le nom est trop court (2 caractères ou plus)")
    private String name;

    //Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Long getEventCount() { return eventCount; }
    public void setEventCount(Long eventCount) { this.eventCount = eventCount; }

    public @NotBlank(message = "Le nom de la catégorie ne peux pas être vide")
        @Size(min = 2, message = "Le nom est trop court (2 caractères ou plus)")
        String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom de la catégorie ne peux pas être vide")
        @Size(min = 2, message = "Le nom est trop court (2 caractères ou plus)")
        String name) {
        this.name = name;
    }

}
