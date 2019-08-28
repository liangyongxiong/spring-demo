package com.example.demo.util.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseMysqlPojo {

    @Column(name = "is_deleted")
    private Integer isDeleted = 0;

    @Column(name = "created_at", precision = 6)     // 精确到微秒
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", precision = 6)     // 精确到微秒
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date updatedAt;
}
