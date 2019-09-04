package com.learn.youxiue.mybatis_plus_generator_demo.service.impl;

import com.learn.youxiue.mybatis_plus_generator_demo.entity.User;
import com.learn.youxiue.mybatis_plus_generator_demo.mapper.UserMapper;
import com.learn.youxiue.mybatis_plus_generator_demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youxiue
 * @since 2019-09-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
