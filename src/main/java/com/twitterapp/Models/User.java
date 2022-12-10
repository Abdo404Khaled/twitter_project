package com.twitterapp.Models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private List<Integer> followers;
    private List<Integer> following;

    public User(int id){
        this.id = id;
        followers = new ArrayList<>(0);
        following = new ArrayList<>(0);
    }

    public int get_followers()
    {
        return followers.size();
    }

    public int get_following()
    {
        return following.size();
    }

    public int get_id()
    {
        return id;
    }

    public void add_follower(User user)
    {
        followers.add(user.get_id());
    }

    public void follow(User user)
    {
        following.add(user.get_id());
    }

    @Override
    public boolean equals(Object o) {
  
        if (o == this) {
            return true;
        }

        if (!(o instanceof User))
            return false;

        
        User c = (User) o;

        return id == c.id;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return "Account ID: " + id + " Followers: " + followers.size() + " Following: " + following.size();
    }
    
}
