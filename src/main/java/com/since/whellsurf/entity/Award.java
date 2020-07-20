package com.since.whellsurf.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "award")
@ToString
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    private int probability;

    @JsonIgnore
    private Integer status;

    @Column(name = "activity_id")
    private Long activityId;


    @Transient
    String awardCode;



//    @ManyToOne
//    @JoinColumn(name = "activity_id")
//    private Activity activity;
}
