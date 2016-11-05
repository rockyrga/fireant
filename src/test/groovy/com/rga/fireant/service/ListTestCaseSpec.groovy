package com.rga.fireant.service

import org.springframework.beans.factory.annotation.Autowired

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.model.TestCase
import com.rga.fireant.model.data.TestCaseRepository

class ListTestCaseSpec extends AbstractSpec {

    ListTestCase service

    @Autowired
    TestCaseRepository repository

    def setup() {
        this.service = new ListTestCaseImpl(repository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should get all test cases"() {

        when: "Call list method of ListTestCase"
        List<TestCase> result = service.list()

        then: "Should get all test cases from database"
        assert result.isEmpty() == false
        def dbData = sql.firstRow("select count(*) number_of_data from test_cases")
        assert dbData.number_of_data == result.size()
    }
}
