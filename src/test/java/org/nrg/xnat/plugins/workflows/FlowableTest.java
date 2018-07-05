package org.nrg.xnat.plugins.workflows;

import org.flowable.engine.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nrg.xnat.plugins.workflows.config.FlowableTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FlowableTestConfig.class)
public class FlowableTest {
    @Autowired ProcessEngine processEngine;

    @Test
    public void testSpringConfiguration() throws Exception {
        assertThat(processEngine, not(nullValue()));
    }
}
