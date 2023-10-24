package cn.shh.test.mybatisplus;

import cn.shh.test.mybatisplus.enums.SexEnum;
import cn.shh.test.mybatisplus.mapper.UserMapper;
import cn.shh.test.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusEnumTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSexEnum(){
        User user = new User();
        user.setName("Enum");
        user.setAge(20);
        // 设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);
        // INSERT INTO t_user ( username, age, sex ) VALUES ( ?, ?, ? )
        // Parameters: Enum(String), 20(Integer), 1(Integer)
        int result = userMapper.insert(user);
        System.out.println("result: " + result);
    }
}