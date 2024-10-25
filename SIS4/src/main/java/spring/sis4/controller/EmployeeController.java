package spring.sis4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.sis4.model.Employee;
import spring.sis4.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Operation(summary = "Get all employees with pagination support")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee List",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "204", description = "No employee found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<Employee>> findAllEmployees(@ParameterObject Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Employee> addOneEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }
}


//http://localhost:8081/swagger-ui.html