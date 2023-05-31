package com;

import com.njupt.ResourceLoad;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = "com")
@MapperScan(basePackages = {"com.njupt.mapper","com.upf.mapper"})
public class MecCloudApplication {
    public static void main(String[] args) {
        try {
             SpringApplication.run(MecCloudApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ResourceLoad resourceLoad() {
        return new ResourceLoad();
    }
}
