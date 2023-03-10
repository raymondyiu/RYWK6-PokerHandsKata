package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Game game = null;

        while(true){
            System.out.println("Please enter: name1: cards name2: cards eg Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
            System.out.print(">");
            String line = sc.nextLine();
            if (line.contains("exit")) { break;};
            game = new Game(line);
            System.out.println(game.winnerCheck());

        }
        sc.close();
    }
}