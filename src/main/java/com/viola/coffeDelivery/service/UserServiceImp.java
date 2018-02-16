package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImp implements UserService {
    
    private static final AtomicLong counter = new AtomicLong();
    private static List<User>users  = new ArrayList<>();
    static {  users = populateUsers();}
    
    public List<User> listAllUsers()
    {
    return users;
    }
     @Override
    public User findUserById(int  userID) {
        for(User user:users){
           
            if(user.getUserID()== userID)
            return user;
        }
        return null;
    }
    
     @Override
    public User findByName(String username) {
        for(User user:users){
        if( user.getUsername()== username)
            return user;
    }
    return null;
    }

     @Override
    public void saveUser(User user,int userID) {
    user.setUserID((int) counter.incrementAndGet());
    users.add(user);    }

   
    @Override
    public void updateUser(User user)
    {
    int index = users.indexOf(user);
    users.add(index, user);
    }
  
    
    @Override
    public void deleteUserById(int userID) 
    {
    for(Iterator<User>iterator = users.iterator();iterator.hasNext();)
        {
            User user = iterator.next();
            if(user.getUserID()== userID)
                iterator.remove();
        
        }     
    }
 
    
    @Override
    public void deleteAllUsers()
    {
    users.clear();
    }
    
    @Override
    public boolean isUserExist(User user )
    {
        
        return findByName(user.getUsername())!= null;
    }
    
    private static List<User> populateUsers()
    {
    List<User> users = new ArrayList<>();
    users.add(new User ((int) counter.incrementAndGet(),"John","john@cit.edu.al","John1"));
    users.add(new User ((int) counter.incrementAndGet(),"Jane","jane@cit.edu.al","Jane1"));
    users.add(new User ((int) counter.incrementAndGet(),"Sam","sam@cit.edu.al","Sam1"));
    users.add(new User ((int) counter.incrementAndGet(),"Beni","beni@cit.edu.al","Beni1"));
    users.add(new User ((int) counter.incrementAndGet(),"Ana","ana@cit.edu.al","Ana1"));
    return users;
    
   } 

   

  
 
    
}
