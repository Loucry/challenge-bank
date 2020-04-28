package com.naranja.transactions.dto;

import com.naranja.transactions.models.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseModelDTO implements Serializable {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BaseModelDTO(BaseModel baseModel) {
        this.id = baseModel.getId();
        this.createdAt = baseModel.getCreatedAt();
        this.updatedAt = baseModel.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
