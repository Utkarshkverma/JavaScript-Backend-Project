package com.vermau2k01.engineering_digest.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vermau2k01.engineering_digest.entity.JournalEntry;
import com.vermau2k01.engineering_digest.repository.JournalEntryRepository;
import java.time.LocalDateTime;
import java.util.*;



@Service
public class JournalService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    

     public JournalEntry saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
          return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id)
    {
        journalEntryRepository.deleteById(id);
    }

}
