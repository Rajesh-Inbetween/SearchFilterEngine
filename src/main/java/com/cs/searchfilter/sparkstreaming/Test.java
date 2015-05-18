package com.cs.searchfilter.sparkstreaming;

import org.apache.spark.sql.SchemaRDD;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;
import org.apache.spark.sql.hive.HiveContext;

public class Test {

	public SchemaRDD getAll(HiveContext sqlContext, String name){
		return sqlContext.hql("SELECT contentAttributes FROM people where name='" + name + "'");
	}
	
	public SchemaRDD getByName(HiveContext sqlContext, String name){
		return sqlContext.hql("SELECT * FROM people WHERE name='"+ name +"'");
	}
	
	public SchemaRDD getWhereAttributeEquals(HiveContext sqlContext, String attrKey,  String attrVal){
		return sqlContext.sql("SELECT * FROM people WHERE contentAttributes[].attributes.ME='100g'");
	}	
}
