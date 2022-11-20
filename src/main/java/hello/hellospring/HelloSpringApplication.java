package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication { // 해당 하위 패키지만 어노테이션 등록하여 사용가능

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
