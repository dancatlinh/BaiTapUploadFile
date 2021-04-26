package Service;

import Model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();

    void save(Employee employee);

    Employee findById(int idEmployee);

    void update(int idEmployee, Employee employee);

    void remove(int idEmployee);
}
