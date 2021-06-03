package org.jeecg.modules.demo.checked.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
