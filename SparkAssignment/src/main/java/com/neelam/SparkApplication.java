package com.neelam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SparkApplication {

	private static final String GROUP_ID = "com.neelm";
	private static final String TOPICS = "stockData";
	private static final String BROKER_ID = "52.55.237.11";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("hadoop.home.dir", "C:\\winutils");
		System.out.println(System.getProperty("hadoop.home.dir"));
		SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkApplication");
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.minutes(1));

		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_ID);
		kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		Set<String> topicSet = new HashSet<String>(Arrays.asList(TOPICS.split(",")));

		JavaInputDStream<ConsumerRecord<String, JsonNode>> messages = KafkaUtils.createDirectStream(jssc,
				LocationStrategies.PreferConsistent(), ConsumerStrategies.Subscribe(topicSet, kafkaParams));

		Function<ConsumerRecord<String, JsonNode>, Stock> f = new Function<ConsumerRecord<String, JsonNode>, Stock>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Stock call(ConsumerRecord<String, JsonNode> record) throws Exception {
				ObjectMapper om = new ObjectMapper();
				JsonNode value = record.value();
				System.out.println("Value: " + value);
				return om.treeToValue(value, Stock.class);
			}
		};
		JavaDStream<Stock> stocks = messages.map(f);

		System.out.println("Out: " + stocks.count());
		jssc.start();
		jssc.awaitTermination();
		jssc.close();

	}

}
