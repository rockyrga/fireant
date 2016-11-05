package com.rga.fireant.service

import org.springframework.beans.factory.annotation.Autowired

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.model.TestCase
import com.rga.fireant.model.data.TestCaseRepository

class UpdateTestCaseSpec extends AbstractSpec {

    UpdateTestCase service

    @Autowired
    TestCaseRepository repository

    def setup() {
        this.service = new UpdateTestCaseImpl(repository)
    }

    @JsonDataSet(["classpath:test-case.json"])
    def "Should update a test case"() {

        given: "Retrieve a test case from database"
        TestCase testCase = repository.findOne(1L)

        when: "Update some data"
        testCase.scenario = "scenario"
        testCase.step = "step"
        testCase.expectedResult = "expected result"
        testCase.testData = "test data"
        testCase.prerequisite = "prerequisite"

        and: "Call update method of UpdateTestCase"
        TestCase result = service.update(testCase)

        then: "Return a updated test case"
        assert result != null
        assert result.id == testCase.id
        assert result.scenario == testCase.scenario
        assert result.step == testCase.step
        assert result.expectedResult == testCase.expectedResult
        assert result.testData == testCase.testData
        assert result.prerequisite == testCase.prerequisite
        assert result.updatedAt != testCase.updatedAt
        assert result.version == testCase.version + 1

        and: "Should update data in database"
        def dbData = sql.firstRow("select * from test_cases where id = ?", result.id)
        assert dbData.id == testCase.id
        assert dbData.scenario == testCase.scenario
        assert dbData.step == testCase.step
        assert dbData.expected_result == testCase.expectedResult
        assert dbData.test_data == testCase.testData
        assert dbData.prerequisite == testCase.prerequisite
        assert dbData.updated_at != testCase.updatedAt
        assert dbData.version == testCase.version + 1
    }
}
