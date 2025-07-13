package com.example.securitydemo.service;

import com.example.securitydemo.models.Journal;
import com.example.securitydemo.models.User;
import com.example.securitydemo.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    public void saveJournal(Journal journal, String username){
        User user = userService.findByUsername(username);
        journal.setDate(new Date());
        Journal saved = journalRepository.save(journal);
        user.getJournalist().add(saved);
        userService.saveUser(user);
    }

    public void saveJournal(Journal journal){
        journalRepository.save(journal);
    }

    public List<Journal> getAllJournals(){
        return journalRepository.findAll();
    }
    
    public Optional<Journal> getJournalById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteJournal(ObjectId id, String username){
        User user = userService.findByUsername(username);
        user.getJournalist().removeIf(x->x.getId().equals(id));
        userService.saveUser(user);
        journalRepository.deleteById(id);
    }

    public Journal updateJournal(ObjectId id, Journal journal){
        journalRepository.save(journal);
        return journal;
    }
}
