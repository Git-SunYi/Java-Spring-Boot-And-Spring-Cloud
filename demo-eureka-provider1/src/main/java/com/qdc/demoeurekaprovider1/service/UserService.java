package com.qdc.demoeurekaprovider1.service;

import com.qdc.demoeurekaprovider1.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public User selectUserById(String id){
        return new User("aaa","123","zlp");
    }

    public List<User> selectAllUsers(){
        List<User> users=new ArrayList<>();
        User u1 = new User("test1","123","zlp");
        User u2 = new User("test2","123","zlp");
        users.add(u1);
        users.add(u2);
        return users;
    }
    public boolean addUser(User user){
        return true;
    }
    public boolean updateUser(User user){
        return true;
    }
    public boolean deleteUser(String name){
        return true;
    }
}
