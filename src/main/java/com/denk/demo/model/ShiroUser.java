package com.denk.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class ShiroUser {
    private String _id;
    private String uname;
    private String upass;
    private List<String> roles;
}
