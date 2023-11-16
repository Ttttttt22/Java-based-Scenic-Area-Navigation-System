package com.example.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {  //模型层里的实体类
	private String username;  //类属性
	private String password;
	private String realname;
	private String phonenumber;
}
