package com.neal.KafkaPractise.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerPractise implements Runnable {

	private static String topic = "mydemo1";

	public void run() {

		ConsumerConnector consumer = createConsumer();

		// string : topic
		// integer : topic records number
		Map<String, Integer> topics = new HashMap();
		topics.put(topic, 2);

		// msaage stream
		Map<String, List<KafkaStream<byte[], byte[]>>> msgStream = consumer.createMessageStreams(topics);
		KafkaStream<byte[], byte[]> stream = msgStream.get(topic).get(0);

		ConsumerIterator<byte[], byte[]> iterator = stream.iterator();

		while (iterator.hasNext()) {
			String mString = new String(iterator.next().message());

			System.out.println("Receive " + mString);
		}
	}

	private ConsumerConnector createConsumer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "192.168.153.128:2181,192.168.153.129:2181,192.168.153.131:2181");
		// properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("group.id", "group1");
		return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
	}

	public static void main(String[] args) {
		ConsumerPractise consumerPractise = new ConsumerPractise();
		new Thread(consumerPractise).start();
	}
}
