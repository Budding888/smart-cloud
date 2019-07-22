package com.smart.track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LErry.li
 * Description:
 *	分布式链路跟踪启动类
 * Date: 2019-7-22 18:44:35
 */
@SpringBootApplication
public class TrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackApplication.class, args);
    }

}
