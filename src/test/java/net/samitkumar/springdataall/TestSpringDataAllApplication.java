package net.samitkumar.springdataall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.util.Set;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringDataAllApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
	}

	public static void main(String[] args) {
		SpringApplication.from(SpringDataAllApplication::main).with(TestSpringDataAllApplication.class).run(args);
	}
}
@Table("employees")
record Employee(
		@Id Long employeeId,
		String employeeName,
		LocalDate hireDate,
		Double salary,
		@MappedCollection(idColumn = "employee_id") Address address,
		@MappedCollection(idColumn = "employee_id") Set<EmployeeHistory> employeeHistories) {
}
record Address(@Id Long addressId, String location) { }
@Table("employee_history")
record EmployeeHistory(@Id Long historyId, Long employeeId, LocalDate startDate, LocalDate endDate) { }
interface EmployeeRepository extends ListCrudRepository<Employee, Long> { }
interface AddressRepository extends ListCrudRepository<Address, Long> { }
interface EmployeeHistoryRepository extends ListCrudRepository<EmployeeHistory, Long> { }