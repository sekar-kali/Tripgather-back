package org.wcs.tripgather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wcs.tripgather.exception.EmailAlreadyInUseException;
import org.wcs.tripgather.exception.UserNotFoundException;
import org.wcs.tripgather.model.Gender;
import org.wcs.tripgather.model.User;
import org.wcs.tripgather.repository.UserRepository;

import java.util.Date;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User register(String email, String password, String bio, String country, String firstName,
                         Gender gender, String imageUrl, String lastName, Date birthDate) {

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setBio(bio);
        user.setCountry(country);
        user.setFirstName(firstName);
        user.setGender(gender);
        user.setImageUrl(imageUrl);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);

        return userRepository.save(user);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public User getUserByEmail(String email) {
        return findByEmail(email);
    }
}