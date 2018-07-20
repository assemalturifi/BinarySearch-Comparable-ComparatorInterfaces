package com.assem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// use a simple seatBooking system in theater to see how these classes fit into the Jva Collections framework.
//theater seats will be numbered with row letter and then a seat number within each row
//create your theater class and thats gonna contain an inner seat class
// create in the theatre class list of seat objects
//there will be a seat number, consist of a row letter and a seat number(theatre seats will be numbered with row
// letter and a seat number)
// charge $10 for seats but if the seats are near the middle of the first three rows
//the price inceases to 14. And seats in the back two rows or the edges of the theatre  only cost $7
//Use compareTo mathod implementing comparable interface to compare the seats
//use compare() to compare the price
public class Main {
    public static Scanner scanner =new Scanner(System.in);
public static  Theatre theatre =new Theatre("Assem Theatre",4,6);

    public static void main(String[] args) {


        List<Seat>newSeat=new ArrayList<>(theatre.getSeats());//shallow copy

//        newSeat.add(new Seat ("B00",13.00));
//        newSeat.add(new Seat ("B00",13.00));
//        Collections.sort(newSeat,Theatre.PRICE_ORDER);
//        printList(newSeat);

        System.out.println("All the seats in the Assem's Theatre:");

        Collections.sort(newSeat);
        printList(newSeat);


        printOptions();
        boolean quit=false;
        int choice=0;
        while(!quit){
            System.out.println("Enter your choice: ");
            choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    printOptions();
                    break;
                case 1:
                    reserveSeat(newSeat);
                    break;
                case 2:
                    cancelReservation(newSeat);
                    break;
                case 3:
                    printList((List<Seat>) theatre.getSeats());
                    break;
                case 4:
                    Collections.sort((List<Seat>) theatre.getSeats(),Theatre.PRICE_ORDER);
                    printList((List<Seat>) theatre.getSeats());
                    break;
                case 5:
                    quit=true;
                    break;
            }
        }
    }
    public static void printList(List<Seat>list ){
        for(Seat seat:list){
            System.out.print("("+seat.getSeatNumber()+" = $"+seat.getPrice()+")");

        }
        System.out.println();
        System.out.println("===========================");

    }
    public static void reserveSeat(List<Seat>list){
        System.out.println("Please enter a seat number to reserve: ");
        String seatNumber=scanner.nextLine().toUpperCase();
        Seat requestedSeat=new Seat(seatNumber);

        int foundSeat=Collections.binarySearch(list,requestedSeat);

        if(foundSeat>=0){
            theatre.reserveSeat(seatNumber);

        }
        else{
            System.out.println("there is no such seat");
        }

    }

//    public static void reserveSeat(Theatre theatre){
//        System.out.println("Please enter a seat number to reserve: ");
//        String seatNumber=scanner.nextLine().toUpperCase();
//
//        theatre.reserveSeat(seatNumber);
//    }

    public static void cancelReservation(List<Seat>list){
        System.out.println("Please enter the seat number that you reserved, so it would be cancelled:  ");
        String seatNumber=scanner.nextLine().toUpperCase();

        Seat requestedSeat=new Seat(seatNumber);

        int foundSeat=Collections.binarySearch(list,requestedSeat);

        if (foundSeat >= 0) {

            theatre.cancelReservation(seatNumber);
        }

        else{
            System.out.println("There is no such seat to be cancelled");
        }
    }

//    public static void cancelReservation(Theatre theatre){
//        System.out.println("Please enter the seat number that you reserved, so it would be cancelled:  ");
//        String seatNumber=scanner.nextLine().toUpperCase();
//        double seatPrice=0;
//
//        Seat seat=new Seat(seatNumber,seatPrice);
//
//        theatre.cancelReservation(seatNumber);
//        System.out.println("Now "+seatNumber+" is available!");
//    }
    public static void printOptions() {
        System.out.println("Available actions:\npress any of the actions below: ");
        System.out.println("0 - TO print menu options\n" +
                "1 - To reserve a seat\n" +
                "2 - to cancel existing reservation\n" +
                "3 - TO print available seats\n" +
                "4 - To get the avaliable seats in price order\n"+
                "5 - to quit");
    }
}
