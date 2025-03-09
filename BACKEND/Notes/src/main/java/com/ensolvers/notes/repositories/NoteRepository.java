package com.ensolvers.notes.repositories;

import com.ensolvers.notes.models.Category;
import com.ensolvers.notes.models.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Page<Note> findAllByActiveAndFlagArchivedOrderById(Pageable pageable, String active, String flagArchived);

    @Query("SELECT c FROM Category c JOIN c.notes n WHERE n.id = :noteId")
    List<Category> findCategoriesByNoteId(@Param("noteId") Long noteId);
}
