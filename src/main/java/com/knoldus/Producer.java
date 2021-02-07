package com.knoldus;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.serializer.UserSerializer");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer(properties);
        try{
            Random randomObject = new Random();
            for(int i=1; i<=100; i++){

                User user = new User(i, "Tanishka Garg", randomObject.nextInt(10)+20, "MCA");
                kafkaProducer.send(new ProducerRecord("user", user));
                System.out.println("Message Sent : " + user.toString());

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}
