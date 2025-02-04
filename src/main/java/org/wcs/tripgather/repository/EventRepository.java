package org.wcs.tripgather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wcs.tripgather.model.Event;
import org.wcs.tripgather.model.Gender;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocalisation(String localisation);
    long countByCategoriesId(Long categoryId);


    @Query("SELECT e FROM Event e WHERE " +
            "(:localisation IS NULL OR LOWER(e.localisation) LIKE LOWER(CONCAT(:localisation, '%'))) " +
            "AND (:gender IS NULL OR e.gender = :gender) " +
            "AND (:fromDate IS NULL OR e.fromDate >= :fromDate) " +
            "AND (:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT(:title, '%'))) " +
            "AND (:toDate IS NULL OR e.toDate <= :toDate)")
    List<Event> findFilteredEvents(
            @Param("localisation") String localisation,
            @Param("gender") Gender gender,
            @Param("title") String title,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate
    );
}

