package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:/application.properties"}) // 프로퍼티 파일 로드
@MapperScan(basePackages = {"org.scoula.board.mapper", "org.scoula.member.mapper" }) // MyBatis 매퍼 스캔
@ComponentScan(basePackages = {"org.scoula.board.service","org.scoula.member.service"}) // 서비스 컴포넌트 스캔
@Log4j2
@EnableTransactionManagement // 트랜잭션 어노테이션 활성화
public class RootConfig {

    // application.properties에서 값 주입
    @Value("${jdbc.driver}")
    String driver;

    @Value("${jdbc.url}")
    String url;

    @Value("${jdbc.username}")
    String username;

    @Value("${jdbc.password}")
    String password;

    /**
     * 커넥션 풀(DataSource) 빈 등록
     * - HikariConfig에 DB 연결 정보 설정
     * - HikariDataSource 생성 후 반환
     */
    @Bean
    public DataSource dataSource() {
        // Hikari 설정 객체 생성
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);      // 드라이버 클래스 설정
        config.setJdbcUrl(url);                 // JDBC URL 설정
        config.setUsername(username);           // DB 사용자 이름 설정
        config.setPassword(password);           // DB 비밀번호 설정

        // 위 설정을 기반으로 DataSource 생성
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Autowired
    ApplicationContext applicationContext;

    /**
     * MyBatis의 SqlSessionFactory 빈 등록
     * - mybatis-config.xml 설정을 읽고
     * - DataSource를 주입받아 SQL 세션 팩토리를 생성
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        // MyBatis 설정 파일(xml) 위치 지정
        sqlSessionFactory.setConfigLocation(
                applicationContext.getResource("classpath:/mybatis-config.xml")
        );

        // 커넥션 풀 설정 주입
        sqlSessionFactory.setDataSource(dataSource());

        // 실제 SqlSessionFactory 객체 반환
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    /**
     * 트랜잭션 매니저 등록
     * - 스프링에서 @Transactional 사용 가능하게 함
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        // DataSource 기반 트랜잭션 매니저 생성
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        return manager;
    }
}