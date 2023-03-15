package org.logger.controller;

import org.logger.entity.LoggerMessage;
import org.logger.service.LoggerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/logger")
public class LoggerController {

    //private final Logger logger = LoggerFactory.getLogger(LoggerController.class);
    private final LoggerService loggerService;

    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @PostMapping("/push")
    public Mono<Void> push(@RequestBody List<LoggerMessage> loggerMessages) {

        Flux<LoggerMessage> flux = Flux.fromIterable(loggerMessages);
        // 将数据分批处理，每批处理2000条记录
        return flux.buffer(2000)
                .flatMap(batch -> {
                    loggerService.push(batch);
                    return Mono.empty();
                })
                .then();
    }
}
