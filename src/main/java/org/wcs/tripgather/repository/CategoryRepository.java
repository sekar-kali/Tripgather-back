package org.wcs.tripgather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcs.tripgather.dto.CategoryDTO;
import org.wcs.tripgather.model.Category;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
