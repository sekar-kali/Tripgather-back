package org.wcs.tripgather.dto;

import org.wcs.tripgather.model.Gender;


public class UserDTO {

    private Long id;
    private String fistName;
    private String email;
    private Gender gender;
    private String bio;
    private String country;


    //GETTERS ET SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFistName() { return fistName; }
    public void setFistName(String fistName) { this.fistName = fistName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
