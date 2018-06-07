package com.denk.demo.dao;

import com.denk.demo.model.ShiroUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: denk
 * desc:
 * date: 2018/6/6
 */
public interface ShiroUserDao extends MongoRepository<ShiroUser, String> {
    ShiroUser findByUnameAndUpass(String uname, String upass);

    ShiroUser findByUname(String uname);
}
