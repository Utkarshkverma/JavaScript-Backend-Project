package com.xmemes.xmemes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xmemes.xmemes.entity.Memes;

public interface MemesRepository extends MongoRepository<Memes,String>{

    boolean findByUrlAndCaption(String url, String caption);

}
