package com.avalon.sahelloserviceapi.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author jwwang on 2018/9/12
 */
@Data
public class User implements Serializable {
    private String name;
    private Integer age;

    public User(String name, Integer age){
        this.name = name;
        this.age  = age;
    }
}
