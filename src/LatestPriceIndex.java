import java.util.HashMap;

public class LatestPriceIndex {

    private HashMap<String, Double> latestPrices;

    public LatestPriceIndex() {
        latestPrices = new HashMap<>();
    }

    public void addPrice(String ticker, double closePrice) {
        latestPrices.put(ticker, closePrice);
    }

    public double getLatestPrice(String ticker) {
        return latestPrices.get(ticker);
    }

    public boolean hasTicker(String ticker) {
        return latestPrices.containsKey(ticker);
    }

    public HashMap<String, Double> getAllLatestPrices() {
        return latestPrices;
    }
}