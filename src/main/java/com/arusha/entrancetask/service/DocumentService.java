package com.arusha.entrancetask.service;

import com.arusha.entrancetask.entity.Document;
import com.arusha.entrancetask.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;


@Service
public class DocumentService {

    @Autowired
    DocumentRepository repository;

    public Flux<Document> getAllDocuments() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return repository.findAll(sort).switchIfEmpty(Flux.empty());
    }

    public Mono<Document> getDocumentById(Long id) {
        return repository.findById(id).switchIfEmpty(Mono.empty());
    }

    @Transactional
    public Mono<Document> saveOrUpdateDocument(Document document) {

        return repository.findById(document.getId())
                .flatMap(p -> {
                    p.setName(document.getName());
                    p.setUpdatedAt(Instant.now());
                    return repository.save(p);
                })
                .switchIfEmpty(repository.save(new Document(document.getName(), Instant.now(), null)));
    }


    @Transactional
    public Mono<String> removeDocumentById(Long id) {

        return repository.findById(id)
                .flatMap(p -> {
                    repository.deleteById(p.getId()).subscribe();
                    return Mono.just(String.format("Document Item with ID %s removed!", id));
                })
                .switchIfEmpty(Mono.just(String.format("Document Item with ID %s NOT FOUND!", id)));
    }


}
