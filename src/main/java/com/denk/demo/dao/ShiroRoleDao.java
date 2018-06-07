package com.denk.demo.dao;

import com.denk.demo.model.ShiroRole;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: denk
 * desc:
 * date: 2018/6/6
 */
public interface ShiroRoleDao extends MongoRepository<ShiroRole, String> {
}