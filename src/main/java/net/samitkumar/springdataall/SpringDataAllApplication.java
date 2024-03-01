package net.samitkumar.springdataall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class SpringDataAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataAllApplication.class, args);
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
@Table("employee_history")
record EmployeeHistory(@Id Long historyId, Long employeeId, LocalDate startDate, LocalDate endDate) { }
record Address(@Id Long addressId, String location) { }

interface AddressRepository extends ListCrudRepository<Address, Long> { }
interface EmployeeRepository extends ListCrudRepository<Employee, Long> { }

interface EmployeeHistoryRepository extends ListCrudRepository<EmployeeHistory, Long> { }
