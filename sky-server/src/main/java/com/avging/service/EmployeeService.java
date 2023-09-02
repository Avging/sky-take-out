package com.avging.service;

import com.avging.dto.EmployeeLoginDTO;
import com.avging.entity.Employee;
import com.avging.exception.AccountLockedException;
import com.avging.exception.AccountNotFoundException;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO) throws AccountLockedException, AccountNotFoundException;

}
