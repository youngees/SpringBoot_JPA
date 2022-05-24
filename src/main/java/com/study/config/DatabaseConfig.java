package com.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

/*
 * @Configuration : 스프링은 이 어노테이션이 지정된 클래스를 자바 기반의 설정 파일로 인식한다.
 * 
 * @propertySource : 해당 클래스에서 참조할 properties 파일의 위치를 지정한다.
 * 
 * @Bean : 이 어노테이션이 지정된 객체는 컨테이너에 의해 관리되는 빈으로 지정된다. (Configuration 클래스의 메서드 레벨에만 지정 가능)
 * 
 * @ConfigurationProperties : 해당 어노테이션은 @PropertySource에 지정된 파일에서 prefix에 해당하는 spring.datasource.hikari로 시작하는
 * 							설정을 모두 읽어들여 해당 메서드에 매핑(바인딩)한다. (메서드 뿐만 아니라 클래스 레벨에도 지정가능)
 * 
 * @ApplicationContext : 스프링의 컨테이너 중 하나로 빈의 생성과 사용,관계,생명주기등을 관리한다. 여기서는 MyBatis의 Mapper XML 경로를 처리하기 위해 사용된다.
*/

@Configuration
@PropertySource("classpath:/application.properties")
@RequiredArgsConstructor
public class DatabaseConfig {
	
	private final ApplicationContext context;

    /*
     * HikariConfig : 커넥션 풀 라이브러리 중 하나 
     * 
     * dataSource : 데이터 소스 객체를 생성. 커넥션 풀은 커넥션 객체를 생성해두고, 데이터베이스에 접근하는 사용자에게 미리 생성해둔 커넥션을 제공했다가 다시 돌려받는 방법이다.
     * 				데이터 소스는 커넥션 풀을 지원하기 위한 인터페이스!
    */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }
    
    
    /*
     * SqlSessionFactory : DB의 커넥션과 SQL 실행에 대한 모든것을 갖는 객체. 65번라인의 factoryBean.setDataSource를 통해 Mapper XML의 경로를 지정한다.
     * 					classpath는 src/main/resources 디렉터리를 의미하며 해당 경로에 Mapper XML을 추가하게 된다. 
     * 
     * SqlSession : SQL 실행에 필요한 모든 메서드(insert, update, delete, select)를 갖는 객체
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}