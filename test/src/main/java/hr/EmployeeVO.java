package hr;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeVO {
	private int employee_id,salary;
	private String first_name,last_name,email,phone_number,job_id,department_id,manager_id;
	private Date hire_date;
	private String name,job_title,department_name,manager_name;
}
