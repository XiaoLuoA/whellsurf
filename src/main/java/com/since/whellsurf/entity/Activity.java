package com.since.whellsurf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private List<AccountAward> accountAwards;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private List<Award> awards;


}
