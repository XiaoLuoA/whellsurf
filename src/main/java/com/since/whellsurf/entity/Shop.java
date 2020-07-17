package com.since.whellsurf.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
    @Data
    @Table(name = "shop")
    public class Shop {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "openid")
        private String openId;

        private String nickname;

        private String address;

        @Column(name = "head_img_url")
        private String headImgUrl;

        private String gender;

        private Integer status;

        @JsonIgnore
        @OneToOne(mappedBy = "shop")
        private Activity activity;

        @Override
        public String toString() {
            return "Shop{" +
                    "id=" + id +
                    ", openId='" + openId + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", address='" + address + '\'' +
                    ", headImgUrl='" + headImgUrl + '\'' +
                    ", gender='" + gender + '\'' +
                    ", status=" + status +
                    '}';
        }

    public Shop() {
    }
}


