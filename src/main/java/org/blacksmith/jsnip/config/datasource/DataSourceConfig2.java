package org.blacksmith.jsnip.config.datasource;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.model.repo",
    entityManagerFactoryRef = "talonEntityManagerFactory",
    transactionManagerRef = "talonTransactionManager")
@ConfigurationProperties(prefix = "talon.datasource")
public class DataSourceConfig2 extends HikariConfig {

  @Configuration
  @ConfigurationProperties(prefix = "db1.datasource.jpa")
  public class Db1JpaProperties extends JpaProperties {
  }

  @Autowired
  private Db1JpaProperties jpaProperties;

  @Primary
  @Bean(name = "db1JpaProperties")
  @ConfigurationProperties(prefix = "db1.datasource.jpa")
  public JpaProperties db1JpaProperties() {
    return new JpaProperties();
  }

  @Primary
  @Bean(name = "db1DataSource")
  public DataSource db1DataSource() {
    return new HikariDataSource(this);
  }

  @Primary
  @PersistenceContext(unitName = "db1")
  @Bean(name = "db1EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder,
      @Qualifier("db1DataSource") DataSource dataSource,
      @Qualifier("db1JpaProperties") JpaProperties jpaProperties) {
    return builder.dataSource(dataSource)
        .persistenceUnit("db1PersistenceUnit")
        .packages("com.model.db1.entity")
        .properties(jpaProperties.getProperties())
        .build();
  }

  @Primary
  @Bean(name = "db1TransactionManager")
  public PlatformTransactionManager db1TransactionManager(
      @Qualifier("db1DataSource") DataSource dataSource,
      @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory.getObject());
  }

}
