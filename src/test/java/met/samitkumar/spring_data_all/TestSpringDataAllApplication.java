package met.samitkumar.spring_data_all;

import org.springframework.boot.SpringApplication;

public class TestSpringDataAllApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringDataAllApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
