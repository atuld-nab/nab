package au.com.nab.model;

import java.time.LocalTime;

public class PriceTimeStamp {
    private LocalTime time;
    private Double price;

    public PriceTimeStamp(LocalTime time, Double price) {
        this.time = time;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
