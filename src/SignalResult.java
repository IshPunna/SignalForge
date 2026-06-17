
public class SignalResult {

    private String ticker;
    private double currentPrice;
    private double simpleMovingAverage;
    private double exponentialMovingAverage;
    private double volatility;
    private double rsi;
    private String riskLevel;
    private long currentVolume;
    private double averageVolume;
    private int score;
    private String finalSignal;
    private String reasons;

    public SignalResult(
            String ticker,
            double currentPrice,
            double simpleMovingAverage,
            double exponentialMovingAverage,
            double volatility,
            double rsi,
            String riskLevel,
            long currentVolume,
            double averageVolume,
            int score,
            String finalSignal,
            String reasons
    ) {
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.simpleMovingAverage = simpleMovingAverage;
        this.exponentialMovingAverage = exponentialMovingAverage;
        this.volatility = volatility;
        this.rsi = rsi;
        this.riskLevel = riskLevel;
        this.currentVolume = currentVolume;
        this.averageVolume = averageVolume;
        this.score = score;
        this.finalSignal = finalSignal;
        this.reasons = reasons;
    }

    public int getScore() {
        return score;
    }

    public String getTicker() {
        return ticker;
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

    public String getRiskLevel() {
        return riskLevel;
    }

    public long getCurrentVolume() {
        return currentVolume;
    }

    public double getAverageVolume() {
        return averageVolume;
    }

    public String getFinalSignal() {
        return finalSignal;
    }

    public String getReasons() {
        return reasons;
    }
}
