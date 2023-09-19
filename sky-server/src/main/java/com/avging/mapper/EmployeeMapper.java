package com.avging.mapper;

import com.avging.annotation.AutoFill;
import com.avging.dto.EmployeePageQueryDTO;
import com.avging.entity.Employee;
import com.avging.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username String
     * @return Employee
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工数据
     * @param employee Employee
     */
    @Insert("INSERT INTO employee (name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user) VALUES (#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    //@Insert("INSERT INTO employee SET name = #{name},username = #{username},password = #{password},phone = #{phone},sex = #{sex},id_number = #{idNumber},status = #{status},create_time = #{createTime},update_time = #{updateTime},create_user = #{createUser},update_user = #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);

    /**
     * 分页查询
     * @param employeePageQueryDTO EmployeePageQueryDTO
     * @return Page<Employee>
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据主键动态修改属性
     * @param employee Employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    /**
     * 根据id查询员工信息
     * @param id Long
     * @return Employee
     */
    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee getById(Long id);
}