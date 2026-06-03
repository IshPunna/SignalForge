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
}