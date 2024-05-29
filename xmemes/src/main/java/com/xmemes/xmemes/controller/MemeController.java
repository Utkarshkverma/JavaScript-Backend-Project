package com.xmemes.xmemes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmemes.xmemes.entity.Memes;
import com.xmemes.xmemes.service.XmemesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/memes")
public class MemeController {

    @Autowired
    private XmemesService memeService;

    @GetMapping
    public ResponseEntity<List<Memes>> getAll()
    {
        return new ResponseEntity<>(memeService.getMemes(),HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Memes> getMemeById(@PathVariable String id)
    {
        Optional<Memes> memesDto = memeService.getMemeById(id);
        if(memesDto.isPresent())
        return new ResponseEntity<>(memesDto.get(),HttpStatus.FOUND);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestBody Memes entity) {
        String isAdded = memeService.addMemes(entity);
        if(isAdded == "meme already exists")
        return new ResponseEntity<>("Meme already exists",HttpStatus.CONFLICT);
        else if(isAdded == "empty field")
        return new ResponseEntity<>("Missing Fields", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(isAdded,HttpStatus.CREATED);
    }
    


}
