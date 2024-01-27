package com.ensolvers.notes.controllers;


import com.ensolvers.notes.controllers.dto.ApiPaginateResponse;
import com.ensolvers.notes.controllers.dto.ApiResponse;
import com.ensolvers.notes.controllers.dto.NoteRequest;
import com.ensolvers.notes.models.Note;
import com.ensolvers.notes.services.NoteService;
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
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping("/note")
    public ResponseEntity<ApiPaginateResponse<Note>> index(
            @RequestParam("nroPage") Integer nroPage,
            @RequestParam("regXPage") Integer regXPage,
            @RequestParam("order") String order
    ){
        Page<Note> page = noteService.getAll(nroPage, regXPage, order);
        List<Note> rs = page.getContent();
        return new ResponseEntity<>(new ApiPaginateResponse<>("OK", HttpStatus.OK.value(), "Exito",
                rs, page.getTotalElements(), page.getTotalPages()), HttpStatus.OK);
    }

    @GetMapping("/listArchived")
    public ResponseEntity<ApiPaginateResponse<Note>> Listarchived(
            @RequestParam("nroPage") Integer nroPage,
            @RequestParam("regXPage") Integer regXPage,
            @RequestParam("order") String order
    ){
        Page<Note> page = noteService.getAllArchived(nroPage, regXPage, order);
        List<Note> rs = page.getContent();
        return new ResponseEntity<>(new ApiPaginateResponse<>("OK", HttpStatus.OK.value(), "Exito",
                rs, page.getTotalElements(), page.getTotalPages()), HttpStatus.OK);
    }


    @PostMapping("/note")
    public  ResponseEntity<ApiResponse<Note>> create(@RequestBody NoteRequest request){
        ApiResponse<Note> note = noteService.createNote(request);
        return new ResponseEntity(note, HttpStatus.OK);
    }

    @PutMapping("/note/{id}")
    public  ResponseEntity<ApiResponse<Note>> edit(@PathVariable Long id, @RequestBody NoteRequest request){
        ApiResponse<Note> note = noteService.editNote(id, request);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<ApiResponse<Note>> delete(@PathVariable Long id){
        ApiResponse<Note> note = noteService.deleteNote(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PutMapping("/note/archived/{id}")
    public ResponseEntity<ApiResponse<Note>> archived(@PathVariable Long id){
        ApiResponse<Note> note = noteService.archived(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PutMapping("/note/dearchived/{id}")
    public ResponseEntity<ApiResponse<Note>> dearchived(@PathVariable Long id){
        ApiResponse<Note> note = noteService.dearchived(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

}

