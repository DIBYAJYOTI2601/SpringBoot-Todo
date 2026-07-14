package com.enterprise.todo.modules.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseAuditEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @PrePersist
    protected void onCreate() {
        long currentMillis = System.currentTimeMillis();
        this.createdAt = currentMillis;
        this.updatedAt = currentMillis;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}