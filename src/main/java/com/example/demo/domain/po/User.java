package com.example.demo.domain.po;

import com.example.demo.util.jpa.BaseMongoPojo;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@QueryEntity
@Document(collection = "user")
public class User extends BaseMongoPojo {

    @Field(value = "name")
    @Indexed(unique = true)
    private String name;

    @Field(value = "age")
    private Integer age = 0;

    @Field(value = "hobby")
    private List<Hobby> hobbies = new ArrayList<>();

    @Field(value = "union")
    private ObjectId union;
}