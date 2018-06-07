package com.denk.demo.dao;

import com.denk.demo.model.ShiroPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: denk
 * desc:
 * date: 2018/6/6
 */
public interface ShiroPermissionDao extends MongoRepository<ShiroPermission, String> {
}
