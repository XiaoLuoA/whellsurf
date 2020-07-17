package com.since.whellsurf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "award")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int probability;

    private Integer status;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Override
    public String toString() {
        return "Award{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", probability=" + probability +
                ", status=" + status +
                '}';
    }
}
