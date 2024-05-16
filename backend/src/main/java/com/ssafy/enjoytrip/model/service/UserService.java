package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;

public interface UserService {
  public User addUser(User user);

  public User selectUserById(User user);

  public boolean checkId(String id);

  public boolean changePW(User user, String pw);

  public boolean delete(User user);

  public void deleteAllUsers();
}
