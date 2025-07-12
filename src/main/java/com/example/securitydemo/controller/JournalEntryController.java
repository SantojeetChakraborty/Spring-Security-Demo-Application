package com.example.securitydemo.controller;

import com.example.securitydemo.models.Journal;
import com.example.securitydemo.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournal(){
        List<Journal> journalList = journalService.getAllJournals();
        if(journalList != null && !journalList.isEmpty()){
            return new ResponseEntity<>(journalList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody Journal journal){
        journalService.saveJournal(journal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<Journal> getJournalById(@PathVariable ObjectId myId){
        Optional<Journal> journalById = journalService.getJournalById(myId);
        if(journalById.isPresent()){
            return new ResponseEntity<>(journalById.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId){
        journalService.deleteJournal(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,@RequestBody Journal newJournal){
        Journal oldJournal = journalService.getJournalById(myId).orElse(null);
        if(oldJournal != null){
            oldJournal.setTitle(newJournal.getTitle() !=null && !newJournal.getTitle().equals("") ? newJournal.getTitle() : oldJournal.getTitle());
            oldJournal.setContent(newJournal.getContent() !=null && !newJournal.getContent().equals("") ? newJournal.getContent() : oldJournal.getContent());
            journalService.saveJournal(oldJournal);
            return new ResponseEntity<>(oldJournal,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
