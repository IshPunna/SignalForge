public class PriceBar {

    private String tickerSymbol;
    private String date;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private long sharesTraded;

    public PriceBar(String tickerSymbol, String date, double openPrice, double highPrice, double lowPrice, double closePrice, long sharesTraded) {
        this.tickerSymbol = tickerSymbol;
        this.date = date;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.sharesTraded = sharesTraded;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getDate() {
        return date;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public long getSharesTraded() {
        return sharesTraded;
    }

    public double getDailyRange(){
        return highPrice - lowPrice;
    }

    public String getSummary(){
        return (this.tickerSymbol + " | " + this.date + "\nClose Price: $" + this.closePrice + "\nDaily Range: $" + this.getDailyRange());
    }
}