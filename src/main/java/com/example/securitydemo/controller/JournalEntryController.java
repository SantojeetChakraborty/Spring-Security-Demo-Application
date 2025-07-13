package com.example.securitydemo.controller;

import com.example.securitydemo.models.Journal;
import com.example.securitydemo.models.User;
import com.example.securitydemo.service.JournalService;
import com.example.securitydemo.service.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<List<Journal>> getAllJournalByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<Journal> journalList = user.getJournalist();
        if(journalList != null && !journalList.isEmpty()){
            return new ResponseEntity<>(journalList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Journal> createJournal(@RequestBody Journal journal,@PathVariable String username){
        journalService.saveJournal(journal,username);
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

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId,@PathVariable String username){
        journalService.deleteJournal(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,@RequestBody Journal newJournal,@PathVariable String username){
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
