package com.since.whellsurf.dto;

import lombok.Data;

import javax.persistence.Entity;


@Data
public class User {

    private String openId;

    private String nickname;

    private String address;

    private String headImgUrl;

    private String gender;

    private Integer status;
}

