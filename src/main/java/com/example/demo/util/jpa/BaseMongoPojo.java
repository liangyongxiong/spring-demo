package com.example.demo.util.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseMongoPojo {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value = "is_deleted")
    private Integer isDeleted = 0;

    @Field(value = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createdAt;

    @Field(value = "updated_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date updatedAt;
}
