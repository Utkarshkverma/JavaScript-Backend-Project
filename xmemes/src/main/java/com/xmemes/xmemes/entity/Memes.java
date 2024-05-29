package com.xmemes.xmemes.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "_memes")
public class Memes {
   
    @Id
    private String id;

    private String name;

    private String url;

    private String caption;

    private LocalDateTime uploadDateTime;
}
