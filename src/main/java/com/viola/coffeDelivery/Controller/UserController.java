package com.viola.coffeDelivery.Controller;

import com.viola.coffeDelivery.CustomError;
import com.viola.coffeDelivery.model.User;
import com.viola.coffeDelivery.service.UserServiceImp;
import java.util.List;
import static org.apache.tomcat.jni.User.username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {
    
    @Autowired 
    UserServiceImp userService;
   
    
 //-------------retrieve all users -------------------//
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<User>> getAllUsers(){
   List<User> usersList = userService.listAllUsers();
   if (usersList.isEmpty())
   {
   return new ResponseEntity(HttpStatus.NO_CONTENT);
   }
   return new ResponseEntity<List<User>>( usersList,HttpStatus.OK);
   }
   
//-------------retrieve an user  by ID-------------------//
   @RequestMapping(value ="{id}",method= RequestMethod.GET)
   public ResponseEntity<?> getUserById(@PathVariable("id")int userID)
   {
   User user = userService.findUserById(userID);
   if(user == null)
   {
       return new ResponseEntity(new CustomError("User with id " + userID
                    + " not found"), HttpStatus.NOT_FOUND);
   }
    return new ResponseEntity<User>(user,HttpStatus.OK);
   }
   
   //-------------create an user  by ID-------------------//
     @RequestMapping(value = "create/{id}",method=RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody User user)
    {
    
    if(userService.isUserExist(user))
    {
    return new ResponseEntity(new CustomError("Unable to create order with id"+user.getUserID()+" .Order already exists"),HttpStatus.CONFLICT );
    }
        userService.saveUser(user,user.getUserID());
        return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }
   
    //------------- update an user by id -------------------//
   
   @RequestMapping(value ="update/{id}",method= RequestMethod.POST)
   public ResponseEntity<?> updateUser(@PathVariable("id")int userID,@RequestBody User user)
   {
       User currentUser = userService.findUserById(userID);
       if(currentUser == null)
       {
       return new ResponseEntity(new CustomError("Can not update user with id "+user.getUserID() +"user does not exist"),HttpStatus.NOT_FOUND);
       }
       currentUser.setUserID(user.getUserID());
       currentUser.setUsername(user.getUsername());
       currentUser.setEmail(user.getEmail());
       currentUser.setPassword(user.getPassword());
       userService.updateUser(currentUser);
       
       return new ResponseEntity<User>(user,HttpStatus.OK);
   }
   
      //-------------delete an user by id -------------------//
   
   @RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
   public ResponseEntity<?> deleteUserById(@PathVariable("id")int userID)
   {
       User user = userService.findUserById(userID);
       if(user== null)
       {
       return new ResponseEntity(new CustomError("Unable to delete user with id "+ userID +"user not found"),HttpStatus.NOT_FOUND);
       }
           
       userService.deleteUserById(userID);
       
       return new ResponseEntity<User>(user,HttpStatus.OK);
   }
   
   
   
   //------------- delete all users  -------------------//
    @RequestMapping(value="/delete",method=RequestMethod.DELETE)
   public ResponseEntity<?> deleteAllUsers(User user)
   {
   
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
   
   }
   
}
   
