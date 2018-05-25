package com.ephesoft.ephesoftCloudClient.controller;

import com.ephesoft.ephesoftCloudClient.Exception.ResourceNotFoundException;
import com.ephesoft.ephesoftCloudClient.repository.DocumentRepository;
import com.ephesoft.ephesoftCloudClient.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/document")
public class DocumentController {


    @Value("${env}")
    String environment;

    @Autowired
    DocumentRepository documentRepository;

    /**
      Create a new Document
     */
    @PostMapping("/")
    public Document addDocument(@Valid @RequestBody Document document) {
        return documentRepository.save(document);
    }


    /*
     Get a single Document by its id
     */
    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable(value = "id") Integer documentId) {
        Optional<Document> oldDocument =  documentRepository.findById(documentId);
        if(!oldDocument.isPresent()){
            throw new ResourceNotFoundException(" Could not find document with id = " + documentId);
        }
        return  oldDocument.get();
    }

    /*
    return all documents in database
     */
    @GetMapping("/getAll")
    public List<Document> getAllDocument(){
        return documentRepository.findAll();
    }
    /*
    update a singe document given its id
     */
    @PutMapping("/{id}")
    public Document updateNote(@PathVariable(value = "id") Integer documentId,
                           @Valid @RequestBody Document newDocument) {

        Document oldDocument = documentRepository.findById(documentId).get();
        if(oldDocument == null){
            throw new ResourceNotFoundException(" Could not find document with id = " + documentId);
        }
       oldDocument.setDocumentDetail(newDocument.getDocumentDetail());
       oldDocument.setPage(newDocument.getPage());
       oldDocument.setDocumentName(newDocument.getDocumentName());
        newDocument = documentRepository.save(oldDocument);
        return newDocument;
    }

    /*
     delete a single document
     */
    @DeleteMapping("{id}")
    public Document deleteDocument( @PathVariable ( value = "id" ) Integer documentId){
        Document oldDocument = documentRepository.findById(documentId).get();
        if(oldDocument == null){
            throw new ResourceNotFoundException(" Could not find document with id = " + documentId);
        }
        documentRepository.delete(oldDocument);
        return  oldDocument;
    }

    @GetMapping("/getEnv")
    public String getEnvironment(){
        return environment;
    }
}
