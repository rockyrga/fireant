package com.rga.fireant.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.rga.fireant.Application;

@Entity
@Table(name = "test_cases")
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

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "testCase")
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

        if (executions == null) {
            return new ArrayList<>();
        }

        return executions;
    }

    public void setExecutions(List<Execution> executions) {
        this.executions = executions;
    }

    public void addExecution(Execution execution) {

        execution.setTestCase(this);
        getExecutions().add(execution);
    }
}
