package com.ensolvers.notes.services;

import com.ensolvers.notes.controllers.dto.ApiResponse;
import com.ensolvers.notes.controllers.dto.NoteRequest;
import com.ensolvers.notes.models.Category;
import com.ensolvers.notes.models.Note;
import com.ensolvers.notes.repositories.NoteRepository;
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
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

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

    public ApiResponse<Note> createNote(NoteRequest request){
        Note note = new Note();
        note.title = request.getTitle();
        note.description = request.getDescription();
        Category category = new Category();
        category.id = request.getCategoryId();
        note.category = category;
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
            obj.category.id= request.getCategoryId();
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

}
