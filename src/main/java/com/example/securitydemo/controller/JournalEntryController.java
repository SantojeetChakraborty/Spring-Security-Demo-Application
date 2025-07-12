package com.example.securitydemo.controller;

import com.example.securitydemo.models.Journal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @GetMapping
    public List<Journal> getAllJournal(){
        return null;
    }

    @PostMapping
    public boolean createJournal(@RequestBody Journal journal){
        return true;
    }

    @GetMapping("id/{myId}")
    public Journal getJournalById(@PathVariable Long myId){
        return null;
    }

    @DeleteMapping("id/{myId}")
    public Journal deleteJournalById(@PathVariable Long myId){
        return null;
    }

    @PutMapping("id/{myId}")
    public Journal updateJournalById(@PathVariable Long id,@RequestBody Journal journal){
        return null;
    }

}
