package com.ephesoft.ephesoftCloudClient.Model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DocumentDetail")
public class DocumentDetail extends ParentModel implements Serializable  {

    public int id;
    public int noOfPages;
    public String documentType;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_detail_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "pageCount" , nullable = false)
    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    @Column(name = "documentType")
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }


    @Override
    public String toString() {
        return "DocumentDetail{" +
                "id=" + id +
                ", noOfPages=" + noOfPages +
                ", documentType='" + documentType + '\'' +
                '}';
    }
}
