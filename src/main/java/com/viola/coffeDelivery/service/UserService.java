package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.User;
import java.util.List;


public interface UserService {
  List<User> listAllUsers ();
  
  User findUserById(int userID);
  
  User findByName(String username);
  
  void saveUser(User user ,int userID);
  
  void updateUser(User user);
  
  void deleteUserById(int userID);
  
  void deleteAllUsers();
  
  boolean isUserExist(User user);
  
  
    
}
