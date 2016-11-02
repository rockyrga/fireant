package com.rga.fireant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import com.rga.fireant.Application;

@Entity
@Table(name = "cases")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.WRAPPER_OBJECT, property = "type")
@JsonIgnoreProperties({"new", "hibernateLazyInitializer", "handler"})
public class TestCase extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(length = Application.TEXT_MAX_LENGTH)
    private String scenario;

    @Column(length = Application.TEXT_MAX_LENGTH)
    private String step;

    @Column(length = Application.TEXT_MAX_LENGTH)
    private String expectedResult;

    @Column(length = Application.TEXT_MAX_LENGTH)
    private String testData;

    @Column(length = Application.TEXT_MAX_LENGTH)
    private String prerequisite;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "testCase", orphanRemoval = true)
    private List<Execution> executions;

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public List<Execution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<Execution> executions) {
        this.executions = executions;
    }

    @Override
    public String toString() {
        return "Case [scenario=" + scenario + ", step=" + step + ", expectedResult=" + expectedResult + ", testData=" + testData
                + ", prerequisite=" + prerequisite + ", executions=" + executions + ", getId()=" + getId() + ", getVersion()="
                + getVersion() + ", getUpdatedAt()=" + getUpdatedAt() + ", getUpdatedBy()=" + getUpdatedBy() + ", isNew()="
                + isNew() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + "]";
    }

}
