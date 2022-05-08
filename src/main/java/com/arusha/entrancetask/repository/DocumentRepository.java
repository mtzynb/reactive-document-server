package com.arusha.entrancetask.repository;

import com.arusha.entrancetask.entity.Document;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends ReactiveSortingRepository<Document, Long> {
}
