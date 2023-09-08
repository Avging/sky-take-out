package com.avging.mapper;

import com.avging.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工数据
     * @param employee
     */
    @Insert("insert into employee set name = #{name},username = #{username},password = #{password},phone = #{phone},sex = #{sex},id_number = #{idNumber},status = #{status},create_time = #{createTime},update_time = #{updateTime},create_user = #{createUser},update_user = #{updateUser})")
    void insert(Employee employee);
}