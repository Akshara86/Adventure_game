import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String currentRoom="Entrance";
    static List<String> Inventory=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    static  String monster="Alive";

    public static void main(String[] args) {
        System.out.println("Welcome to the Adventure Game");
        System.out.println("Your goal is to go to treasure Room and collect Open Treasure box");
        while(true){
            displayRooms();

            System.out.print("what do you want to do choose the command :");
            String toMove=sc.nextLine();

            //Implementations of the commands

            switch(toMove){
                case "go north":
                case "go south":
                case "go east":
                case "go west":
                    movePlayer(toMove);
                    break;
                case "pick up coins":
                    if(!Inventory.contains("coins")){
                        Inventory.add("coins");
                        System.out.println("coins are picked");
                    }else if(Inventory.contains("coins")){
                        System.out.println("Already coins are taken away");
                    }
                    break;
                case "kill":
                    if(monster.equals("Alive")) {
                        if (Inventory.contains("coins")) {
                            System.out.println("Hurry you Successfully killed the Monster and won the key");
                            Inventory.remove("coins");
                            Inventory.add("key");
                            monster = "dead";
                        } else if (!Inventory.contains("coins")) {
                            System.out.println("To kill the Monster you need coins");
                        }
                    }else {
                        System.out.println("Monster is already killed");
                    }
                    break;

                case "CheckInventory":
                    if(Inventory.isEmpty()){
                        System.out.println("Your Inventory is Empty");
                    }else {
                        System.out.println("Your Inventory Contains "+Inventory);
                    }
                    break;
                case "talk":
                    toTalk();
                    break;
                case "unlock":
                    if(Inventory.isEmpty()){
                        System.out.println("first kill the monster to get the key");
                    }else{
                        System.out.println("Sucessfully unlocked the treasure box");
                        System.out.println("You won the game");
                        System.exit(0);
                    }

                case "quit":
                    System.out.println("Thanks for Playing");
                    System.exit(0);
                default:
                    System.out.println("Invalid Command");
            }

        }

    }

    //to get hints to player to succeed in the game

    public static void toTalk(){
        switch (currentRoom){
            case "Entrance":
                System.out.println("You are at Entrance Now, move towards North is a good move i suggest");
                break;

            case "Forest":
                if(Inventory.contains("coins"))
                    System.out.println("You are in Forest Now, and picked coins so i suggest to go east");
                else
                    System.out.println("You are in Forest Now, Pick up the coins so that you become more stronger");
                break;

            case "Dungeon":
                if(Inventory.contains("coins"))
                    System.out.println("Kill the monster to get the key");
                else if(monster.equals("Alive") && !Inventory.contains("coins")){
                    System.out.println("Go west and collect coins to kill the monster which have key to open treasurer");
                }else{
                    System.out.println("go north to find the treasure box");
                }
                break;

            case "Treasure":
                if(Inventory.contains("key")){
                    System.out.println("Unlock the treasure box to win the game");
                }else {
                    System.out.println("You don't have the Key to open the treasure box better go south to find it");
                }
        }
    }

    //To get commands when player is in specific Room

    public static void displayRooms(){
        switch (currentRoom){
            case "Entrance":
                System.out.println("You are at entrance Now");
                System.out.println("1.go north (to go to Forest)");
                System.out.println("2.talk (to get hints)");
                System.out.println("3.CheckInventory");
                System.out.println("4.quit");
                break;

            case "Forest":
                System.out.println("You are in Forest now");
                System.out.println("1.go east (to go to Dungeon)");
                System.out.println("2.go south (to go to Entrance)");
                System.out.println("3.pick up coins");
                System.out.println("4.talk (to get hints)");
                System.out.println("5.CheckInventory");
                System.out.println("6.quit");
                break;

            case "Dungeon":
                System.out.println("You are in Dungeon now");//\"go west\" to move to Forest or \"go north\" to move to Treasure Room");
                System.out.println("1.go south (to go to treasure room)");
                System.out.println("2.go west (to go to Forest)");
                System.out.println("3.kill");
                System.out.println("4.talk (to get hints)");
                System.out.println("5.CheckInventory");
                System.out.println("6.quit");
                break;

            case "Treasure":
                if (Inventory.contains("Key")) {
                    System.out.println("1.unlock");
                    System.out.println("2.go north (to go to Dungeon)");
                    System.out.println("3.talk (to get hints)");
                    System.out.println("4.CheckInventory");
                    System.out.println("5.quit");

                } else {
                    System.out.println("The Treasure Room is locked. You need a Key to enter");
                    System.out.println("1.go north (to go to Dungeon)");
                    System.out.println("2.talk (to get hints)");
                    System.out.println("3.CheckInventory");
                    System.out.println("4.quit");                }
                break;
            default:
                System.out.println("Unknown location.");
        }
    }

    // to move the player from one place to other

    public static void movePlayer(String toMove){
        switch (currentRoom){
            case "Entrance":
                if(toMove.equals("go north"))
                    currentRoom="Forest";
                break;

            case "Forest":
                if(toMove.equals("go south"))
                    currentRoom="Entrance";
                else if(toMove.equals("go east"))
                    currentRoom="Dungeon";
                break;

            case "Dungeon":
                if(toMove.equals("go west"))
                    currentRoom="Forest";
                else if(toMove.equals("go south"))
                    currentRoom="Treasure";
                break;

            case "Treasure":
                if(toMove.equals("go north"))
                    currentRoom="Dungeon";
                break;
        }
    }
}