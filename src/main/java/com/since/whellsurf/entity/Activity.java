package com.since.whellsurf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "activity")
    private List<AccountAward> accountAwards=new ArrayList<>();

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
