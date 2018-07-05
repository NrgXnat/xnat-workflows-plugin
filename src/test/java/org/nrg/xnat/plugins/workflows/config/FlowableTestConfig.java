package org.nrg.xnat.plugins.workflows.config;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Properties;

@Configuration
@Import({HibernateConfig.class, ObjectMapperConfig.class})
public class FlowableTestConfig {

    @Bean
    public ProcessEngine processEngine(final DataSource dataSource,
                                       final PlatformTransactionManager transactionManager) {
        final SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        return processEngineConfiguration.buildProcessEngine();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(final DataSource dataSource, @Qualifier("hibernateProperties") final Properties properties) {
        final LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setHibernateProperties(properties);
        // bean.setAnnotatedClasses(
        //         CommandEntity.class,
        //         DockerCommandEntity.class,
        //         DockerSetupCommandEntity.class,
        //         DockerWrapupCommandEntity.class,
        //         CommandInputEntity.class,
        //         CommandOutputEntity.class,
        //         CommandMountEntity.class,
        //         CommandWrapperEntity.class,
        //         CommandWrapperExternalInputEntity.class,
        //         CommandWrapperDerivedInputEntity.class,
        //         CommandWrapperOutputEntity.class);
        return bean;
    }

    @Bean
    public ResourceTransactionManager transactionManager(final SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
