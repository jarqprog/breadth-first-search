package com.codecool.bfsexample;

import com.codecool.bfsexample.model.Graph;
import com.codecool.bfsexample.model.UserNode;

import java.util.List;

public class BFSExample {

    public static List<UserNode> getUsers() {

        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.generate();

    }

    public static void main(String[] args) {
        
        List<UserNode> nodes = getUsers();

        Graph graph = new Graph(nodes);
        GraphPlotter.plot(nodes);

        int len = nodes.size();

        for (int i=1; i<len; i++) {
            if (i > len / 2 - 2) {
                break;
            }

            UserNode first = nodes.get(i);
            UserNode second = nodes.get(len-i);
            System.out.println("Shortest path between: " + first + " & " + second + ":");
            System.out.println(graph.shortestPath(first, second));
            i++;
        }
    }
}
