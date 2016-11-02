package com.rga.fireant.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Execution.class)
public abstract class Execution_ extends com.rga.fireant.model.AbstractEntity_ {

	public static volatile SingularAttribute<Execution, Timestamp> executedAt;
	public static volatile SingularAttribute<Execution, Outcome> outcome;
	public static volatile SingularAttribute<Execution, TestCase> testCase;
	public static volatile SingularAttribute<Execution, String> actualResult;

}

