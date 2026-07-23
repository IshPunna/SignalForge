
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        String[] watchlist = {"AAPL", "MSFT"};

        int movingAveragePeriod = 3;
        int rsiPeriod = 2;
        int minimumDataPoints = 3;
        int watchScoreThreshold = 60;

        ArrayList<PriceBar> bars = StockCsvReader.readFile("data/sample_stock_data.csv");

        StockData stockData = new StockData(bars);

        LatestPriceIndex latestPriceIndex = stockData.buildLatestPriceIndex();

        StockDataPrinter.printLatestPriceIndex(latestPriceIndex);

        System.out.println("===== SCAN SETTINGS =====");
        System.out.println("Moving Average Period: " + movingAveragePeriod);
        System.out.println("RSI Period: " + rsiPeriod);
        System.out.println("Minimum Data Points: " + minimumDataPoints);
        System.out.println("Watch Score Threshold: " + watchScoreThreshold);
        System.out.println("=========================");
        System.out.println();

        ArrayList<SignalResult> results = new ArrayList<>();

        MarketSignalEvaluator marketSignalEvaluator = new MarketSignalEvaluator();

        for (String ticker : watchlist) {
            ArrayList<PriceBar> selectedBars = stockData.getBarsForTicker(ticker);

            if (selectedBars.size() == 0) {
                System.out.println("No data found for ticker: " + ticker);
                continue;
            }

            if (selectedBars.size() < minimumDataPoints) {
                System.out.println("===== SKIPPED TICKER =====");
                System.out.println("Ticker: " + ticker);
                System.out.println("Reason: Needs at least " + minimumDataPoints + " data points.");
                System.out.println("==========================");
                System.out.println();
                continue;

            }

            StockDataPrinter.printBarsForTicker(ticker, selectedBars);

            double[] price = stockData.getClosePrices(ticker);
            long[] volume = stockData.getVolumes(ticker);

            double sma = IndicatorCalculator.calculateSMA(price, movingAveragePeriod);
            double ema = IndicatorCalculator.calculateEMA(price, movingAveragePeriod);
            double volatility = IndicatorCalculator.calculateVolatility(price);
            double rsi = IndicatorCalculator.calculateRSI(price, rsiPeriod);

            double currentPrice = price[price.length - 1];

            double averageVolume = IndicatorCalculator.calculateAverageVolume(volume);
            long currentVolume = volume[volume.length - 1];

            String riskLevel = SignalIndicator.getRiskLevel(volatility);

            String combinedSignal = SignalIndicator.getCombinedSignal(
                    currentPrice,
                    sma,
                    currentVolume,
                    averageVolume
            );

            CalculatedMarketIndicators calculatedIndicators
                    = new CalculatedMarketIndicators(
                            currentPrice,
                            sma,
                            ema,
                            volatility,
                            rsi,
                            currentVolume,
                            averageVolume
                    );

            int score
                    = marketSignalEvaluator.calculateTotalScore(
                            calculatedIndicators
                    );

            String reasons
                    = marketSignalEvaluator.buildAllReasons(
                            calculatedIndicators
                    );

            SignalResult result = new SignalResult(
                    ticker,
                    currentPrice,
                    sma,
                    ema,
                    volatility,
                    rsi,
                    riskLevel,
                    currentVolume,
                    averageVolume,
                    score,
                    combinedSignal,
                    reasons
            );

            results.add(result);

            String report = SignalReport.buildReport(result);

            System.out.println(report);
            System.out.println();
        }
        results.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("===== WATCHLIST RANKING =====");

        for (int i = 0; i < results.size(); i++) {
            SignalResult result = results.get(i);

            String watchStatus;

            if (result.getScore() >= watchScoreThreshold) {
                watchStatus = "FLAGGED";
            } else {
                watchStatus = "REVIEW";
            }

            System.out.println((i + 1) + ". "
                    + result.getTicker()
                    + " | "
                    + watchStatus
                    + " | "
                    + result.getFinalSignal()
                    + " | Score: "
                    + result.getScore()
                    + "/100");

        }
        System.out.println();
        System.out.println("PSA: Educational review only. Not real financial advice.\n");
    }

}
