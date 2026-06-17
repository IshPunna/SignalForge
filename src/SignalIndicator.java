
public class SignalIndicator {

    public static String getMovingAverageSignal(double currentPrice, double movingAverage) {
        if (currentPrice > movingAverage) {
            return "Bullish: current price is above moving average";
        } else if (currentPrice < movingAverage) {
            return "Bearish: current price is below moving average";
        } else {
            return "Neutral: current price equals moving average";
        }
    }

    public static String getVolumeSignal(long currentVolume, double averageVolume) {
        if (currentVolume > averageVolume) {
            return "Volume Confirmed: current volume is above average";
        } else {
            return "Low Volume: current volume is not above average";
        }
    }

    public static String getCombinedSignal(
            double currentPrice,
            double movingAverage,
            long currentVolume,
            double averageVolume
    ) {
        if (currentPrice > movingAverage && currentVolume > averageVolume) {
            return "Strong Bullish Signal";
        } else if (currentPrice > movingAverage && currentVolume <= averageVolume) {
            return "Weak Bullish Signal";
        } else if (currentPrice < movingAverage && currentVolume > averageVolume) {
            return "Strong Bearish Signal";
        } else if (currentPrice < movingAverage && currentVolume <= averageVolume) {
            return "Weak Bearish Signal";
        } else {
            return "Neutral Signal";
        }
    }

    public static String getRiskLevel(double volatility) {
        if (volatility < 2) {
            return "Low";
        } else if (volatility < 5) {
            return "Moderate";
        } else {
            return "High";
        }
    }

    public static String getSignalReasons(
            double currentPrice,
            double simpleMovingAverage,
            double exponentialMovingAverage,
            double rsi,
            long currentVolume,
            double averageVolume,
            String riskLevel
    ) {
        StringBuilder reasons = new StringBuilder();

        if (currentPrice > simpleMovingAverage) {
            reasons.append("- Price is above SMA, suggesting an upward trend.\n");
        } else if (currentPrice < simpleMovingAverage) {
            reasons.append("- Price is below SMA, suggesting a weaker trend.\n");
        } else {
            reasons.append("- Price is equal to SMA, suggesting a neutral trend.\n");
        }

        if (exponentialMovingAverage > simpleMovingAverage) {
            reasons.append("- EMA is above SMA, suggesting recent momentum is improving.\n");
        } else if (exponentialMovingAverage < simpleMovingAverage) {
            reasons.append("- EMA is below SMA, suggesting recent momentum is weakening.\n");
        } else {
            reasons.append("- EMA and SMA are equal, suggesting neutral momentum.\n");
        }

        if (rsi < 30) {
            reasons.append("- RSI is below 30, suggesting the stock may be oversold.\n");
        } else if (rsi > 70) {
            reasons.append("- RSI is above 70, suggesting the stock may be overbought.\n");
        } else {
            reasons.append("- RSI is in a neutral range.\n");
        }

        if (currentVolume > averageVolume) {
            reasons.append("- Volume is above average, which gives the signal more support.\n");
        } else {
            reasons.append("- Volume is below average, so the signal is weaker.\n");
        }

        reasons.append("- Risk level is ").append(riskLevel).append(".\n");

        return reasons.toString();
    }

    public static int getSignalScore(
            double currentPrice,
            double simpleMovingAverage,
            double exponentialMovingAverage,
            double rsi,
            long currentVolume,
            double averageVolume,
            String riskLevel
    ) {
        int score = 0;

        if (currentPrice > simpleMovingAverage) {
            score += 25;
        }

        if (exponentialMovingAverage > simpleMovingAverage) {
            score += 20;
        }

        if (currentVolume > averageVolume) {
            score += 20;
        }

        if (rsi >= 30 && rsi <= 70) {
            score += 20;
        } else if (rsi < 30) {
            score += 10;
        }

        if (riskLevel.equals("Low")) {
            score += 15;
        } else if (riskLevel.equals("Moderate")) {
            score += 8;
        }

        return score;
    }

}
