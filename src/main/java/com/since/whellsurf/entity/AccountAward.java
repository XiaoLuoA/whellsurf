package com.since.whellsurf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne
    @JoinColumn(name = "award_id")
    private Award award;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Override
    public String toString() {
        return "AccountAward{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", awardName='" + awardName + '\'' +
                ", status=" + status +
                ", awardCode='" + awardCode + '\'' +
                '}';
    }
}
