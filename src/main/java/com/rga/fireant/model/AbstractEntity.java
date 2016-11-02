package com.rga.fireant.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.rga.fireant.LocalDateTimeAttributeConverter;

@SuppressWarnings("serial")
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class AbstractEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    private Timestamp updatedAt;

    @Column(nullable = true)
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getUpdatedAt() {
        return new LocalDateTimeAttributeConverter().convertToEntityAttribute(updatedAt);
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = new LocalDateTimeAttributeConverter().convertToDatabaseColumn(updatedAt);
    }

    @PreUpdate
    @PrePersist
    protected void onModifiedDate() {
        this.setUpdatedAt(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "AbstractEntity [id=" + id + ", version=" + version + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy
                + "]";
    }

}
