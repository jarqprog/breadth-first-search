package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.List;

public class BFSExample {

    public static List<UserNode> getUsers() {

        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.generate();

    }

    public static void main(String[] args) {

        List<UserNode> nodes = getUsers();

        nodes.forEach(System.out::println);

        nodes.stream().filter(u -> u.getId() % 2 ==0)
                .forEach(System.out::println);

        GraphPlotter.plot(nodes);

    }
}
