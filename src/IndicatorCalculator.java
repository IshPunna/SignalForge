
/**
 * IndicatorCalculator = calculations for market indicators
 */
public class IndicatorCalculator {

    public static double calculateSMA(double[] values, int period) {
        if (values == null || period <= 0 || values.length < period) {
            return 0.0;
        }

        double sum = 0.0;

        for (int i = values.length - period; i < values.length; i++) {
            sum += values[i];
        }

        return sum / period;
    }

    public static double calculateEMA(double[] values, int period) {
        if (values == null || period <= 0 || values.length < period) {
            return 0.0;
        }

        double multiplier = 2.0 / (period + 1);

        double ema = calculateSMA(values, period);

        for (int i = values.length - period; i < values.length; i++) {
            ema = (values[i] - ema) * multiplier + ema;
        }

        return ema;
    }

    public static double calculateAverageVolume(long[] volumes) {
        if (volumes == null || volumes.length == 0) {
            return 0.0;
        }

        double sum = 0.0;

        for (int i = 0; i < volumes.length; i++) {
            sum += volumes[i];
        }

        return sum / volumes.length;
    }

    public static double calculateVolatility(double[] values) {
        if (values == null || values.length < 2) {
            return 0.0;
        }

        double[] dailyReturns = new double[values.length - 1];

        for (int i = 1; i < values.length; i++) {
            dailyReturns[i - 1] = ((values[i] - values[i - 1]) / values[i - 1]) * 100;
        }

        double averageReturn = 0.0;

        for (int i = 0; i < dailyReturns.length; i++) {
            averageReturn += dailyReturns[i];
        }

        averageReturn = averageReturn / dailyReturns.length;

        double squaredDifferenceSum = 0.0;

        for (int i = 0; i < dailyReturns.length; i++) {
            double difference = dailyReturns[i] - averageReturn;
            squaredDifferenceSum += difference * difference;
        }

        double variance = squaredDifferenceSum / dailyReturns.length;

        return Math.sqrt(variance);
    }

}
