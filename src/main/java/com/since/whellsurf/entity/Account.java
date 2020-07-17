package com.since.whellsurf.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
    @Data
    @Table(name = "account")
@ToString
    public class Account {

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

}
