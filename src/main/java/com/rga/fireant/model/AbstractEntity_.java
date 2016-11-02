package com.rga.fireant.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractEntity.class)
public abstract class AbstractEntity_ {

	public static volatile SingularAttribute<AbstractEntity, String> updatedBy;
	public static volatile SingularAttribute<AbstractEntity, Long> id;
	public static volatile SingularAttribute<AbstractEntity, Integer> version;
	public static volatile SingularAttribute<AbstractEntity, Timestamp> updatedAt;

}

