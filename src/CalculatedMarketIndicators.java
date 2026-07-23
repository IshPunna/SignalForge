/**
 * holds the calculated market indicators for one stock
 */
public class CalculatedMarketIndicators {

    private final double currentPrice;
    private final double simpleMovingAverage;
    private final double exponentialMovingAverage;
    private final double volatility;
    private final double rsi;
    private final long currentVolume;
    private final double averageVolume;

    public CalculatedMarketIndicators(
            double currentPrice,
            double simpleMovingAverage,
            double exponentialMovingAverage,
            double volatility,
            double rsi,
            long currentVolume,
            double averageVolume
    ) {
        this.currentPrice = currentPrice;
        this.simpleMovingAverage = simpleMovingAverage;
        this.exponentialMovingAverage = exponentialMovingAverage;
        this.volatility = volatility;
        this.rsi = rsi;
        this.currentVolume = currentVolume;
        this.averageVolume = averageVolume;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getSimpleMovingAverage() {
        return simpleMovingAverage;
    }

    public double getExponentialMovingAverage() {
        return exponentialMovingAverage;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getRsi() {
        return rsi;
    }

    public long getCurrentVolume() {
        return currentVolume;
    }

    public double getAverageVolume() {
        return averageVolume;
    }
}