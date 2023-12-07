package com.avging.mapper;

import com.avging.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User getByOpenid(String openid);


    /**
     * 插入数据
     * @param user User
     */
    void insert(User user);

    @Select("SELECT * from user WHERE id = #{id}")
    User getById(Long userId);


    /**
     * 根据动态条件统计用户数量
     * @param map Map
     * @return Integer
     */
    Integer countByMap(Map map);
}
