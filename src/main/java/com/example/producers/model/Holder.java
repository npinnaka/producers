package com.example.producers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Holder {
    private int size;
    private List<Employee> employees;

    public int getSize(){
        return employees.size();
    }
}
