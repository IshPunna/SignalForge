import java.util.ArrayList;

public class SignalScanner {

    private final StockData stockData;
    private final int movingAveragePeriod;
    private final int rsiPeriod;
    private final int minimumDataPoints;
    private final int watchScoreThreshold;
    private final MarketSignalEvaluator evaluator;

    public SignalScanner(
            StockData stockData,
            int movingAveragePeriod,
            int rsiPeriod,
            int minimumDataPoints,
            int watchScoreThreshold
    ) {
        this.stockData = stockData;
        this.movingAveragePeriod = movingAveragePeriod;
        this.rsiPeriod = rsiPeriod;
        this.minimumDataPoints = minimumDataPoints;
        this.watchScoreThreshold = watchScoreThreshold;
        this.evaluator = new MarketSignalEvaluator();
    }

    public ScanReport scan(String[] watchlist) {
        ArrayList<SignalResult> results = new ArrayList<>();
        ArrayList<String> skippedTickers = new ArrayList<>();

        int requiredDataPoints = Math.max(
                minimumDataPoints,
                Math.max(movingAveragePeriod, rsiPeriod + 1)
        );

        for (String ticker : watchlist) {
            String normalizedTicker = ticker.trim().toUpperCase();

            ArrayList<PriceBar> selectedBars =
                    stockData.getBarsForTicker(normalizedTicker);

            if (selectedBars.size() < requiredDataPoints) {
                skippedTickers.add(
                        normalizedTicker
                                + ": needs at least "
                                + requiredDataPoints
                                + " data points."
                );
                continue;
            }

            double[] prices =
                    stockData.getClosePrices(normalizedTicker);

            long[] volumes =
                    stockData.getVolumes(normalizedTicker);

            double currentPrice =
                    prices[prices.length - 1];

            double simpleMovingAverage =
                    IndicatorCalculator.calculateSMA(
                            prices,
                            movingAveragePeriod
                    );

            double exponentialMovingAverage =
                    IndicatorCalculator.calculateEMA(
                            prices,
                            movingAveragePeriod
                    );

            double volatility =
                    IndicatorCalculator.calculateVolatility(prices);

            double rsi =
                    IndicatorCalculator.calculateRSI(
                            prices,
                            rsiPeriod
                    );

            long currentVolume =
                    volumes[volumes.length - 1];

            double averageVolume =
                    IndicatorCalculator.calculateAverageVolume(
                            volumes
                    );

            CalculatedMarketIndicators indicators =
                    new CalculatedMarketIndicators(
                            currentPrice,
                            simpleMovingAverage,
                            exponentialMovingAverage,
                            volatility,
                            rsi,
                            currentVolume,
                            averageVolume
                    );

            int score =
                    evaluator.calculateTotalScore(indicators);

            String reasons =
                    evaluator.buildAllReasons(indicators);

            String riskLevel =
                    SignalIndicator.getRiskLevel(volatility);

            String finalSignal =
                    SignalIndicator.getCombinedSignal(
                            currentPrice,
                            simpleMovingAverage,
                            currentVolume,
                            averageVolume
                    );

            SignalResult result =
                    new SignalResult(
                            normalizedTicker,
                            currentPrice,
                            simpleMovingAverage,
                            exponentialMovingAverage,
                            volatility,
                            rsi,
                            riskLevel,
                            currentVolume,
                            averageVolume,
                            score,
                            finalSignal,
                            reasons
                    );

            results.add(result);
        }

        return new ScanReport(
                results,
                skippedTickers,
                movingAveragePeriod,
                rsiPeriod,
                minimumDataPoints,
                watchScoreThreshold
        );
    }
}