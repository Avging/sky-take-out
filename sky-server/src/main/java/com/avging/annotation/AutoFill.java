package com.avging.annotation;

import com.avging.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 */
//指定这个注解只能加在方法上面
@Target(ElementType.METHOD)
//当你需要在运行时根据注解的信息执行自定义操作时，可以使用这个注解来确保注解的信息在运行时可以使用
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //指定这个注解的属性，即指定当前数据库操作的类型
    //OperationType 是属性的类型：UPDATE 和 INSERT
    OperationType value();
}
