package cn.shh.test.mybatisplus.mapper;

import cn.shh.test.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    Page<User> selectByAge(@Param("page") Page<User> page, @Param("age") Integer age);
}