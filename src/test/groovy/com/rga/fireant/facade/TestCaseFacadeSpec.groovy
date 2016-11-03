package com.rga.fireant.facade


import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate

import spock.lang.Shared
import spock.lang.Stepwise

import com.rc.spoonbill.test.annotation.JsonDataSet

import com.rga.fireant.AbstractSpec
import com.rga.fireant.Application

@WebIntegrationTest(randomPort = true)
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application, locations = ["classpath:test-context-persistence.xml"])
@Stepwise
@JsonDataSet([
    "classpath:test-case.json"
])
class TestCaseFacadeSpec extends AbstractSpec {

    @Value('${local.server.port}')
    def port
    def url

    @Shared
    RestTemplate restTemplate

    def setupSpec() {
        restTemplate = new TestRestTemplate()
    }

    def setup() {
        url = "http://localhost:" + port + "/test-cases"
    }

    def "Should return 200 and respond all test cases"() {

        when:
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
        assert response.getBody() == """[{"id":1,"version":0,"updatedAt":[2015,12,8,17,50,31],"updatedBy":null,"scenario":"Verify that the input field that can accept maximum of 10 characters","step":"Login to application and key in 10 characters","expectedResult":"Application should be able to accept all 10 characters.","testData":null,"prerequisite":null,"executions":[]},{"id":2,"version":1,"updatedAt":[2015,12,8,17,50,31],"updatedBy":null,"scenario":"Scenario of test case 2","step":"Step of test case 2","expectedResult":"Expected result of test case 2","testData":"Test data of test case 2","prerequisite":"Prerequisite of test case 2","executions":[]}]"""
    }

    def "Should return 200 and respond a test case"() {

        when:
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
        assert response.getBody() == """{"id":1,"version":0,"updatedAt":[2015,12,8,17,50,31],"updatedBy":null,"scenario":"Verify that the input field that can accept maximum of 10 characters","step":"Login to application and key in 10 characters","expectedResult":"Application should be able to accept all 10 characters.","testData":null,"prerequisite":null,"executions":[]}"""
    }

    def "Should return 200 and respond a new created test case"() {

        given:
        def request = """
        {
            "scenario": "test scenario",
            "step": "test step",
            "expectedResult": "test expectedResult",
            "testData": "test testData",
            "prerequisite": "test prerequisite"
        }
        """

        when:
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def entity = new HttpEntity<String>(request, headers)
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
        def respondedJson = response.getBody()
        assert respondedJson.contains('"id":3')
        assert respondedJson.contains('"scenario":"test scenario"')
        assert respondedJson.contains('"step":"test step"')
        assert respondedJson.contains('"expectedResult":"test expectedResult"')
        assert respondedJson.contains('"testData":"test testData"')
        assert respondedJson.contains('"prerequisite":"test prerequisite"')
    }

    def "Should return 200 and respond an updated test case"() {

        given:
        def request = """
        {
            "scenario": "test updating scenario",
            "step": "test updating step",
            "expectedResult": "test updating expectedResult",
            "testData": "test updating testData",
            "prerequisite": "test updating prerequisite"
        }
        """

        when:
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def entity = new HttpEntity<String>(request, headers)
        ResponseEntity<String> response = restTemplate.exchange(url + "/1", HttpMethod.PUT, entity, String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
        def respondedJson = response.getBody()
        assert respondedJson.contains('"id":1')
        assert respondedJson.contains('"scenario":"test updating scenario"')
        assert respondedJson.contains('"step":"test updating step"')
        assert respondedJson.contains('"expectedResult":"test updating expectedResult"')
        assert respondedJson.contains('"testData":"test updating testData"')
        assert respondedJson.contains('"prerequisite":"test updating prerequisite"')
    }

    def "Should return 200 and remove a test case"() {

        when:
        ResponseEntity<String> response = restTemplate.exchange(url + "/1", HttpMethod.DELETE, null, String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
    }
}