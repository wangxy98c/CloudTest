package com.example.stream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);
    }

    @Bean
    Consumer<List<String>> input() {
        return list -> {
            list.forEach(thing -> {
                System.out.println("In Strater:"+thing);
            });
        };
    }

    @Bean//我们可以在程序启动之前执行任何任务。为了达到这个目的，我们需要使用CommandLineRunner或ApplicationRunner接口创建bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {
            template.convertAndSend("input-in-0.someGroup", "{\"field\":\"value1\"}");
            template.convertAndSend("input-in-0.someGroup", "{\"field\":\"value2\"}");
        };
    }

//    public static class Thing {
//
//        private String field;
//
//        public Thing() {
//        }
//
//        public Thing(String field) {
//            this.field = field;
//        }
//
//        public String getField() {
//            return this.field;
//        }
//
//        public void setField(String field) {
//            this.field = field;
//        }
//
//        @Override
//        public String toString() {
//            return "Thing [field=" + this.field + "]";
///       }
//
//
//    }
}
