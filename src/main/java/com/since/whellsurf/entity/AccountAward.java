package com.since.whellsurf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account_award")
@Data
@NoArgsConstructor
public class AccountAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String openid;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "head_img_url")
    private String headImgUrl;

    @Column(name = "award_name")
    private String awardName;

    @Column(name = "award_id")
    private Long awardId;

    private Integer status;

    @Column(name = "award_code")
    private String awardCode;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "shop_id")
    private Long shopId;

}
