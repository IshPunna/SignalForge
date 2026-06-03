
public class StockMath {

    public static double calculatePercentChange(double oldPrice, double newPrice) {
        if (oldPrice == 0) {
            return 0;
        }

        double change = ((newPrice - oldPrice) / oldPrice) * 100;

        return change;
    }

    public static double calculateAvgPrice(double[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }

        double avg = sum / prices.length;

        return avg;
    }

    public static double calculateAvgVolume(long volumes[]) {
        if (volumes.length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < volumes.length; i++) {
            sum += volumes[i];
        }

        double avg = sum / volumes.length;

        return avg;
    }

    public static double calculateSimpleMovingAverage(double[] prices, int days) {
        if (days <= 0 || days > prices.length) {
            return 0;
        }

        double sum = 0;
        for (int i = prices.length - 1; i >= prices.length - days; i--) {
            sum += prices[i];
        }

        double avg = sum / days;

        return avg;
    }

    // ******************* Singal Logic ************************//
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
