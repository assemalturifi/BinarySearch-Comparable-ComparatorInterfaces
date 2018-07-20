package com.assem;

public class Seat implements Comparable<Seat>{
    private String seatNumber;
    private boolean reserved=false;
    private double price;

    public Seat(String seatNumber,double price) {
        this.seatNumber = seatNumber;
        this.price=price;
    }

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public int compareTo(Seat seat) {
        return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        // it will return a number greater than 0 if we should sort greater than the objects
        // that being compared to, and if the two objects are equal, the method needs to return 0
    }

    public boolean reserve(){
        if(!this.reserved){
            this.reserved=true;
            System.out.println("Seat "+seatNumber+" reserved.");
            return true;
        }
        else{
            //it was already reserved
            return false;
        }
    }
    public boolean cancel(){
        if(this.reserved){
            this.reserved=false;
            System.out.println("Reservation of seat "+seatNumber+ " cancelled");
            System.out.println("Now "+seatNumber+ " is available");
            return true;
        }
        else {
            //we werent able to cancel because the seat was already reserved
            return false;
        }
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }
}
