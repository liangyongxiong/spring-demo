package com.example.demo.domain.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Hobby {
    @Field(value = "name")
    private String name;
}
