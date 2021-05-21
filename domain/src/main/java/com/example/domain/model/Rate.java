package com.example.domain.model;

public class Rate {

    private int priceHour;
    private int priceDay;

    public Rate(int priceHour, int priceDay) {
        this.priceHour = priceHour;
        this.priceDay = priceDay;
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
}
