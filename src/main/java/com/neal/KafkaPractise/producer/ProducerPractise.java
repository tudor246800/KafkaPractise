package com.neal.KafkaPractise.producer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class ProducerPractise implements Runnable {
	private static String topic = "mydemo1";

	public static void main(String[] args) {
		ProducerPractise producerPractise = new ProducerPractise();
		new Thread(producerPractise).start();
	}

	public void run() {
		Producer producer = createProducer();
		int i = 1;

		while (true) {
			String msg = i + "msg";
			i++;
			producer.send(new KeyedMessage(topic, msg));

			System.out.println(msg);

			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Producer createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "192.168.153.128:2181,192.168.153.129:2181,192.168.153.131:2181");
		properties.put("zookeeper.session.timeout.ms", "400000");
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "192.168.153.128:9092,192.168.153.128:9093,192.168.153.129:9092");
		return new Producer(new ProducerConfig(properties));
	}
}
