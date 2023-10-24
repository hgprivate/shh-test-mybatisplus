package cn.shh.test.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private Integer sexNum;
    private String sexName;
    SexEnum(Integer sexNum, String sexName) {
        this.sexNum = sexNum;
        this.sexName = sexName;
    }
}

/*
public final class SexEnum extends Enum{
    public static SexEnum[] values(){
        return (SexEnum[])$VALUES.clone();
    }
    public static SexEnum valueOf(String name){
        return (SexEnum)Enum.valueOf(cn/shh/test/mybatisplus/enums/SexEnum, name);
    }
    private SexEnum(String s, int i, Integer sexNum, String sexName){
        super(s, i);
        this.sexNum = sexNum;
        this.sexName = sexName;
    }
    public Integer getSexNum(){
        return sexNum;
    }
    public String getSexName(){
        return sexName;
    }

    public static final SexEnum MALE;
    public static final SexEnum FEMALE;
    private Integer sexNum;
    private String sexName;
    private static final SexEnum $VALUES[];

    static {
        MALE = new SexEnum("MALE", 0, Integer.valueOf(1), "\u7537");
        FEMALE = new SexEnum("FEMALE", 1, Integer.valueOf(2), "\u5973");
        $VALUES = (new SexEnum[] {
            MALE, FEMALE
        });
    }
}
 */