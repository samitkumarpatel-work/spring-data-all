package net.samitkumar.springdataall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
@Testcontainers
class SpringDataAllApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	AddressRepository addressRepository;
	@Container
	@ServiceConnection
	static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
			.withInitScript("db/schema.sql")
				.waitingFor(Wait.forListeningPort());


	@Test
	void contextLoads() {
	}

	@Test
	void employeeEntityTest() {
		employeeRepository.save(
				new Employee(
						null,
						"John Doe",
						LocalDate.of(2023,11,3),
						12.20,
						new Address(null, "New York"),
						Set.of(
								new EmployeeHistory(null, null, LocalDate.now(), null)
						)
				)
		);
		System.out.println(employeeRepository.findAll());
		System.out.println(addressRepository.findAll());

	}

}
