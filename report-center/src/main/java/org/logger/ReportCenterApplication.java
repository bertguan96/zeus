package org.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动日志中心
 */
@SpringBootApplication(scanBasePackages="org.logger")
public class ReportCenterApplication {
    public static void main(String[] args)  {
        SpringApplication.run(ReportCenterApplication.class, args);
    }
}