package com.learn.youxiue.mybatis_plus_generator_demo.mapper;

import com.learn.youxiue.mybatis_plus_generator_demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youxiue
 * @since 2019-09-04
 */
/*public interface UserMapper extends BaseMapper<User> {

    int deleteAll();
}*/

public interface UserMapper extends MyMapper<User>{

}
