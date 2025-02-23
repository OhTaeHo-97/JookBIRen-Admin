package com.ablez.admin.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.ablez.admin.first_db.answer.repository",
                "com.ablez.admin.first_db.hint.repository",
                "com.ablez.admin.first_db.quiz.repository",
                "com.ablez.admin.first_db.security.repository",
                "com.ablez.admin.first_db.user.repository",
                "com.ablez.admin.first_db.repository"
        },
        entityManagerFactoryRef = "firstEntityManager",
        transactionManagerRef = "firstTransactionManager",
        repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class
)
public class FirstDatabaseConfig {
    @Value("${spring.datasource.first_db.username}")
    private String username;
    @Value("${spring.datasource.first_db.password}")
    private String password;
    @Value("${spring.datasource.first_db.url}")
    private String url;

    @Primary
    @Bean
    public PlatformTransactionManager firstTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(firstEntityManager().getObject());

        return transactionManager;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean firstEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(firstDataSource());
        em.setPackagesToScan(new String[]{
                "com.ablez.admin.first_db.answer.entity",
                "com.ablez.admin.first_db.hint.entity",
                "com.ablez.admin.first_db.quiz.entity",
                "com.ablez.admin.first_db.security.entity",
                "com.ablez.admin.first_db.user.entity"
        });

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update"); // DB 스키마를 자동으로 생성하거나 업데이트할 수 있도록 설정
//        properties.put("hibernate.show_sql", "true"); // SQL 쿼리 콘솔 출력 활성화
//        properties.put("hibernate.format_sql", "true"); // SQL 쿼리를 읽기 쉽게 포맷팅 활성화
//        properties.put("hibernate.use_sql_comments", "true"); // SQL 쿼리에 주석을 추가하도록 활성화
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5InnoDBDialect"); // MySQL5의 InnoDB 다이얼렉트 사용하도록 설정
        properties.put("hibernate.jdbc.time_zone", "Asia/Seoul"); // JDBC 타임존 설정

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource firstDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}