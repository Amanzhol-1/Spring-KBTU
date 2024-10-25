package spring.sis3.part3;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee addOneEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
