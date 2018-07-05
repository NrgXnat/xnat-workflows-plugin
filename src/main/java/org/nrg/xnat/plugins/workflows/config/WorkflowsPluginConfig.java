package org.nrg.xnat.plugins.workflows.config;

import org.nrg.framework.annotations.XnatPlugin;
import org.nrg.xnat.initialization.RootConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
@XnatPlugin(value = "xnat-workflows-plugin",
        name = "xnat-workflows-plugin",
        description = "XNAT Workflows Plugin",
        entityPackages = "org.nrg.xnat.plugins.workflows",
        log4jPropertiesFile = "META-INF/resources/log4j.properties",
        version = ""
)
@ComponentScan(value = "org.nrg.xnat.plugins.workflows",
        excludeFilters = @Filter(type = FilterType.REGEX, pattern = ".*TestConfig.*", value = {}))
@Import({RootConfig.class})
public class WorkflowsPluginConfig {
    // @Bean
    // public Module guavaModule() {
    //     return new GuavaModule();
    // }
    //
    // @Bean
    // public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder objectMapperBuilder) {
    //     return objectMapperBuilder.build();
    // }

    // @Bean
    // public TriggerTask dockerEventPullerTask(final DockerStatusUpdater dockerStatusUpdater) {
    //     return new TriggerTask(
    //             dockerStatusUpdater,
    //             new PeriodicTrigger(10L, TimeUnit.SECONDS)
    //     );
    // }
}