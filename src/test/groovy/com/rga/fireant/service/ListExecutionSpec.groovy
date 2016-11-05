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
class ListExecutionSpec extends AbstractSpec {

    ListExecution service

    @Autowired
    GetTestCase getTestCase

    @Autowired
    ExecutionRepository executionRepository

    def setup() {
        this.service = new ListExecutionImpl(getTestCase, executionRepository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should get all test cases"() {

        when: "Call list method of ListTestCase"
        List<Execution> result = service.list(2L)

        then: "Should get all test cases from database"
        assert result.isEmpty() == false
        def dbData = sql.firstRow("select count(*) number_of_data from executions where test_case_id = ?", 2L)
        assert dbData.number_of_data == result.size()
    }
}
