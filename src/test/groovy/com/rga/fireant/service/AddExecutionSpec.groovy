package com.rga.fireant.service

import java.time.LocalDateTime

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.Application
import com.rga.fireant.LocalDateTimeAttributeConverter
import com.rga.fireant.model.Execution
import com.rga.fireant.model.Outcome
import com.rga.fireant.model.data.ExecutionRepository

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application, locations = ["classpath:test-context-persistence.xml"])
class AddExecutionSpec extends AbstractSpec {

    AddExecution addExecution

    @Autowired
    GetTestCase getTestCase

    @Autowired
    ExecutionRepository executionRepository

    def setup() {
        this.addExecution = new AddExecutionImpl(getTestCase, executionRepository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should add an execution to the test case"() {

        given: "There is an execution"
        Execution execution = new Execution()
        execution.setExecutedAt(LocalDateTime.of(2016, 1, 1, 5, 30, 02))
        execution.setActualResult("Application accepts all 10 characters.")
        execution.setOutcome(Outcome.PASS)

        when: "Call add method of AddExecution"
        Execution result = addExecution.add(1L, execution)

        then: "The execution should be created"
        assert result != null
        assert result.id != null
        assert result.updatedAt != null
        assert result.version == 0
        assert result.executedAt == execution.getExecutedAt()
        assert result.actualResult == execution.getActualResult()
        assert result.outcome == execution.getOutcome()

        and: "Added to the test case"
        def dbData = sql.firstRow("select * from executions where test_case_id = ?", 1L)
        assert dbData != null
        assert dbData.id != null
        assert dbData.updated_at != null
        assert dbData.version == 0
        assert execution.getExecutedAt().compareTo(new LocalDateTimeAttributeConverter().convertToEntityAttribute(dbData.executed_at)) == 0
        assert dbData.actual_result == execution.getActualResult()
        assert dbData.outcome == execution.getOutcome().ordinal()
    }
}
