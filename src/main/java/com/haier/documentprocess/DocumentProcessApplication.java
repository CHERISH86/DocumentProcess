package com.haier.documentprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.haier.documentprocess.dao")
public class DocumentProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocumentProcessApplication.class, args);
    }
}
