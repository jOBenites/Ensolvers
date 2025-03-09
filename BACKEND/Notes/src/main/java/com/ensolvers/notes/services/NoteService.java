package com.ensolvers.notes.services;

import com.ensolvers.notes.controllers.dto.ApiResponse;
import com.ensolvers.notes.controllers.dto.CategoryResponse;
import com.ensolvers.notes.controllers.dto.NoteRequest;
import com.ensolvers.notes.models.Category;
import com.ensolvers.notes.models.Note;
import com.ensolvers.notes.repositories.CategoryRepository;
import com.ensolvers.notes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    public Page<Note> getAll(Integer nroPage, Integer regXPage, String order){
        Pageable sortedByOrderDesc =
                PageRequest.of(nroPage, regXPage, Sort.by(order).descending());

        return noteRepository.findAllByActiveAndFlagArchivedOrderById(sortedByOrderDesc, "1", "0");
    }

    public Page<Note> getAllArchived(Integer nroPage, Integer regXPage, String order){
        Pageable sortedByOrderDesc =
                PageRequest.of(nroPage, regXPage, Sort.by(order).descending());

        return noteRepository.findAllByActiveAndFlagArchivedOrderById(sortedByOrderDesc, "1", "1");
    }

    public List<CategoryResponse> getAllCategoriesAsignedByNote(Long noteId){
        Page<Category> categoriesPage =  categoryService.getAll(0, 100, "id");
        List<Category> categories = categoriesPage.getContent();

        List<Category> categorieByNote = noteRepository.findCategoriesByNoteId(noteId);

        List<CategoryResponse> list = new ArrayList<>();
        categories.forEach((category) -> {
            AtomicReference<Integer> flagAsigned = new AtomicReference<>(0);
            categorieByNote.forEach((category1) -> {
                if(category.getId().equals(category1.getId())) {
                    flagAsigned.set(1);
                }
            });

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.id);
            categoryResponse.setNombre(category.getName());
            categoryResponse.setAsigned(flagAsigned.get());
            list.add(categoryResponse);
        });

        return list;
    }

    public ApiResponse<Note> createNote(NoteRequest request){
        Note note = new Note();
        note.title = request.getTitle();
        note.description = request.getDescription();
        note.active = "1";
        note.flagArchived = "0";
        Note rs = noteRepository.save(note);
        return new ApiResponse<Note>("OK", HttpStatus.CREATED.value(), "Operación exitosa", rs);
    }

    public ApiResponse<Note> editNote(Long id, NoteRequest request){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isPresent()){
            Note obj = note.get();
            obj.title = request.getTitle();
            obj.description = request.getDescription();
            Note rs= noteRepository.save(obj);
            return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación exitosa", rs);
        }
        return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación sin exitox", null);
    }

    public ApiResponse<Note> deleteNote(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isPresent()){
            Note obj = note.get();
            obj.active = "0";
            Note rs= noteRepository.save(obj);
            return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación exitosa", rs);
        }
        return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación sin exito", null);
    }

    public ApiResponse<Note> archived(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isPresent()){
            Note obj = note.get();
            obj.flagArchived = "1";
            Note rs= noteRepository.save(obj);
            return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación exitosa", rs);
        }
        return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación sin exito", null);
    }

    public ApiResponse<Note> dearchived(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isPresent()){
            Note obj = note.get();
            obj.flagArchived = "0";
            Note rs= noteRepository.save(obj);
            return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación exitosa", rs);
        }
        return new ApiResponse<Note>("OK", HttpStatus.OK.value(), "Operación sin exito", null);
    }

    @Transactional
    public void assignCategoriesToNote(Long noteId, List<CategoryResponse> categoryAssignments) {
        // Fetch the note by ID
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // Clear existing categories (optional, depending on your use case)
        note.getCategories().clear();

        // Iterate through the category assignments
        for (CategoryResponse assignment : categoryAssignments) {
            if (assignment.getAsigned() == 1) {
                // Fetch the category by ID
                Category category = categoryRepository.findById(assignment.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));

                // Add the category to the note
                note.getCategories().add(category);
            }
        }

        // Save the updated note
        noteRepository.save(note);
    }

}
