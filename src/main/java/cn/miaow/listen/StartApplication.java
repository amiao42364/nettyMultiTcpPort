package cn.miaow.listen;

import cn.miaow.listen.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author miaow
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("cn.miaow.listen.mapper")
public class StartApplication implements CommandLineRunner {

    private final NettyServer server;

    public StartApplication(NettyServer server) {
        this.server = server;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {
        server.start();
    }
}
