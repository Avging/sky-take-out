package com.avging.mapper;

import com.avging.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User getByOpenid(String openid);


    /**
     * 插入数据
     * @param user User
     */
    void insert(User user);
}
