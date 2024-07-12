package met.samitkumar.spring_data_all;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringDataAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataAllApplication.class, args);
	}

}

record Department(
		@Id
		Long departmentId,
		String name,
		String description,
		@MappedCollection(idColumn = "department_id")
		Set<Employee> employees) { }

record Employee(
		@Id
		Long employeeId,
		String name,
		LocalDateTime hireDate,
		String jobTitle,
		Double salary,
		Long departmentId,
		@MappedCollection(idColumn = "employee_id")
		Set<Address> addresses) { }

enum AddressType { HOME, OFFICE() }

record Address(
		@Id Long addressId,
		Long employeeId,
		AddressType addressType,
		String address) { }

interface DepartmentRepository extends ListCrudRepository<Department, Long> {}

interface EmployeeRepository extends ListCrudRepository<Employee, Long> {}

interface AddressRepository extends ListCrudRepository<Address, Long> {}