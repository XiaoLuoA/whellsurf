package com.since.whellsurf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author drj
 */
@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    private String title;

    private String details;

    private Integer status;

    private String text;

    private String image;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="shop_id",referencedColumnName="id")
    private Shop shop;


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
