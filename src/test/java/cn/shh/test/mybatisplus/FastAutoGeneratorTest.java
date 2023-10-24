package cn.shh.test.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码
 */
public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3307/test_elasticsearch?characterEncoding=utf8&serverTimezone=UTC";
        FastAutoGenerator.create(url,"root", "root666")
                        .globalConfig(builder -> {
                            builder.author("haoge") // 设置作者
                                    //.enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("D://tmp//mybatis_plus"); // 指定输出目录
                        }).packageConfig(builder -> {
                            builder.parent("cn.shh.test") // 设置父包名
                                    .moduleName("es") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "D://tmp//mybatis_plus"));
                            // 设置mapperXml生成路径
                        }).strategyConfig(builder -> {
                            builder.addInclude("tb_hotel") // 设置需要生成的表名
                                    .addTablePrefix("tb_", "sys_"); // 设置过滤表前缀
                        }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认是Velocity引擎模板
                        .execute();
    }
}