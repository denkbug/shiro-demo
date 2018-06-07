package com.denk.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author: denk
 * desc:
 * date: 2018/6/6
 */
@Data
public class ShiroRole {
    private String _id;
    private String name;
    List<String> permissions;
}
