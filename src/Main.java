
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        String ticker = "AAPL";

        ArrayList<PriceBar> bars = StockCsvReader.readFile("data/sample_stock_data.csv");

        StockData stockData = new StockData(bars);

        LatestPriceIndex latestPriceIndex = stockData.buildLatestPriceIndex();

        StockDataPrinter.printLatestPriceIndex(latestPriceIndex);

        ArrayList<PriceBar> selectedBars = stockData.getBarsForTicker(ticker);

        if (selectedBars.size() == 0) {
            System.out.println("No data found for ticker: " + ticker);
            return;
        }

        StockDataPrinter.printBarsForTicker(ticker, selectedBars);

        double[] price = stockData.getClosePrices(ticker);
        long[] volume = stockData.getVolumes(ticker);

        double sma = IndicatorCalculator.calculateSMA(price, 3);
        double ema = IndicatorCalculator.calculateEMA(price, 3);
        double volatility = IndicatorCalculator.calculateVolatility(price);
        String riskLevel = SignalIndicator.getRiskLevel(volatility);

        double currentPrice = price[price.length - 1];

        double averageVolume = StockMath.calculateAvgVolume(volume);
        long currentVolume = volume[volume.length - 1];

        String combinedSignal = SignalIndicator.getCombinedSignal(
                currentPrice,
                sma,
                currentVolume,
                averageVolume
        );

        String reasons = SignalIndicator.getSignalReasons(
        currentPrice,
        sma,
        ema,
        currentVolume,
        averageVolume,
        riskLevel
);

        String report = SignalReport.buildReport(
                ticker,
                currentPrice,
                sma,
                ema,
                volatility,
                riskLevel,
                currentVolume,
                averageVolume,
                combinedSignal,
                reasons
        );

        System.out.println(report);
    }

    
}
