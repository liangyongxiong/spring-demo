package com.example.demo.domain.po;

import com.example.demo.util.jpa.BaseMysqlPojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = "worker",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}, name = "uk_name")
    }
)
public class Worker extends BaseMysqlPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 31)
    private String name;

    @Column(nullable = false)
    private Integer age = 0;

    @Column(nullable = false)
    private Long workshop;
}
