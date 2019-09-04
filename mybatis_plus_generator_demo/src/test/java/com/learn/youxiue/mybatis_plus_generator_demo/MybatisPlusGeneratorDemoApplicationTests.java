package com.learn.youxiue.mybatis_plus_generator_demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.learn.youxiue.mybatis_plus_generator_demo.config.MybatisConfig;
import com.learn.youxiue.mybatis_plus_generator_demo.entity.User;
import com.learn.youxiue.mybatis_plus_generator_demo.mapper.UserMapper;
import com.learn.youxiue.mybatis_plus_generator_demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusGeneratorDemoApplicationTests {

    private static String author = "youxiue"; //作者名称
    private static String outputDir = "D:\\my_work\\mybatis_plus\\mybatis_plus_generator_demo\\src\\main\\java"; // 文件生成的位置
    private static String driver = "com.mysql.cj.jdbc.Driver"; //驱动 注意版本
    private static String url = "jdbc:mysql://localhost:3306/mp?useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai"; //数据库连接路径 ,
    private static String username = "root"; // 数据库用户名
    private static String password = "root"; //数据库密码
    private static String tablePrefix = "tbl_"; //数据库表的前缀
    private static String[] tables = {"tbl_user"}; //生成哪些表
    private static String parentPackage = "com.learn.youxiue.mybatis_plus_generator_demo"; //顶级包的结构
    private static String dao = "mapper"; //数据访问层包名称
    private static String service = "service"; //业务逻辑层包名称
    private static String entity = "entity"; //实体层包名称
    private static String controller = "controller"; //控制层包名称
    private static String mapperxml = "mapper"; //mapper映射文件包名称

    @Test
    public void contextLoads() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir(outputDir); //生成文件的输出目录
        gc.setAuthor(author); //开发人员
        gc.setFileOverride(true); //是否覆盖已有文件
        gc.setOpen(true); //是否打开输出目录
        gc.setIdType(IdType.AUTO) //主键生成策略
//                .setServiceName("%sService") //设置生成的service接口的名称, %s为占位符, 例如User -> UserService
                .setBaseResultMap(true) // 映射文件中是否生成ResultMap 配置
                .setBaseColumnList(true); // 生成通用sql字段
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driver);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);


        // 数据库表策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体类的命名策略
                .setTablePrefix(tablePrefix) // 表前缀
                .setEntityLombokModel(true) //是否为lombok模型
                .setEntityTableFieldAnnotationEnable(true) //生成实体类时是否生成注解
                .setRestControllerStyle(true) //生成@RestController 控制器
                .setControllerMappingHyphenStyle(true) //驼峰转连字符

                //继承公共父类 需要自己定义, mybatisPlus 并没有提供
//                .setSuperEntityClass("com.baomidou.ant.common.BaseEntity")
//                .setSuperControllerClass("com.baomidou.ant.common.BaseController")
                // 写于父类中的公共字段
//                .setSuperEntityColumns("id")


//                .setExclude();
                .setInclude(tables); //生成的表
        mpg.setStrategy(sc);


        // 包名策略配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage)
                .setMapper(dao)
                .setService(service)
                .setController(controller)
                .setEntity(entity);
//                .setXml(mapperxml);
        mpg.setPackageInfo(pc);


        //执行
        mpg.execute();


    }


    @Autowired
    private IUserService userService;

    @Test
    public void deleteUser(){
        boolean b = userService.removeById(1087982257332887553L);
        System.out.println(b?"逻辑删除成功":"逻辑删除失败");
    }

    @Test
    public void selectUser(){
        //被置为删除的 数据 会自动过滤掉
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void selectById(){
        //设置表名为user_2019
        MybatisConfig.myTableName.set("user_2019");

        User user = userService.getById(1094592041087729668L);
        System.out.println(user);
    }

    /*@Test
    public void selectByName(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(User::getAge,28);
        List<User> list = userService.mySelectList(wrapper);
        list.forEach(System.out::println);
    }*/

    @Test
    public void updateTime(){
        User user = new User();
        user.setName("wjj")
                .setId(1094592041087729669L)
                .setAge(99)
                .setEmail("wjj@youxiue.com");
        userService.updateById(user);
    }

    @Test
    public void insertTime(){
        User user = new User();
        user.setName("挖掘机")
                .setAge(36)
                .setEmail("lms@youxiue.com")
                .setManagerId(1088248166370832385L)
                .setVersion(1);
        boolean b = userService.save(user);
    }


    @Test
    public void updateVersion(){
        int version = 1;

        User u = new User();
        u.setId(1094592041087729669L);
//        u.setVersion(5);
        u.setName("qqqqqqqqqqq");

        if(userService.updateById(u)){
            System.out.println("Update successfully");
        }else{
            System.out.println("Update failed due to modified by others");
        }

    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteAll(){
        int i = userMapper.deleteAll();
    }


    @Test
    public void insertBatch(){
        User user = new User();
        user.setName("厉害")
                .setAge(23)
                .setManagerId(1088248166370832385L);

        User user1 = new User();
        user1.setName("2222")
                .setAge(544)
                .setManagerId(1088248166370832385L);

        List<User> users = Arrays.asList(user, user1);
        userMapper.insertBatchSomeColumn(users);
    }
}
