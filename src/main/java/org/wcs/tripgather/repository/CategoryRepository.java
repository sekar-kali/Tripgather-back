package org.wcs.tripgather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcs.tripgather.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
