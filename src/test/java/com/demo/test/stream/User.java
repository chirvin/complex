package com.demo.test.stream;

import lombok.Data;

/**
 * @author scc
 * @date 2020/5/29 15:39
 */
@Data
public class User {

    private String name;
    private Integer age;
    private Integer score;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
}
