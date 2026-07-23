
public class SignalIndicator {

    public static String getCombinedSignal(
        double currentPrice,
        double movingAverage,
        long currentVolume,
        double averageVolume
) {
    double volumeSpikeThreshold = averageVolume * 1.5;
    boolean hasVolumeSpike = currentVolume >= volumeSpikeThreshold;

    if (currentPrice > movingAverage && hasVolumeSpike) {
        return "Strong Bullish Signal";
    } else if (currentPrice > movingAverage) {
        return "Weak Bullish Signal";
    } else if (currentPrice < movingAverage && hasVolumeSpike) {
        return "Strong Bearish Signal";
    } else if (currentPrice < movingAverage) {
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

}
