package cn.shh.test.mybatisplus.pojo;

import cn.shh.test.mybatisplus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 注解 @TableId
     *
     * 作用：将属性对应的字段设为主键
     * 属性值：
     *      - value：表和类中字段不一致时，通过value指定表中的字段名。
     *      - type：
     *          - AUTO：自动增长（数据库配置了该字段为自增长，否则无效）
     *          - NONE：自己来设置
     *          - INPUT：自己来设置
     *          - ASSIGN_ID：雪花算法
     *          - ASSIGN_UUID：字符串类型（UUID.tostring().replace("-","")）
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private SexEnum sex;
    private Integer age;
    private String email;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic     // 逻辑删除
    private Integer isDeleted;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
