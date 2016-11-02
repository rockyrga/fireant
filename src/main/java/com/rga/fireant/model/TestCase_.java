package com.rga.fireant.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TestCase.class)
public abstract class TestCase_ extends com.rga.fireant.model.AbstractEntity_ {

	public static volatile SingularAttribute<TestCase, String> testData;
	public static volatile ListAttribute<TestCase, Execution> executions;
	public static volatile SingularAttribute<TestCase, String> scenario;
	public static volatile SingularAttribute<TestCase, String> expectedResult;
	public static volatile SingularAttribute<TestCase, String> prerequisite;
	public static volatile SingularAttribute<TestCase, String> step;

}

