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
class ExecutionFacadeSpec extends AbstractSpec {

    @Value('${local.server.port}')
    def port
    def url

    @Shared
    RestTemplate restTemplate

    def setupSpec() {
        restTemplate = new TestRestTemplate()
    }

    def setup() {
        url = "http://localhost:" + port + "/test-cases/1/executions"
    }

    def "Should return 200 and respond all executions"() {

        when:
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
        assert response.getBody() == """[{"id":1,"version":0,"updatedAt":[2015,12,8,17,50,31],"updatedBy":null,"executedAt":[2015,12,8,17,50,31],"actualResult":"Application accepts all 10 characters.","outcome":"PASS","new":false}]"""
    }

    def "Should return 200 and respond a new created execution"() {

        given:
        def request = """
        {
            "actualResult": "test actualResult",
            "outcome": "PASS",
            "executedAt": [2017,12,8,17,50,31]
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
        assert respondedJson.contains('"actualResult":"test actualResult"')
        assert respondedJson.contains('"outcome":"PASS"')
        assert respondedJson.contains('"executedAt":[2017,12,8,17,50,31]')
    }

    def "Should return 200 and respond an updated execution"() {

        given:
        def request = """
        {
            "actualResult": "test actualResult",
            "outcome": "FAIL",
            "executedAt": [2017,12,8,17,50,31]
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
        assert respondedJson.contains('"actualResult":"test actualResult"')
        assert respondedJson.contains('"outcome":"FAIL"')
        assert respondedJson.contains('"executedAt":[2017,12,8,17,50,31]')
    }

    def "Should return 200 and remove an execution"() {

        when:
        ResponseEntity<String> response = restTemplate.exchange(url + "/1", HttpMethod.DELETE, null, String.class)

        then:
        assert response.getStatusCode() == HttpStatus.OK
    }
}
