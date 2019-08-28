package com.example.demo.domain.po;

import com.example.demo.util.jpa.BaseMysqlPojo;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = "workshop",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}, name = "uk_name")
    }
)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Workshop extends BaseMysqlPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 31)
    private String name;

    @Type(type = "json")
    @Column(name = "workers", columnDefinition = "json")
    private List<Long> workers = new ArrayList<>();
}
