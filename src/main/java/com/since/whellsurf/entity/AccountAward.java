package com.since.whellsurf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "account_award")
@Data
@NoArgsConstructor
public class AccountAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "openid")
    private String openId;
    @Column(name = "head_img_url")
    private String headImgUrl;
    @Column(name = "award_name")
    private String awardName;
    private Integer status;
    @Column(name = "award_code")
    private String awardCode;


    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "award_id")
    private Award award;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;



}
