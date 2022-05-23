package com.study.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 * @Configuration : 스프링은 이 어노테이션이 지정된 클래스를 자바 기반의 설정 파일로 인식한다.
 * 
 * @propertySource : 해당 클래스에서 참조할 properties 파일의 위치를 지정한다.
 * 
 * @Bean : 이 어노테이션이 지정된 객체는 컨테이너에 의해 관리되는 빈으로 지정된다. (Configuration 클래스의 메서드 레벨에만 지정 가능)
 * 
 * @ConfigurationProperties : 해당 어노테이션은 @PropertySource에 지정된 파일에서 prefix에 해당하는 spring.datasource.hikari로 시작하는
 * 							설정을 모두 읽어들여 해당 메서드에 매핑(바인딩)한다. (메서드 뿐만 아니라 클래스 레벨에도 지정가능)
*/

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    /*
     * HikariConfig : 커넥션 풀 라이브러리 중 하나 
     * 
     * dataSource : 데이터 소스 객체를 생성 (커넥션 풀을 지원하기 위한 인터페이스)
    */
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

}