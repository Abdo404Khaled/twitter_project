package com.twitterapp.Interfaces;

import com.twitterapp.Models.Edge;
import com.twitterapp.Models.User;

public interface Igraph {
    public void addUser(User user);
    public void follow(User source, User destination);
    public int getUserCount();
    public Boolean hasUser(User user);
    public Boolean isFollowing(Edge edge);
}
