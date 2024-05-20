package com.ssafy.enjoytrip.model.dao;

//import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.User;

//@Mapper
public interface UserDao {

	public int addUser(User user);

	public User selectUserById(User user);

	public boolean checkId(String id);

	public boolean changePW(User user);

	public boolean delete(User user);

    public void deleteAllUsers();
}
