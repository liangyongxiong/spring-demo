package com.example.demo.domain.po;

import com.example.demo.util.jpa.BaseMongoPojo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@QueryEntity
@Document(collection = "union")
public class Union extends BaseMongoPojo {

    @Field(value = "name")
    @Indexed(unique = true)
    private String name;

    @Field(value = "users")
    @JsonSerialize(using = ToStringSerializer.class)
    private List<ObjectId> users;
}