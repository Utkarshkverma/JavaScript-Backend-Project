package com.vermau2k01.engineering_digest.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vermau2k01.engineering_digest.entity.JournalEntry;
import com.vermau2k01.engineering_digest.service.JournalService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll()
    {
        return new ResponseEntity<>(journalService.getAll(),HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getMethodName(@PathVariable ObjectId id) {
        Optional<JournalEntry> jEntry =  journalService.findById(id);

        if(jEntry !=  null)
        return new ResponseEntity<>(jEntry.get(),HttpStatus.FOUND);

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry jEntry)
    {
        return new ResponseEntity<>(journalService.saveEntry(jEntry),HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJournals(@PathVariable ObjectId id)
    {
        try{
            journalService.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
    

}
