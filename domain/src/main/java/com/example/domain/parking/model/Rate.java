package com.example.domain.parking.model;

public class Rate {

    private int priceHour;
    private int priceDay;
    private int surplus;

    public Rate(int priceHour, int priceDay, int surplus) {
        setPriceHour(priceHour);
        setPriceDay(priceDay);
        setSurplus(surplus);
    }

    public int getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(int priceHour) {
        this.priceHour = priceHour;
    }

    public int getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(int priceDay) {
        this.priceDay = priceDay;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }
}
