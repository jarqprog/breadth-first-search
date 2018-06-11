package com.codecool.bfsexample.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class GraphTest {


    private UserNode jerry = new UserNode(0, "Jerry", "Louis");
    private UserNode filip = new UserNode(1, "Filip", "Arnold");
    private UserNode hanna = new UserNode(2, "Hanna", "Arnold");
    private UserNode mark = new UserNode(3, "Mark", "Arnold");
    private UserNode sophia = new UserNode(4, "Sophia", "Twain");
    private UserNode ann = new UserNode(5, "Ann", "Twain");
    private UserNode ben = new UserNode(6, "Ben", "Smith");
    private UserNode luke = new UserNode(7, "Luke", "Smith");
    private UserNode han = new UserNode(8, "Han", "Solo");
    private UserNode leia = new UserNode(9, "Leia", "Ol");
    private UserNode yoda = new UserNode(10, "Yoda", "Yoda");

    private Graph graph;

    @Before
    public void before() {
        graph = new Graph();
        fillGraphWithFriends();
    }

    @Test
    public void checkDistance_same_user() {

        int expected = 0;

        int result = graph.checkDistance(jerry, jerry);

        assertEquals(expected, result);
    }


    @Test
    public void checkDistance_if_not_contains() {

        int expected = -1;

        int result = graph.checkDistance(jerry, new UserNode(20, "Dan", "Martin"));

        assertEquals(expected, result);
    }

    @Test
    public void checkDistance_close_friends() {

        int expected = 1;

        int result = graph.checkDistance(jerry, hanna);

        assertEquals(expected, result);
    }


    @Test
    public void checkDistance_if_distance_2() {

        int expected = 2;

        int result = graph.checkDistance(jerry, mark);

        assertEquals(expected, result);
    }

    @Test
    public void checkDistance_if_distance_3() {

        int expected = 3;

        int result = graph.checkDistance(jerry, filip);

        assertEquals(expected, result);
    }

    @Test
    public void checkDistance_if_distance_4() {

        int expected = 4;

        int result = graph.checkDistance(jerry, han);

        assertEquals(expected, result);
    }

    @Test
    public void getClosestFriends() {

        Set<UserNode> expected = new HashSet<>(jerry.getFriends());

        Set<UserNode> friendsOfJerry = graph.getFriends(jerry, 1);

        assertTrue(friendsOfJerry.containsAll(expected));
        assertEquals(expected.size(), friendsOfJerry.size());
    }

    @Test
    public void getFriendsWithDistanceTwo() {

        Set<UserNode> expected = new HashSet<>(jerry.getFriends());
        expected.addAll(jerry.getFriends());
        expected.addAll(hanna.getFriends());
        expected.addAll(yoda.getFriends());
        expected.addAll(luke.getFriends());
        expected.addAll(mark.getFriends());
        expected.remove(jerry);

        Set<UserNode> friends = graph.getFriends(jerry, 2);

        assertTrue(friends.containsAll(expected));
        assertEquals(expected.size(), friends.size());
    }


    @Test
    public void getFriendsWithDistanceThree() {

        Set<UserNode> expected = new HashSet<>(jerry.getFriends());
        expected.addAll(hanna.getFriends());
        expected.addAll(yoda.getFriends());
        expected.addAll(luke.getFriends());
        expected.addAll(filip.getFriends());
        expected.addAll(mark.getFriends());
        expected.remove(jerry);

        Set<UserNode> friends = graph.getFriends(jerry, 3);

        assertTrue(friends.containsAll(expected));
        assertEquals(expected.size(), friends.size());
    }

    @Test
    public void getFriendsWithDistanceFour() {

        Set<UserNode> all = new HashSet<>(createFriendsCollection());
        all.remove(jerry);

        Set<UserNode> friends = graph.getFriends(jerry, 4);

        assertTrue(friends.containsAll(all));
        assertEquals(all.size(), friends.size());
    }

    private List<UserNode> createFriendsCollection() {

        return Arrays.asList(luke, leia, han, yoda, jerry, filip, ann, hanna, ben, mark, sophia);
    }


    private void fillGraphWithFriends() {

        jerry.addFriend(hanna);
        jerry.addFriend(yoda);
        jerry.addFriend(luke);

        yoda.addFriend(jerry);

        hanna.addFriend(jerry);

        filip.addFriend(ann);
        filip.addFriend(sophia);
        filip.addFriend(ben);
        filip.addFriend(han);
        han.addFriend(filip);
        filip.addFriend(leia);
        leia.addFriend(filip);

        ben.addFriend(filip);
        sophia.addFriend(filip);
        ann.addFriend(filip);

        luke.addFriend(jerry);

        mark.addFriend(hanna);
        hanna.addFriend(mark);
        filip.addFriend(mark);
        mark.addFriend(filip);

        for(UserNode userNode : createFriendsCollection()) {
            graph.addUser(userNode);
        }
    }

}