package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.User;

public interface UserService {

  /**
   * Adds a new user to the database.
   *
   * @param user the user to add
   * @return the added user with updated fields (e.g., generated ID)
   */
  User addUser(User user);

  /**
   * Selects a user by their ID.
   *
   * @param user the user with the ID to look for
   * @return the found user or null if not found or if the password is invalid
   */
  User selectUserById(User user);

  /**
   * Checks if a user ID exists in the database.
   *
   * @param id the user ID to check
   * @return true if the ID exists, false otherwise
   */
  boolean checkId(String id);

  /**
   * Changes the password for a user.
   *
   * @param user the user whose password is to be changed
   * @param pw the new password
   * @return true if the password was successfully changed, false otherwise
   */

  /**
   * Deletes a user from the database.
   *
   * @param user the user to delete
   * @return true if the user was successfully deleted, false otherwise
   */
  boolean delete(User user);
  boolean changePW(User user, String newPassword);
  /**
   * Deletes all users from the database.
   */
  void deleteAllUsers();
  public boolean isValidPassword(User inputUser, User storedUser);
  String generateTemporaryPassword(String userId, String name);
}
