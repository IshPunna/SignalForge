
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        String ticker = "AAPL";

        ArrayList<PriceBar> bars = StockCsvReader.readFile("data/sample_stock_data.csv");

        StockData stockData = new StockData(bars);

        HashMap<String, Double> latestPrices = stockData.getLatestClosePriceMap();

        StockDataPrinter.printLatestClosePriceMap(latestPrices);

        ArrayList<PriceBar> selectedBars = stockData.getBarsForTicker(ticker);

        StockDataPrinter.printBarsForTicker(ticker, selectedBars);

        if (selectedBars.size() == 0) {
            System.out.println("No data found for ticker: " + ticker);
            return;
        }

        double[] price = new double[selectedBars.size()];
        long[] volume = new long[selectedBars.size()];

        for (int i = 0; i < selectedBars.size(); i++) {
            price[i] = selectedBars.get(i).getClosePrice();
            volume[i] = selectedBars.get(i).getSharesTraded();
        }

        double sma = StockMath.calculateSimpleMovingAverage(price, 3);
        double currentPrice = price[price.length - 1];

        double averageVolume = StockMath.calculateAvgVolume(volume);
        long currentVolume = volume[volume.length - 1];

        String combinedSignal = SignalIndicator.getCombinedSignal(
                currentPrice,
                sma,
                currentVolume,
                averageVolume
        );

        String report = SignalReport.buildReport(
                ticker,
                currentPrice,
                sma,
                currentVolume,
                averageVolume,
                combinedSignal
        );

        System.out.println(report);
    }
}
