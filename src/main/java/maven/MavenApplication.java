package maven;
import maven.runner.ServerRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class MavenApplication {

	public static void main(String[] args) {
		ServerRunner serverRunner = new ServerRunner();
		serverRunner.run();

		SpringApplication.run(MavenApplication.class, args);

		MassTaskAllocator allocator = new MassTaskAllocator();
        allocator.start();
	}


	/**
	 * 文件上传配置
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("10240KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
