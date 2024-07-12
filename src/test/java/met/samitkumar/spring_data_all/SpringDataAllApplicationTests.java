package met.samitkumar.spring_data_all;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringDataAllApplicationTests {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AddressRepository addressRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void departmentRepositoryTest() {
		var department = new Department(null, "IT", "Information Technology", Set.of());

		var newDepartment = departmentRepository.save(department);
		assertNotNull(newDepartment);
		System.out.println(newDepartment);

		var departmentFromDb = departmentRepository.findById(newDepartment.departmentId());
		System.out.println(departmentFromDb);

		var employee = employeeRepository.findAll();
		System.out.println(employee);

		var address = addressRepository.findAll();
		System.out.println(address);
	}

/*	@Test
	void employeeRepositoryTest() {
		var department = departmentRepository.save(new Department(null, "IT", "Information Technology", null));

		var employee = new Employee(null, "Samit", LocalDateTime.now(), "Developer", 1000.00, department.departmentId(),
				Set.of(
						new Address(null, null, AddressType.HOME, "A B Street, Delhi, India"),
						new Address(null, null, AddressType.OFFICE, "VIP Street, Delhi, India")
				)
		);

		var newEmployee = employeeRepository.save(employee);
		assertNotNull(newEmployee);
		System.out.println(newEmployee);

		var address = addressRepository.findAll();
		System.out.println(address);
	}

	@Test
	void addressRepositoryTest() {
		var department = departmentRepository.save(new Department(null, "IT", "Information Technology", null));

		var employee = employeeRepository.save(new Employee(null, "Samit", LocalDateTime.now(), "Developer", 1000.00, department.departmentId(), null));

		var address = new Address(null, employee.employeeId(), AddressType.HOME, "A B Street, Delhi, India");
		var address1 = new Address(null, employee.employeeId(), AddressType.OFFICE, "VIP Street, Delhi, India");

		var newAddress = addressRepository.saveAll(List.of(address, address1));
		assertNotNull(newAddress);
		System.out.println(newAddress);
	}*/

}
