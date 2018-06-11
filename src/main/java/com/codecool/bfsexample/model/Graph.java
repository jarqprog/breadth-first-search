package com.codecool.bfsexample.model;

import java.util.*;

public class Graph {

    private Map<UserNode, Set<UserNode>> adjacency = new HashMap<>();

    public Graph() {}

    public Graph(Collection<UserNode> users) {
        users.forEach(this::addUser);
    }

    public boolean addUser(UserNode user) {
        adjacency.put(user, user.getFriends());
        return true;
    }

    public List<UserNode> shortestPath(UserNode first, UserNode second) {
        List<UserNode> path = new LinkedList<>();
        if (! adjacency.containsKey(first) || ! adjacency.containsKey(second) ) {
            return path;
        }

        if (first == second) {
            path.add(first);
            return path;
        }

        Map<UserNode,UserNode> predecessors = new HashMap<>();
        Set<UserNode> examined = new HashSet<>();
        Queue<UserNode> queue = new ArrayDeque<>(first.getFriends());

        UserNode friend = null;

        while (! queue.isEmpty() ) {

            friend = queue.poll();
            examined.add(friend);

            if (friend == null) {
                return path;
            }

            if (friend == second) {
                break;
            }

            for (UserNode user : friend.getFriends()) {
                if (! examined.contains(user) ) {
                    queue.add(user);
                    predecessors.put(user, friend);
                    examined.add(user);
                }
            }
        }

        if (friend != null && friend == second) {
            examined.clear();

            while (friend != first) {
                if (friend == null) {
                    break;
                }

                if (! examined.contains(friend) ) {
                    path.add(0, friend);
                    examined.add(friend);
                }

                UserNode temp = friend;
                friend = predecessors.get(friend);
                predecessors.remove(temp);
            }
        }
        path.add(0, first);
        return path;
    }

    public int minimumDistance(UserNode first, UserNode second) {

        int size = shortestPath(first, second).size();

        if (size == 0) {
            return -1;
        }

        return size - 1;
    }

    public Set<UserNode> getFriends(UserNode user, int distance) {

        if (distance == 0) {
            return new HashSet<>();
        }

        Set<UserNode> friends = new HashSet<>(user.getFriends());  // output
        Queue<UserNode> queue = new ArrayDeque<>();
        Set<UserNode> toAdd = new HashSet<>(user.getFriends());

        if(distance == 1)  {
            return friends;
        }

        UserNode currentUser;

        for (int i=0; i<distance; i++) {

            queue.addAll(toAdd);
            toAdd.clear();

            while (! queue.isEmpty() ) {
                currentUser = queue.poll();
                if (currentUser != null) {
                    toAdd.addAll(currentUser.getFriends());
                }
            }
            friends.addAll(toAdd);
        }

        friends.remove(user);
        return friends;
    }
}
