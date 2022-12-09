package com.twitterapp.Models;

import java.util.Vector;

public class User {

    private int id;
    private Vector<User> followers;
    private Vector<User> following;

    public User(int id){
        this.id = id;
        followers = new Vector<>();
        following = new Vector<>();
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
        followers.add(user);
    }

    public void follow(User user)
    {
        following.add(user);
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
