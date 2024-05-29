package com.xmemes.xmemes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmemes.xmemes.entity.Memes;
import com.xmemes.xmemes.repository.MemesRepository;

@Component
public class XmemesService {

    @Autowired
    private MemesRepository memesRepository;

    public List<Memes> getMemes()
    {
        List<Memes> list = memesRepository.findAll();
        if(list.size()>100)
        {
           return list.size() > 100? list.stream().sorted((m1, m2) -> m2.getUploadDateTime().compareTo(m1.getUploadDateTime())).limit(100).collect(Collectors.toList()) : list;
        }

        return list;
    }

    public String addMemes(Memes meme)
    {
        if (memesRepository.findByUrlAndCaption(meme.getUrl(), meme.getCaption()) == true) {
        return "meme already exists";
    }
    else if(meme == null || meme.getCaption() == null || meme.getUrl() == null || meme.getName()==null)
    return "empty field";

    meme.setUploadDateTime(LocalDateTime.now());
    memesRepository.save(meme);
    return "meme added successfully";
    }

    public Optional<Memes> getMemeById(String id)
    {
        return memesRepository.findById(id);
    }



}
