CREATE TABLE IF NOT EXISTS department (
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS employee (
      employee_id SERIAL PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      hire_date TIMESTAMP NOT NULL,
      job_title VARCHAR(100) NOT NULL,
      salary NUMERIC(15, 2),
      department_id INTEGER NOT NULL,
      FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS address (
     address_id SERIAL PRIMARY KEY,
     employee_id INTEGER NOT NULL,
     address VARCHAR(150) NOT NULL,
     address_type VARCHAR(50) NOT NULL, -- e.g., 'home', 'work'
     FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);
