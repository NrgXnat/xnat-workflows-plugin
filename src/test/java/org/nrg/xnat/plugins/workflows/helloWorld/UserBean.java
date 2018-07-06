package org.nrg.xnat.plugins.workflows.helloWorld;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Copied from example docs: https://www.flowable.org/docs/userguide/index.html#springintegration
 */
@Component
public class UserBean {

    /** injected by Spring */
    private RuntimeService runtimeService;

    @Autowired
    public UserBean(final RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Transactional
    public void hello() {
        // here you can do transactional stuff in your domain model
        // and it will be combined in the same transaction as
        // the startProcessInstanceByKey to the Flowable RuntimeService
        runtimeService.startProcessInstanceByKey("helloProcess");
    }
}