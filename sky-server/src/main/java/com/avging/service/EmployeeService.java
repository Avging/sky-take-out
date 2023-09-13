package com.avging.service;

import com.avging.dto.EmployeeDTO;
import com.avging.dto.EmployeeLoginDTO;
import com.avging.dto.EmployeePageQueryDTO;
import com.avging.entity.Employee;
import com.avging.exception.AccountLockedException;
import com.avging.exception.AccountNotFoundException;
import com.avging.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO) throws AccountLockedException, AccountNotFoundException;

    //新增员工

    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
