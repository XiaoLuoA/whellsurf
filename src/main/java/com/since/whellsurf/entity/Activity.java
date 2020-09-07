package com.since.whellsurf.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * @author drj
 */
@Entity
@Data
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String details;

    private Integer status;

    private String text;

    private String image;

    @Transient
    private int participates;

    @Column(name = "shop_id")
    private Long shopId;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private List<AccountAward> accountAwards;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private List<Award> awards;
}
