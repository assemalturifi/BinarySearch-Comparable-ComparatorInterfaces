package com.assem;

import java.util.*;

public class Theatre {
    private final String theatreName;
    private List<Seat>seats=new ArrayList<>();
    private List<Seat>removedSeats=new ArrayList<>();

    static final Comparator<Seat>PRICE_ORDER=new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {//they are equal;
                return 0;
            }
        }
    };

    public Theatre(String theatreName, int numRows,int seatsPerRow) {
        this.theatreName = theatreName;
        //creating the last row, all chars have a uniqe number, A is 65, adding (3-1) to it will give you the last row 'C'
        int lastRow='A'+(numRows-1);
        //then i am cycling through and create a seat for each one of these theatre seats.

        //so here, im going through all the rows from A to Z effectively
        for(char row='A';row<=lastRow;row++){
            //now and for each row  going through and allocating the seats for the section

            for(int seatNum=1;seatNum<=seatsPerRow;seatNum++){
                double price=10.00;
                if((row<'D')&&(seatNum>=2&&seatNum<=5)){
                    price=14.00;
                }
                else if((row>'F')||(seatNum<2||seatNum>5)){
                    price=7.00;
                }
                Seat seat=new Seat(row+String.format("%02d",seatNum),price);
                seats.add(seat);
            }
        }
    }
    public boolean reserveSeat(String seatNumber){
        Seat requestedSeat=new Seat(seatNumber,0);
        int foundSeat=Collections.binarySearch(seats,requestedSeat,null);
        requestedSeat=seats.get(foundSeat);
        if(foundSeat>=0){//if it is in the list, will give the index num of the seat
            seats.get(foundSeat).reserve();
            seats.remove(foundSeat);
            removedSeats.add(requestedSeat);
            return true;
        }
        else{
            System.out.println("There is no seat "+seatNumber+" available!");
            return false;
        }
    }
    public boolean cancelReservation(String seatNumber){

        Seat requestedSeat=new Seat(seatNumber,0);
        int foundSeat=Collections.binarySearch(removedSeats,requestedSeat,null);
        requestedSeat=removedSeats.get(foundSeat);
        if(foundSeat>=0){
            removedSeats.get(foundSeat).cancel();
            removedSeats.remove(foundSeat);
            seats.add(requestedSeat);
            return true;
        }
        else{
            return false;
        }
    }
    public String getTheatreName() {
        return theatreName;
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

}
