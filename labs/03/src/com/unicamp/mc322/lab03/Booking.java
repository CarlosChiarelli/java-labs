package com.unicamp.mc322.lab03;

import java.util.ArrayList;

public class Booking {
    private ArrayList<User> userList;
    private ArrayList<Hotel> hotelList;
    private ArrayList<Integer> numberRoomList;
    private ArrayList<Integer> daysList;

    public Booking() {
        userList = new ArrayList<User>();
        hotelList = new ArrayList<Hotel>();
        numberRoomList = new ArrayList<Integer>();
        daysList = new ArrayList<Integer>();
    }

    public boolean createHotelReserv(User user, Hotel hotel, int nbRoom, int days) {
        boolean success = false;
        boolean checkAvailableRoom = this.checkAvailableRoom(hotel, nbRoom);
        boolean checkBalanceUser = this.checkBalanceUser(hotel, nbRoom, user);
        boolean checkSmoker = this.checkSmoker(hotel, nbRoom, user);
        if (checkAvailableRoom && checkBalanceUser && checkSmoker) {
            hotel.setOccupiedByNumberRoom(nbRoom, true);
            user.changeBalance(-1 * hotel.getPriceRoom(nbRoom));
            userList.add(user);
            hotelList.add(hotel);
            numberRoomList.add(nbRoom);
            daysList.add(days);
            success = true;
        }
        return success;
    }

    private boolean checkAvailableRoom(Hotel hotel, int nbRoom) {
        return !hotel.getAvailableRoom(nbRoom);
    }

    private boolean checkBalanceUser(Hotel hotel, int nbRoom, User user) {
        if (hotel.getPriceRoom(nbRoom) > user.getBalance())
            return false;
        return true;
    }

    private boolean checkSmoker(Hotel hotel, int nbRoom, User user) {
        if (user.getSmoker() && !hotel.getSmokerRoom(nbRoom))
            return false;
        return true;
    }

    public boolean cancelHotelReserv(User user, Hotel hotel, int nbRoom) {
        boolean success = false;
        int idxReserv = this.checkReservationExist(user, hotel, nbRoom);
        if (idxReserv != -1) {
            float price = hotelList.get(idxReserv).getPriceRoom(nbRoom);
            userList.get(idxReserv).changeBalance(0.7 * price);
            hotelList.get(idxReserv).setOccupiedByNumberRoom(nbRoom, false);
            userList.remove(idxReserv);
            hotelList.remove(idxReserv);
            numberRoomList.remove(idxReserv);
            daysList.remove(idxReserv);
            success = true;
        }
        return success;
    }

    private int checkReservationExist(User user, Hotel hotel, int nbRoom) {
        int success = -1;
        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i) == user && hotelList.get(i) == hotel && numberRoomList.get(i) == nbRoom)
                success = i;
        return success;
    }
}
