package com.cs.searchfilter.sparkstreaming;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SchemaRDD;
import org.apache.spark.sql.catalyst.expressions.Row;
import org.apache.spark.sql.hive.HiveContext;

public class CustomReceiver {
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    
    final String SPARK_HOME = "D:/spark-1.2.1";
    String logFile = SPARK_HOME + "/README.txt";
    
    SparkConf conf = new SparkConf(true).setMaster("local[4]").setAppName("ContentEngine2");
    SparkContext spc = new SparkContext(conf);
//    JavaSparkContext sc = new JavaSparkContext(conf);
//    JavaHiveContext hc = new JavaHiveContext(sc);
    /*JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sc, new Duration(1));
//    JavaRDD<String> logData = sc.textFile(logFile).cache();
    JavaPairInputDStream<String,String> logData = javaStreamingContext.fileStream(logFile);
    
    long numAs = logData.filter(new Function<String, Boolean>()
    {
      
      public Boolean call(String s)
      {
        return s.contains("a");
      }
    }).count();
    
    long numBs = logData.filter(new Function<String, Boolean>()
    {
      
      public Boolean call(String s)
      {
        return s.contains("b");
      }
    }).count();
    
    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    
    javaStreamingContext.start();
    javaStreamingContext.awaitTermination();*/
    
    Test test = new Test();
//    JavaSQLContext sqlContext = new JavaSQLContext(sc);
    HiveContext sqlContext = new HiveContext(spc);
    SchemaRDD people = sqlContext.jsonFile("src/test/resources/ContentDataNew.json");
//    StreamingContext ssc = new StreamingContext(conf, new Duration(1000l));
//    JavaStreamingContext jsc = new JavaStreamingContext(ssc);
//    JavaPairInputDStream<String, String> abc = jsc.fileStream("src/test/resources/ContentDataNew.json");
//    abc.
    people.printSchema();
    people.registerTempTable("people");
//    JavaSchemaRDD teenagers = sqlContext.sql("SELECT * FROM people WHERE name='Tortenbrie'");
//    JavaSchemaRDD teenagers = test.getWhereAttributeEquals(sqlContext, "Tortenbrie", "");
    SchemaRDD all = test.getWhereAttributeEquals(sqlContext, "Tortenbrie", "");
    Row[] allRows = (Row[]) all.collect();
    System.out.println(allRows.length);
    /*JavaSchemaRDD filteredRows = all.filter(new Function<Row, Boolean>()
    	    {
        
        public Boolean call(Row s)
        {
          System.out.println(s.get(0));
          System.out.println("!@!@!@!@!@!@");
          List<Row> first = (List) s.get(0);
          System.out.println(first);
          for(Row r : first){
        	System.out.println(r.get(1));  
          }
//          System.out.println(s.get(1));
          return true;
        }
      });
*/    /*Row[] abc = (Row[])teenagers.rdd().collect();
    System.out.println(abc.length);*/
    
//    Row[] filtered = (Row[]) filteredRows.rdd().collect();
//    System.out.println(filtered.length);
  }
  
}
