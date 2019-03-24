# KafkaPractise

	* 单Broker安装：


	1. cd /opt
	2. tar -zxvf kafka_2.9.2-0.8.1.1.tgz
	3. rm kafka_2.9.2-0.8.1.1.tgz
	4. mv kafka_2.9.2-0.8.1.1.tgz karfka
	5. cd /var
	6. mkdir kafka-log
	7. cd /opt/kafka/config
	8. vim server.properties
	9. log.dirs = /var/kafka-log
	10. zookeeper.connect=192.168.153.128:2181,192.168.153.129:2181,192.168.153.131:2181


	* 
 单机多Broker安装(伪分布)


	1. 
cd /opt/kafka-log
	2. 
mkdir broker0
	3. 
mkdir broker1
	4. 
cd /opt/kafka/config
	5. 
vim server.properties
	6. 
log.dirs = /var/kafka-log/broker0
	7. 
cp server.properties server1.properties
	8. 
vim server1.properties
	9. 
port=9093
	10. 
broker.id=1
	11. 
log.dirs = /var/kafka-log/broker1




	* 
  Broker集群


	1. 
根据单机多broker安装，在其他linux机器上安装kafka以及配置多个broker


	* 启动和关闭Kafka


            cd /opt/kafka/bin
            bin/kafka-server-start.sh config/server.properties &
            bin/kafka-server-start.sh config/server1.properties &
            bin/kafka-server-start.sh config/server2.properties &
            bin/kafka-server-start.sh config/server3.properties &
            bin/kafka-server-stop.sh onfig/server.properties
 
	* 
创建Topic : 


            bin/kafka-topics.sh --create --zookeeper 192.168.153.128:2181 --replication-factor 1 --partitions 3 --topic mydemo1

	* 
查看Topic：


            bin/kafka-topics.sh --list --zookeeper 192.168.153.128:2181 --topic mydemo1
            bin/kafka-topics.sh --describe --zookeeper 192.168.153.128:2181 --topic mydemo1

	* 
生产者：


            bin/kafka-console-producer.sh --broker-list192.168.153.128:9092 --topic mydemo1
           
	* 
消费者：


            bin/kafka-console-consumer.sh --zookeeper 192.168.153.128:2181 --topic mydemo1
            
            
	* kafka 启动 报错cannot allocate memory，即内存不足


	1. 将 kafka-server-start.sh的export KAFKA_HEAP_OPTS="-Xmx1G -Xms1G"

           修改为 export KAFKA_HEAP_OPTS="-Xmx256M -Xms128M"

	* 
 Timeout （3 times try error）


	1. 
 firewall-cmd --zone=public --add-port=9092/tcp --permanent


               firewall-cmd --reload
	1. 
 vim server.properties


               host.name = 192.168.153.128
       
