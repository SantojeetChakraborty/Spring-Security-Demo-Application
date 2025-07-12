package com.example.securitydemo.service;

import com.example.securitydemo.models.Journal;
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

    public void saveJournal(Journal journal){
        journal.setDate(new Date());
        journalRepository.save(journal);
    }

    public List<Journal> getAllJournals(){
        return journalRepository.findAll();
    }
    
    public Optional<Journal> getJournalById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteJournal(ObjectId id){
        journalRepository.deleteById(id);
    }

    public Journal updateJournal(ObjectId id, Journal journal){
        journalRepository.save(journal);
        return journal;
    }
}
