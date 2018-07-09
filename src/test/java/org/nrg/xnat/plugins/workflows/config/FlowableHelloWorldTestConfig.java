package org.nrg.xnat.plugins.workflows.config;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.FlowableRule;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.hibernate.SessionFactory;
import org.nrg.xnat.plugins.workflows.helloWorld.Printer;
import org.nrg.xnat.plugins.workflows.helloWorld.UserBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({HibernateConfig.class, ObjectMapperConfig.class})
public class FlowableHelloWorldTestConfig {

    @Bean
    public ProcessEngine processEngine(final DataSource dataSource,
                                       final PlatformTransactionManager transactionManager,
                                       final ApplicationContext ctx) {
        final SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        processEngineConfiguration.setApplicationContext(ctx);

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

    // NOT SURE IF I NEED ALL OF THESE (they were in the example so I'm keeping them)
    // https://www.flowable.org/docs/userguide/index.html#springintegration
    @Bean
    public RepositoryService repositoryService(final ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(final ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(final ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(final ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(final ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean
    public UserBean userBean(final RuntimeService runtimeService) {
        return new UserBean(runtimeService);
    }

    @Bean
    public Printer printer() {
        return new Printer();
    }

    @Bean
    public FlowableRule flowableRule(final ProcessEngine processEngine) {
        return new FlowableRule(processEngine);
    }
}
