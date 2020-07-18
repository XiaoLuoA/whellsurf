package com.since.whellsurf.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "shop")
@NoArgsConstructor
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


}


