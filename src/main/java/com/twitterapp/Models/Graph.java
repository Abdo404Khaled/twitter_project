package com.twitterapp.Models;

import java.util.*;
import javafx.util.*;

import com.twitterapp.Interfaces.Igraph;

public class Graph implements Igraph{

    private Map<Integer,  Pair<User, List<Edge> >> map = new HashMap<>();
	private Heap heap = new Heap();

	@Override
	public void addUser(User user)
	{
		if(map.containsKey(user.get_id()))
            return;

        map.put(user.get_id(), new Pair<User, List<Edge> >(user, new LinkedList<Edge>()));
	}
	@Override
	public void follow(User user1, User user2)
	{
		if (!map.containsKey(user1.get_id()))
            addUser(user1);

		if (!map.containsKey(user2.get_id()))
            addUser(user2);

		map.get(user1.get_id()).getValue().add(new Edge(user1.get_id(), user2.get_id()));

        map.get(user1.get_id()).getKey().follow(user2);
		map.get(user2.get_id()).getKey().add_follower(user1);
	}

	@Override
	public int getUserCount()
	{
        return map.keySet().size();
	}

	@Override
	public Boolean hasUser(User user)
	{
		return map.containsKey(user.get_id());
	}
	
	@Override
	public Boolean isFollowing(Edge edge)
	{
		return map.get(edge.getFollower()).getValue().contains(edge);
	}

	public void processDB(){
		for(Pair<User, List<Edge>> user: map.values())
			heap.insert(user.getKey());
	}

	public User topInfluencer(){
		return heap.popMax();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		int records = 0;

		for (Pair<User, List<Edge>> usersPair : map.values()) {
			builder.append(usersPair.getKey().toString());
			records += usersPair.getKey().get_followers();
			builder.append("\n");
		}
        builder.append("Users Count: " + map.size());
		builder.append("\nRecords Count: " + records);
		builder.append("\nTop 10 Influencers: \n");
		builder.append("1- " + topInfluencer().toString()+ '\n');
		builder.append("2- " + topInfluencer().toString()+ '\n');
		builder.append("3- " + topInfluencer().toString()+ '\n');
		builder.append("4- " + topInfluencer().toString()+ '\n');
		builder.append("5- " + topInfluencer().toString()+ '\n');
		builder.append("6- " + topInfluencer().toString()+ '\n');
		builder.append("7- " + topInfluencer().toString()+ '\n');
		builder.append("8- " + topInfluencer().toString()+ '\n');
		builder.append("9- " + topInfluencer().toString()+ '\n');
		builder.append("10- " + topInfluencer().toString()+ '\n');
		return (builder.toString());
	}
}
