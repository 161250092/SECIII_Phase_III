package com.example.maven;

import com.example.maven.data.fileHelper.FileSystemInitializer;
import com.example.maven.runner.ServerRunner;
import org.apache.catalina.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MavenApplication {

	public static void main(String[] args) {
		ServerRunner serverRunner = new ServerRunner();
		serverRunner.run();
		SpringApplication.run(MavenApplication.class, args);
	}
}
