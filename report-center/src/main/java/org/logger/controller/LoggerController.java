package org.logger.controller;

import org.logger.entity.LoggerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/logger")
public class LoggerController {

    private final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @PostMapping("/push")
    public void push(LoggerMessage loggerMessage) {
        logger.info("test id: " + loggerMessage);
    }
}
