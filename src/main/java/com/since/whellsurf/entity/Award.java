package com.since.whellsurf.entity;
import lombok.Data;

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

    private int probability;

    private Integer status;

    @Column(name = "activity_id")
    private Long activityId;

//    @ManyToOne
//    @JoinColumn(name = "activity_id")
//    private Activity activity;
}
