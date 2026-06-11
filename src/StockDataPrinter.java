import java.util.ArrayList;
import java.util.HashMap;

public class StockDataPrinter {

    public static void printBars(ArrayList<PriceBar> bars) {
        System.out.println("===== LOADED CSV DATA =====");

        for (PriceBar bar : bars) {
            System.out.println(bar.getSummary());
        }
    }

    public static void printBarsForTicker(String ticker, ArrayList<PriceBar> bars) {
        System.out.println("===== DATA FOR " + ticker + " =====");

        for (PriceBar bar : bars) {
            System.out.println(bar.getSummary());
        }
    }

    public static void printLatestClosePriceMap(HashMap<String, Double> latestPrices) {
    System.out.println("===== LATEST CLOSE PRICE MAP =====");

    for (String symbol : latestPrices.keySet()) {
        System.out.println(symbol + " -> $" + latestPrices.get(symbol));
    }
}
}