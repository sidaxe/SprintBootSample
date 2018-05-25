package com.ephesoft.ephesoftCloudClient.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Page")
public class Page  extends ParentModel implements Serializable {

    public int id;
    public int pageNo;
    public String data;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "pageIndex" , nullable = false)
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Column(name = "date")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", pageNo=" + pageNo +
                ", data='" + data + '\'' +
                '}';
    }
}
