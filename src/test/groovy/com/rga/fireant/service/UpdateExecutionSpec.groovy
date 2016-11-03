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
class UpdateExecutionSpec extends AbstractSpec {

    UpdateExecution updateExecution

    @Autowired
    GetTestCase getTestCase

    @Autowired
    ExecutionRepository executionRepository

    def setup() {
        this.updateExecution = new UpdateExecutionImpl(getTestCase, executionRepository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should update an execution on the test case"() {

        given: "Retrieve an execution from database"
        Execution execution = executionRepository.findOne(1L)

        and: "Update some data"
        execution.setExecutedAt(LocalDateTime.of(2011, 1, 1, 5, 30, 02))
        execution.setActualResult("Test acutal result")
        execution.setOutcome(Outcome.FAIL)

        when: "Call update method of UpdateExecution"
        Execution result = updateExecution.update(1L, execution)

        then: "The execution should be created"
        assert result != null
        assert result.id == execution.id
        assert result.updatedAt != execution.updatedAt
        assert result.version == execution.version + 1
        assert result.executedAt == execution.getExecutedAt()
        assert result.actualResult == execution.getActualResult()
        assert result.outcome == execution.getOutcome()

        and: "Updateed to the test case"
        def dbData = sql.firstRow("select * from executions where id = ?", 1L)
        assert dbData != null
        assert dbData.id == execution.id
        assert dbData.updated_at != execution.updatedAt
        assert dbData.version == execution.version + 1
        assert execution.getExecutedAt().compareTo(new LocalDateTimeAttributeConverter().convertToEntityAttribute(dbData.executed_at)) == 0
        assert dbData.actual_result == execution.getActualResult()
        assert dbData.outcome == execution.getOutcome().ordinal()
    }
}
