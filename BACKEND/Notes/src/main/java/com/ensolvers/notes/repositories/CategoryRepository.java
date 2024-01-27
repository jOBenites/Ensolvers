package com.ensolvers.notes.repositories;

import com.ensolvers.notes.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long>{
}
