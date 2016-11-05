package com.rga.fireant.service

import org.springframework.beans.factory.annotation.Autowired

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.model.TestCase
import com.rga.fireant.model.data.TestCaseRepository

class GetTestCaseSpec extends AbstractSpec {

    GetTestCase service

    @Autowired
    TestCaseRepository repository

    def setup() {
        this.service = new GetTestCaseImpl(repository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should get a test case"() {

        when: "Call get method of GetTestCase"
        TestCase result = service.get(1L)

        then: "Should get a test case from database"
        assert result != null
        def dbData = sql.firstRow("select * from test_cases where id = ?", 1L)
        assert result != null
        assert result.id == dbData.id
        assert result.scenario == dbData.scenario
        assert result.step == dbData.step
        assert result.expectedResult == dbData.expected_result
        assert result.testData == dbData.test_data
        assert result.prerequisite == dbData.prerequisite
        assert result.updatedAt != dbData.updated_at
        assert result.version == dbData.version
    }
}
