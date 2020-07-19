package com.since.whellsurf.dto;


import lombok.Data;

import javax.persistence.Entity;

/**
 * @author wyh
 */
@Data
public class CheckAwardParameter {

    private String headImgUrl;

    private String awardName;

    private Integer status;
}
