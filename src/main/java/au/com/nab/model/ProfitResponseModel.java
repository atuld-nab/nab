package au.com.nab.model;

public class ProfitResponseModel {
    private String currency;
    private Double profit;
    private PriceTimeStamp buyingPrice;
    private PriceTimeStamp sellPrice;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public PriceTimeStamp getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(PriceTimeStamp buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public PriceTimeStamp getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(PriceTimeStamp sellPrice) {
        this.sellPrice = sellPrice;
    }
}
