package org.wcs.tripgather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcs.tripgather.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocalisation(String localisation);


}
