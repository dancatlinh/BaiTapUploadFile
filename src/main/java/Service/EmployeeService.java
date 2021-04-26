package Service;

import Model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public void save(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Employee findById(int id) {
        return employees.get(id);
    }

    @Override
    public void update(int id, Employee employee) {
        for (Employee p : employees) {
            if (p.getIdEmployee() == id) {
                p = employee;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdEmployee() == id) {
                employees.remove(i);
                break;
            }
        }
    }
}
