package com.rga.fireant.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.Application
import com.rga.fireant.model.Execution
import com.rga.fireant.model.data.ExecutionRepository

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application, locations = ["classpath:test-context-persistence.xml"])
class DeleteExecutionSpec extends AbstractSpec {

    DeleteExecution deleteExecution

    @Autowired
    GetTestCase getTestCase

    @Autowired
    ExecutionRepository executionRepository

    def setup() {
        this.deleteExecution = new DeleteExecutionImpl(getTestCase, executionRepository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should delete an execution from the test case"() {

        when: "Call delete method of DeleteExecution"
        Execution result = deleteExecution.delete(1L, 1L)

        then: "The execution should be deleted"
        def dbData = sql.firstRow("select count(*) as number_of_data from executions where id = ?", 1L)
        assert dbData.number_of_data == 0
    }
}
