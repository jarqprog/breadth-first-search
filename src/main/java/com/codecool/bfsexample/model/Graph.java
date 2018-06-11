package com.codecool.bfsexample.model;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private Map<UserNode, Set<UserNode>> adjacency = new HashMap<>();

    public boolean addUser(UserNode user) {
        adjacency.put(user, user.getFriends());
        return true;
    }

    public UserNode findUserById(int userId) {


        return null;

    }

    public int checkDistance(UserNode first, UserNode second) {

        // WIP
        if (first.getId() == second.getId()) {
            return 0;
        }

        Set<UserNode> toCheck = new HashSet<>(first.getFriends());

        if (toCheck.contains(second))  {
            return 1;
        }

        Queue<UserNode> queue = new ArrayDeque<>(toCheck);
        Set<UserNode> examined = new HashSet<>(toCheck);
        Set<UserNode> checked = new HashSet<>();

        UserNode currentUser;

        int counter = 1;

        while (! toCheck.isEmpty() ) {

            toCheck.clear();

            for (UserNode user : queue) {
                toCheck.addAll(user.getFriends());
            }

            counter++;

            if (toCheck.contains(second)) {
                return counter;
            }

            while (! queue.isEmpty() ) {
                currentUser = queue.poll();
                toCheck.addAll(currentUser.getFriends());
            }

            queue.addAll(toCheck);
        }

        return -1;
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
                toAdd.addAll(currentUser.getFriends());
            }
            friends.addAll(toAdd);
        }

        friends.remove(user);
        return friends;
    }
}
