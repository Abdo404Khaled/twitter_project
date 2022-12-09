package com.twitterapp.Models;

public class Edge {
    private int followerID;
    private int followedID;

    public Edge(int followerID, int followedID){
        this.followerID = followerID;
        this.followedID = followedID;
    }

    public int getFollower(){
        return followerID;
    }

    public int getFollowed(){
        return followedID;
    }

    @Override
    public String toString(){
        return "( " + followerID + " Follows -> " + followedID + ")";
    }

    @Override
    public boolean equals(Object o) {
  
        if (o == this) {
            return true;
        }

        if (!(o instanceof Edge))
            return false;

        
        Edge c = (Edge) o;
         
        return followerID == c.followerID && followedID == c.followedID;
    }

}
