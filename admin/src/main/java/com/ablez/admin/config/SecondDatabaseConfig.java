package com.ablez.admin.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.ablez.admin.second_db.answer.repository",
                "com.ablez.admin.second_db.hint.repository",
                "com.ablez.admin.second_db.quiz.repository",
                "com.ablez.admin.second_db.user.repository",
                "com.ablez.admin.second_db.repository"
        },
        entityManagerFactoryRef = "secondEntityManager",
        transactionManagerRef = "secondTransactionManager",
        repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class
)
public class SecondDatabaseConfig {
    @Value("${spring.datasource.second_db.username}")
    private String username;
    @Value("${spring.datasource.second_db.password}")
    private String password;
    @Value("${spring.datasource.second_db.url}")
    private String url;

    @Bean
    public PlatformTransactionManager secondTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(secondDataSource());
        em.setPackagesToScan(new String[]{
                "com.ablez.admin.second_db.answer.entity",
                "com.ablez.admin.second_db.hint.entity",
                "com.ablez.admin.second_db.quiz.entity",
                "com.ablez.admin.second_db.security.entity",
                "com.ablez.admin.second_db.user.entity"
        });

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.show_sql", "true"); // SQL 쿼리 출력 활성화
//        properties.put("hibernate.format_sql", "true"); // SQL 포맷팅 활성화
//        properties.put("hibernate.use_sql_comments", "true"); // SQL 쿼리 주석 활성화
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.jdbc.time_zone", "Asia/Seoul"); // JDBC 타임존 설정

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource secondDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
