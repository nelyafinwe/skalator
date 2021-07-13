/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package com.npg.skalator

import org.apache.spark.sql.SparkSession

object App {

  def main(args: Array[String]): Unit = {
    if (args.length != 2){
      println("Insufficient arguments. arg1=topic, arg2=output_path")
    }

    val ss = SparkSession
      .builder
      .appName("Spark Kafka Consumer n_n)")
      .getOrCreate()

    val topicToUse = args(0)
    val outputPathToUse = args(1)

    val df = ss
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "klooster-03-w-0:9092")
      .option("subscribe", topicToUse)
      .option("enable.auto.commit","true")
      .load()
    df.printSchema()

    val df2 = df.selectExpr("CAST(timestamp as TIMESTAMP)", "CAST(value as STRING)", "offset", "topic")
    df2.rdd.saveAsTextFile(outputPathToUse)

  }

  def greeting(): String = "Hello, world!"

}
