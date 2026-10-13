package com.ms.practice.shoppingWithJwt.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.practice.shoppingWithJwt.dto.EmployeeDto;

@RestController
@RequestMapping("/emp")
public class EmployeeApi {
	
	@GetMapping(value = "/empDetails/{empId}")
	public EmployeeDto getEmpDetails(@PathVariable(name = "empId") int empId) {
		return new EmployeeDto(empId, "AK");
	}
}
