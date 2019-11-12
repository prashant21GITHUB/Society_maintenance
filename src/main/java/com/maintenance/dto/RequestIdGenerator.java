package com.maintenance.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bajpai
 */
@Entity
@Table(name = "IdGenerator")
public class RequestIdGenerator {
    
    @Id
    private int id;
    private String date;
    private Integer counter;

    public String getData() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

   
}
