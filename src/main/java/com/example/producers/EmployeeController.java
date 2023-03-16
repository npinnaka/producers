package com.example.producers;

import com.example.producers.model.Employee;
import com.example.producers.model.Holder;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    private Holder holder;

    private final Faker faker = new Faker();

    public EmployeeController(Holder holder) {
        this.holder = holder;
    }

    @GetMapping(value = "/employees/setup/{counter}")
    public Holder setup(@PathVariable Integer counter) {
        List<Employee> employees = new ArrayList<>();
        for (var i = 0; i < counter; i++) {
            employees.add(new Employee(
                    faker.number().numberBetween(1000, 10000),
                    faker.name().name(),
                    faker.date().birthday()));
        }
        holder.setEmployees(employees);
        return holder;
    }

    @GetMapping(value = "/employees/{empId}")
    public Employee getEmployee(@PathVariable Integer empId) {
        return holder.getEmployees().stream().filter(emp -> emp.getEmpId() == empId)
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .orElse(null);
    }

    @GetMapping(value = "/employees")
    public Holder getEmployees() {
        return holder;
    }

    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee getEmployee(@RequestBody Employee emp) {
        holder.getEmployees().add(emp);
        Employee tmp = new Employee();
        tmp.setEmpId(emp.getEmpId());
        return tmp;
    }
}
