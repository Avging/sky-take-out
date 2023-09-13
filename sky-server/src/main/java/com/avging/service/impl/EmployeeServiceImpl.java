package com.avging.service.impl;

import com.avging.constant.MessageConstant;
import com.avging.constant.PasswordConstant;
import com.avging.constant.StatusConstant;
import com.avging.context.BaseContext;
import com.avging.dto.EmployeeDTO;
import com.avging.dto.EmployeeLoginDTO;
import com.avging.dto.EmployeePageQueryDTO;
import com.avging.entity.Employee;
import com.avging.exception.AccountLockedException;
import com.avging.exception.AccountNotFoundException;
import com.avging.exception.PasswordErrorException;
import com.avging.mapper.EmployeeMapper;
import com.avging.result.PageResult;
import com.avging.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) throws AccountLockedException, AccountNotFoundException {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
           // throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 将前端传过来的明文密码进行md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    //新增员工
    public void save(EmployeeDTO employeeDTO) {
        System.out.println("当前线程的id:" + Thread.currentThread().getId());
        Employee employee = new Employee();

        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO,employee);//把employeeDTO里的数据拷贝给employee

        //设置账号的状态，默认状态1表示正常0表示锁定
        employee.setStatus(StatusConstant.ENABLE);//值调用常量类里的常量属性，后期若更改只需要修改常量类中的值即可

        //设置密码，默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置当前记录的创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置当前记录创建人id和修改人id
        // TODO 后期需要改为当前登录用户的id
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //SELECT * FROM employee limit 0,10
        //开始分页查询//这个插件底层是基于MyBatis的拦截器，会动态的把limit拼进去，并把两个数据参数动态的计算
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());//页码和每页的记录数
        //Page本质上是一个ArrayList集合
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(total,records);
    }


    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        // update employee set status = ? where id = ?

//        Employee employee = new Employee();
//        employee.setStatus(status);
//        employee.setId(id);
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }


    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("****");
        return employee;
    }


    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employeeMapper.update();
    }
}