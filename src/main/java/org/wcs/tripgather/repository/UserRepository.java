package org.wcs.tripgather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcs.tripgather.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
