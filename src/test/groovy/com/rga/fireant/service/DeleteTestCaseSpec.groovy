package com.rga.fireant.service

import org.springframework.beans.factory.annotation.Autowired

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.model.data.TestCaseRepository

class DeleteTestCaseSpec extends AbstractSpec {

    DeleteTestCase service

    @Autowired
    TestCaseRepository repository

    def setup() {
        this.service = new DeleteTestCaseImpl(repository)
    }

    @JsonDataSet([
        "classpath:test-case.json"
    ])
    def "Should delete a test case"() {

        when: "Call delete method of DeleteTestCase"
        service.delete(1L)

        then: "The test case should be deleted"
        def dbData = sql.firstRow("select count(*) as number_of_data from test_cases where id = ?", 1L)
        assert dbData.number_of_data == 0
    }
}
