package com.example.producers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @JsonProperty("employee_id")
    private int empId;
    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("hire_date")
    private Date hireDate;
}
