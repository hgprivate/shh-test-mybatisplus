package cn.shh.test.mybatisplus.service.impl;

import cn.shh.test.mybatisplus.mapper.UserMapper;
import cn.shh.test.mybatisplus.pojo.User;
import cn.shh.test.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
