package cn.shh.test.mybatisplus;

import cn.shh.test.mybatisplus.mapper.UserMapper;
import cn.shh.test.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * mapper.selectList(QueryWrapper)
     */
    @Test
    public void test01(){
        // SELECT id,name,sex,age,email,create_time,update_time,is_deleted,version
        // FROM sys_user WHERE is_deleted=0 AND (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "s").between("age", 20, 30).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * mapper.selectList(QueryWrapper)
     */
    @Test
    public void test02(){
        // SELECT id,name,sex,age,email,create_time,update_time,is_deleted,version
        // FROM sys_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * mapper.delete(QueryWrapper)
     */
    @Test
    public void test03(){
        // UPDATE sys_user SET is_deleted=1 WHERE is_deleted=0 AND (age IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.isNull("age");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result: " + result);
    }

    /**
     * mapper.update(QueryWrapper)
     *
     * UPDATE sys_user SET age=?, email=?, update_time=?
     * WHERE is_deleted=0 AND (name LIKE ? AND age > ? OR email IS NULL)
     */
    @Test
    public void test04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").gt("age", 20).or().isNull("email");
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    /**
     * mapper.update(QueryWrapper)
     *
     * UPDATE sys_user SET age=?, email=?, update_time=?
     * WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
     */
    @Test
    public void test05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    /**
     * mapper.selectMaps(QueryWrapper)
     *
     * SELECT name,age FROM sys_user WHERE is_deleted=0
     */
    @Test
    public void test06() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * mapper.selectList(QueryWrapper)
     *
     * SELECT id,name,sex,age,email,create_time,update_time,is_deleted,version
     * FROM sys_user WHERE is_deleted=0 AND (id IN (select id from sys_user where id <= 3))
     */
    @Test
    public void test07() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from sys_user where id <= 3");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * mapper.update(UpdateWrapper)
     *
     * UPDATE sys_user SET age=?,email=?
     * WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
     */
    @Test
    public void test08() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age", 18).set("email", "user@atguigu.com")
                .like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    /**
     * mapper.selectList(QueryWrapper)
     *
     * SELECT id,name,sex,age,email,create_time,update_time,is_deleted,version
     * FROM sys_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
     */
    @Test
    public void test09() {
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username","a");
        }
        if(ageBegin != null){
            queryWrapper.ge("age", ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.le("age", ageEnd);
        }
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * mapper.selectList(LambdaQueryWrapper)
     *
     * SELECT id,name,sex,age,email,create_time,update_time,is_deleted,version
     * FROM sys_user WHERE is_deleted=0 AND (name LIKE ? AND age >= ? AND age <= ?)
     */
    @Test
    public void test10() {
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * mapper.update(LambdaUpdateWrapper)
     *
     * UPDATE sys_user SET update_time=?, age=?, email=?
     * WHERE is_deleted=0 AND (name LIKE ? AND (age < ? OR email IS NULL))
     */
    @Test
    public void test11() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getAge, 18).set(User::getEmail, "user@atguigu.com")
                .like(User::getName, "a")
                .and(i -> i.lt(User::getAge, 24).or().isNull(User::getEmail));
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println("受影响的行数：" + result);
    }

    /**
     * mapper.selectPage(Page)
     *
     * SELECT COUNT(*) AS total FROM sys_user WHERE is_deleted = 0
     *
     * SELECT id,name,sex,age,email,create_time,update_time,is_deleted,version
     * FROM sys_user WHERE is_deleted=0 LIMIT ?
     */
    @Test
    public void testPage(){
        Page<User> userPage = new Page<>(1,3);
        userMapper.selectPage(userPage, null);
        System.out.println("当前页：" + userPage.getCurrent());  // 当前页
        System.out.println("当前页数据：" + userPage.getRecords());  // 每页的数据
        System.out.println("当前页数据数量：" + userPage.getSize());     // 每页记录数量
        System.out.println("总数据数量：" + userPage.getTotal());    // 总记录数量
        System.out.println("总页数量：" + userPage.getPages());    // 总页数量
        System.out.println("是否有下一页：" + userPage.hasNext());     // 下一页
        System.out.println("是否有上一页：" + userPage.hasPrevious()); // 上一页
    }
}