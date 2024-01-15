package com.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class Student {
    private static Long averageID = 1l;
    private Long id;
    private String name;
    private String groupName;


    public Student(String name, String groupName) {
        this.id = averageID++;
        this.name = name;
        this.groupName = groupName;
    }
}
