package met.samitkumar.spring_data_all;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class SpringDataAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataAllApplication.class, args);
	}

}

@Entity
@Builder
record Department(
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long departmentId,
		String name,
		String description,
		@OneToMany(mappedBy = "department")
		Set<Employee> employees) { }

@Entity
@Builder
record Employee(
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long employeeId,
		String name,
		LocalDateTime hireDate,
		String jobTitle,
		Double salary,

		@ManyToOne
		@JoinColumn(name = "department_id")
		Department department,

		@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
		Set<Address> addresses) { }

enum AddressType { HOME, OFFICE() }

@Entity
@Builder
record Address(
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long addressId,
		@Enumerated(EnumType.STRING)
		AddressType addressType,
		String address,
		@ManyToOne
		@JoinColumn(name = "employee_id")
		Employee employee) { }

interface DepartmentRepository extends ListCrudRepository<Department, Long> {}

interface EmployeeRepository extends ListCrudRepository<Employee, Long> {}

interface AddressRepository extends ListCrudRepository<Address, Long> {}