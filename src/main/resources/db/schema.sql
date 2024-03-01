CREATE TABLE IF NOT EXISTS employees (
    employee_id SERIAL PRIMARY KEY,
    employee_name VARCHAR(50) NOT NULL,
    hire_date DATE NOT NULL,
    salary DECIMAL(15, 2)
);

CREATE TABLE IF NOT EXISTS address (
   address_id SERIAL PRIMARY KEY,
   employee_id INT NOT NULL REFERENCES employees(employee_id),
   location VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_history (
   history_id SERIAL PRIMARY KEY,
   employee_id INT NOT NULL REFERENCES employees(employee_id),
   start_date DATE NOT NULL,
   end_date DATE
);