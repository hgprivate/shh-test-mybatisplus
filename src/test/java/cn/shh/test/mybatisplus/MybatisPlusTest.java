package cn.shh.test.mybatisplus;

import cn.shh.test.mybatisplus.mapper.UserMapper;
import cn.shh.test.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询
     */
    @Test
    public void testSelectList(){
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

    /**
     * 新增
     */
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("李四");
        user.setEmail("lisi@atguigu.com");
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userMapper.insert(user);
        System.out.println(result > 0 ? "success " + result : "error " + result);
        //1498965861758377985
        System.out.println("自动生成的ID：" + user.getId());
    }

    /**
     * 删除
     */
    @Test
    public void testDeleteById() {
        //通过id删除用户信息
        //DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1);
        System.out.println(result > 0 ? "success " + result : "error " + result);
    }

    /**
     * 修改
     */
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(2L);
        user.setAge(28);
        //UPDATE user SET name=?, age=? WHERE id=?
        int result = userMapper.updateById(user);
        System.out.println(result > 0 ? "success " + result : "error " + result);
    }

    @Test
    public void testVersion(){
        User user = userMapper.selectById(1523578458276397058L);
        user.setAge(18);
        int result = userMapper.updateById(user);
        System.out.println(result > 0 ? "success " + result : "error " + result);
    }
}
