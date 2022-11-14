package payroll;

import employee.Employee;
import java.math.BigDecimal;

public final class PayrollEntry {

	private final Employee employee;
	private final BigDecimal salaryPlusBonus;
	
	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		this.employee = employee;
		salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
	}

	public BigDecimal getSalaryPlusBonus() {
		return salaryPlusBonus;
	}
}