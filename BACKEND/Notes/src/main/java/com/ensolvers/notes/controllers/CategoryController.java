package com.ensolvers.notes.controllers;
import com.ensolvers.notes.controllers.dto.ApiPaginateResponse;
import com.ensolvers.notes.controllers.dto.ApiResponse;
import com.ensolvers.notes.controllers.dto.CategoryRequest;
import com.ensolvers.notes.controllers.dto.NoteRequest;
import com.ensolvers.notes.models.Category;
import com.ensolvers.notes.models.Note;
import com.ensolvers.notes.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( "/api/notes/v1")

    public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<ApiPaginateResponse<Category>> index(
            @RequestParam("nroPage") Integer nroPage,
            @RequestParam("regXPage") Integer regXPage,
            @RequestParam("order") String order
    ){
        Page<Category> page = categoryService.getAll(nroPage, regXPage, order);
        List<Category> rs = page.getContent();
        return new ResponseEntity<>(new ApiPaginateResponse<>("OK", HttpStatus.OK.value(), "Exito",
                rs, page.getTotalElements(), page.getTotalPages()), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<Category>> create(@RequestBody CategoryRequest request) {
        ApiResponse<Category> category = categoryService.createCategory(request);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @PutMapping("/category/{id}")
    public  ResponseEntity<ApiResponse<Category>> edit(@PathVariable Long id, @RequestBody CategoryRequest request){
        ApiResponse<Category> category = categoryService.editCategory(id, request);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse<Category>> delete(@PathVariable Long id){
        ApiResponse<Category> category = categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}


