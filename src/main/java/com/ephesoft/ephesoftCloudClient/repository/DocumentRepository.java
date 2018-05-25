package com.ephesoft.ephesoftCloudClient.repository;

import com.ephesoft.ephesoftCloudClient.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document , Integer> {

}
