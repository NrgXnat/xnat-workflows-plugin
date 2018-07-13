package org.nrg.xnat.plugins.workflows.config;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.nrg.framework.annotations.XnatPlugin;
import org.nrg.xnat.initialization.RootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@XnatPlugin(value = "xnat-workflows-plugin",
        name = "xnat-workflows-plugin",
        description = "XNAT Workflows Plugin",
        entityPackages = "org.nrg.xnat.plugins.workflows",
        log4jPropertiesFile = "META-INF/resources/log4j.properties",
        version = "1.0.0-SNAPSHOT"
)
@ComponentScan(value = "org.nrg.xnat.plugins.workflows",
        excludeFilters = @Filter(type = FilterType.REGEX, pattern = ".*TestConfig.*", value = {}))
@Import({RootConfig.class})
public class WorkflowsPluginConfig {
    @Bean
    public ProcessEngineConfigurationImpl processEngineConfiguration(final DataSource dataSource,
                                                                     final PlatformTransactionManager transactionManager) {
        final SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        return processEngineConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(final ProcessEngineConfigurationImpl processEngineConfiguration,
                                                             final ApplicationContext ctx) {
        final ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        processEngineFactoryBean.setApplicationContext(ctx);

        return processEngineFactoryBean;
    }

    @Bean
    public ProcessEngine processEngine(final ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
        return processEngineFactoryBean.getObject();
    }
}