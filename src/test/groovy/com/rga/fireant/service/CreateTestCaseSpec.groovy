package com.rga.fireant.service

import org.springframework.beans.factory.annotation.Autowired

import com.rga.fireant.AbstractSpec
import com.rga.fireant.model.TestCase
import com.rga.fireant.model.data.TestCaseRepository

class CreateTestCaseSpec extends AbstractSpec {

    CreateTestCase service

    @Autowired
    TestCaseRepository repository

    def setup() {
        this.service = new CreateTestCaseImpl(repository)
    }

    def "Should create a test case"() {

        given: "There is a test case"
        TestCase testCase = new TestCase()
        testCase.scenario = "Verify that the input field that can accept maximum of 10 characters"
        testCase.step = "Login to application and key in 10 characters"
        testCase.expectedResult = "Application should be able to accept all 10 characters."
        testCase.testData = null
        testCase.prerequisite = null

        when: "Call save method of CreateTestCase"
        TestCase result = service.create(testCase)

        then: "Return a saved test case"
        assert result != null
        assert result.id != null
        assert result.scenario == testCase.scenario
        assert result.step == testCase.step
        assert result.expectedResult == testCase.expectedResult
        assert result.testData == testCase.testData
        assert result.prerequisite == testCase.prerequisite
        assert result.updatedAt != null
        assert result.version == 0

        and: "Should write data into database"
        def dbData = sql.firstRow("select * from test_cases where id = ?", result.id)
        assert dbData.id != null
        assert dbData.scenario == testCase.scenario
        assert dbData.step == testCase.step
        assert dbData.expected_result == testCase.expectedResult
        assert dbData.test_data == testCase.testData
        assert dbData.prerequisite == testCase.prerequisite
        assert dbData.updated_at != null
        assert dbData.version == 0
    }
}
