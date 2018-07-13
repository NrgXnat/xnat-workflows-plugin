package org.nrg.xnat.plugins.workflows.helloWorld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
public class Printer implements Serializable {

    public void printMessage() {
        log.info("hello world");
    }
}
