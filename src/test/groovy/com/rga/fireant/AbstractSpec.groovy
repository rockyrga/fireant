package com.rga.fireant

import groovy.sql.Sql

import org.springframework.test.context.ContextConfiguration

import spock.lang.Shared
import spock.lang.Specification


@ContextConfiguration(locations = ["classpath:test-context-persistence.xml"])
class AbstractSpec extends Specification {

    @Shared sql = Sql.newInstance('jdbc:hsqldb:mem://localhost/fireant', 'sa', '', 'org.hsqldb.jdbcDriver')
}
