package org.nrg.xnat.plugins.workflows.helloWorld;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.nrg.xnat.plugins.workflows.config.FlowableHelloWorldTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FlowableHelloWorldTestConfig.class)
@Slf4j
public class FlowableHelloWorldTest {

    @Autowired ProcessEngine processEngine;
    @Autowired RepositoryService repositoryService;

    @Rule public TestName testName = new TestName();

    @Before
    public void setup() throws Exception {
        log.info("Begin test " + testName.getMethodName());
    }

    @Test
    public void testSpringConfiguration() throws Exception {
        assertThat(processEngine, not(nullValue()));
    }

    @Test
    public void testDeployProcessXml() throws Exception {
        final String processXMLPath = "org/nrg/xnat/plugins/workflows/helloWorld/hello.bpmn20.xml";

        log.debug("Deploying process XML " + processXMLPath);
        final String id = repositoryService.createDeployment()
                .addClasspathResource(processXMLPath)
                .deploy()
                .getId();

        log.info("Got id " + id);
        assertThat(id, not(isEmptyOrNullString()));
    }
}
