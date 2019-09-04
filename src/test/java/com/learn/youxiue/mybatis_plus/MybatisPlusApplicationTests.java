package com.learn.youxiue.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.youxiue.mybatis_plus.entity.Employee;
import com.learn.youxiue.mybatis_plus.entity.User;
import com.learn.youxiue.mybatis_plus.mapper.EmployeeMapper;
import com.learn.youxiue.mybatis_plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        if (users != null && users.size() > 0) {
            users.forEach(System.out::println);
        }
    }


    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void addTest() {
        Employee employee = new Employee();
        employee.setUserName("BOBO")
                .setEmail("bobo@youxiue.com")
                .setAge(24)
                .setGender(0);
        employeeMapper.insert(employee);
    }

    @Test
    public void queryTest() {
        List<Employee> employees = employeeMapper.selectList(null);
        if (employees != null && employees.size() > 0) {
            employees.forEach(System.out::println);
        }
    }

    @Test
    public void selectOneTest() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        // 如果查询到多条将会报错
        //Expected one result (or null) to be returned by selectOne(), but found: 5
        Employee employee = employeeMapper.selectOne(wrapper);
        System.out.println(employee);
    }

    @Test
    public void selectMapsTest() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "o");

        //查询结果返回的是 字段映射对象 Map
        List<Map<String, Object>> list = this.employeeMapper.selectMaps(wrapper);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        }
        /**
         * {gender=1, user_name=Tom, id=9, age=44, email=tom@youxiue.com}
         * {gender=1, user_name=DIO, id=11, age=44, email=dio@youxiue.com}
         * {gender=0, user_name=JOJO, id=12, age=44, email=jojo@youxiue.com}
         */

    }

    @Test
    public void deleteTest() {
        int i = employeeMapper.deleteById(5);
        if (i > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        List<Employee> employees = employeeMapper.selectList(null);
        if (employees != null && employees.size() > 0) {
            employees.forEach(System.out::println);
        }
    }

    @Test
    public void deleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 6);
        // key为表中的字段名称, 不是实体类的属性名
        map.put("user_name", "bobo");

        int i = employeeMapper.deleteByMap(map);
        if (i > 0) {
            System.out.println("删除成功" + i);
        } else {
            System.out.println("删除失败" + i);
        }
    }


    @Test
    public void delete() {
        // 条件构造器
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "bobo");

        int i = this.employeeMapper.delete(wrapper);
        if (i > 0) {
            System.out.println("删除成功" + i);
        } else {
            System.out.println("删除失败" + i);
        }
    }


    @Test
    public void deleteBatchIdsTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        //DELETE FROM tbl_employee WHERE id IN ( ? , ? , ? )
        int i = this.employeeMapper.deleteBatchIds(list);
        if (i > 0) {
            System.out.println("删除成功" + i);
        } else {
            System.out.println("删除失败" + i);
        }
    }

    @Test
    public void updateByIdTest() {
        Employee employee = new Employee();
        employee.setAge(25)
                .setEmail("123344@youxiue.com")
                .setId(10);
        int i = this.employeeMapper.updateById(employee);
        if (i > 0) {
            System.out.println("修改成功" + i);
        } else {
            System.out.println("修改失败" + i);
        }
    }


    @Test
    public void updateTest() {
        Employee employee = new Employee();
        employee.setAge(44);

//        UpdateWrapper<Employee> wrapper = new UpdateWrapper<Employee>();
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "o");
        int i = this.employeeMapper.update(employee, wrapper);
        if (i > 0) {
            System.out.println("修改成功" + i);
        } else {
            System.out.println("修改失败" + i);
        }
    }

    @Test
    public void testFindEmpList() {
        List<Employee> list = this.employeeMapper.findEmployeeList();
        list.forEach(System.out::println);
    }


    @Test
    public void findPageTest() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.gt("id", 9);
        //执行 当前页码 以及每页查询数量
        Page<Employee> page = new Page<>(1, 2);
        IPage<Employee> pageUser = this.employeeMapper.selectPage(page, wrapper);
        System.out.println("总页数:" + pageUser.getPages());
        System.out.println("当期页码:" + pageUser.getCurrent());
        System.out.println("每页显示数量:" + pageUser.getSize());
        System.out.println("总记录数:" + pageUser.getTotal());
        List<Employee> list = pageUser.getRecords();
        if (list != null && list.size() > 0) {
            list.forEach(System.out::println);
        }
    }

    //自定义sql 的分页查询
    @Test
    public void findPage2Test() {
        //执行 当前页码 以及每页查询数量
        Page<Employee> page = new Page<>(1, 2);
        String gender = "1";
        System.out.println("执行前Page对象: " + page); // com.baomidou.mybatisplus.extension.plugins.pagination.Page@534e58b6
        // 分页返回的对象与传入的对象是同一个
        IPage<Employee> pageVo = this.employeeMapper.selectPageVo(page, gender);
        System.out.println("执行后Page对象:" + pageVo); //com.baomidou.mybatisplus.extension.plugins.pagination.Page@534e58b6
        System.out.println("总页数:" + pageVo.getPages());
        System.out.println("当期页码:" + pageVo.getCurrent());
        System.out.println("每页显示数量:" + pageVo.getSize());
        System.out.println("总记录数:" + pageVo.getTotal());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("是否有上一页:" + page.hasPrevious());
        List<Employee> list = pageVo.getRecords();
        if (list != null && list.size() > 0) {
            list.forEach(System.out::println);
        }
    }

    @Test
    public void deleteAll() {
        int delete = employeeMapper.delete(null);
        if (delete > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    @Test
    public void deleteUserAll() {
        int delete = userMapper.delete(null);
        if (delete > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }


    /**
     * AR模式
     */
    @Test
    public void insertModel() {
        Employee employee = new Employee();
        employee.setUserName("tina")
                .setEmail("tina@youxiue.com")
                .setGender(0)
                .setAge(18);
        boolean flag = employee.insert();
        System.out.println(flag?"添加成功":"添加失败");
    }
    @Test
    public void selectById(){
        Employee employee = new Employee();
//        Employee selectEmployee = employee.selectById(13);
        //或者
        employee.setId(13);
        Employee selectEmployee = employee.selectById();
        System.out.println(selectEmployee);
    }

    @Test
    public void updateById(){
        Employee employee = new Employee();
        employee.setId(12)
                .setUserName("heihei");
        boolean flag = employee.updateById();
        System.out.println(flag?"修改成功":"修改失败");
    }

    @Test
    public void insertOrUpdate(){
        Employee employee = new Employee();
        employee.setUserName("lisa")
                .setId(15)
                .setEmail("lisa@youxiue.com")
                .setGender(1)
                .setAge(15);
        //如果不设置id 或该id没有对应的数据 则执行插入,  否则执行修改
        boolean flag = employee.insertOrUpdate();
        System.out.println(flag?"修改成功":"修改失败");
    }

    @Test
    public void deleteById() {
        Employee employee = new Employee();
        employee.setId(15);
        //删除不存在的数据时逻辑上属于成功, 判断逻辑为: null != result && result >= 0;
        boolean flag = employee.insertOrUpdate();
        System.out.println(flag?"删除成功":"删除失败");
    }
}
