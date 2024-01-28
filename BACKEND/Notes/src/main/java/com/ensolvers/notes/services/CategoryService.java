package com.ensolvers.notes.services;

import com.ensolvers.notes.controllers.dto.ApiResponse;
import com.ensolvers.notes.controllers.dto.CategoryRequest;
import com.ensolvers.notes.models.Category;
import com.ensolvers.notes.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

        public Page<Category> getAll(Integer nroPage, Integer regXPage, String order){
            Pageable sortedByOrderDesc =
                    PageRequest.of(nroPage, regXPage, Sort.by(order).descending());

            return categoryRepository.findAllByActiveOrderById(sortedByOrderDesc, "1");
        }

    public ApiResponse<Category> createCategory(CategoryRequest request){
        Category category = new Category();
        category.name = request.getName();
        category.color = request.getColor();
        category.active = "1";
        Category rs = categoryRepository.save(category);

        return new ApiResponse<Category>("OK", HttpStatus.CREATED.value(), "Operación exitosa", rs);
    }

    public ApiResponse<Category> editCategory(Long id, CategoryRequest request){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category obj = category.get();
            obj.name = request.getName();
            obj.color = request.getColor();
            Category rs =  categoryRepository.save(obj);
            return new ApiResponse<Category>("OK", HttpStatus.OK.value(), "Operación exitosa", rs);
        }
        return new ApiResponse<Category>("OK", HttpStatus.OK.value(), "No existe el código enviadp", null);
    }

    public ApiResponse<Category> deleteCategory(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category obj = category.get();
            obj.active = "0";
            Category rs =  categoryRepository.save(obj);
            return new ApiResponse<Category>("OK", HttpStatus.OK.value(), "Operación exitosa", rs);
        }
        return new ApiResponse<Category>("OK", HttpStatus.OK.value(), "Operación sin exitosa", null);
    }

}
