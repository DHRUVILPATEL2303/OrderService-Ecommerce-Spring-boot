package com.example.OrderService_Ecommerce_Spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Instant created_at;


    @LastModifiedDate
    @Column(nullable = false)
    private Instant updated_at;


    @PrePersist
    public void onCreate(){
        Instant now= Instant.now();
        this.created_at=now;
        this.updated_at=now;
    }


    @PreUpdate

    public void onUpdate(){
        this.updated_at=Instant.now();
    }
}
