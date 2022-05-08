package com.arusha.entrancetask.controller;

import com.arusha.entrancetask.entity.Document;
import com.arusha.entrancetask.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    DocumentService service;

    @GetMapping
    public Flux<Document> getAllDocuments() {
        return service.getAllDocuments();
    }

    @GetMapping("/{id}")
    public Mono<Document> getDocumentById(@PathVariable("id") Long id) {
        return service.getDocumentById(id);
    }

    @PostMapping
    public Mono<Document> saveOrUpdateDocument(@RequestBody Document document) {
        return service.saveOrUpdateDocument(document);
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteDocumentById(@PathVariable Long id){
        return service.removeDocumentById(id);
    }
}
