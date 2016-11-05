package com.rga.fireant.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.rga.fireant.Application;
import com.rga.fireant.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "executions")
public class Execution extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "test_case_id")
    @JsonIgnore
    private TestCase testCase;

    private Timestamp executedAt;

    @Column(length = Application.TEXT_MAX_LENGTH)
    private String actualResult;

    private Outcome outcome;

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getExecutedAt() {
        return new LocalDateTimeAttributeConverter().convertToEntityAttribute(executedAt);
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = new LocalDateTimeAttributeConverter().convertToDatabaseColumn(executedAt);
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }
}
