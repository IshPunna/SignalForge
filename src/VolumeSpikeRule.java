/**
 * Evaluates whether the stock's current trading volume
 * is significantly higher than its average volume
 */
public class VolumeSpikeRule implements TradingRule {

    private static final double SPIKE_MULTIPLIER = 1.5;

    @Override
    public int calculateScore(CalculatedMarketIndicators indicators) {
        double spikeThreshold =
                indicators.getAverageVolume() * SPIKE_MULTIPLIER;

        if (indicators.getCurrentVolume() >= spikeThreshold) {
            return 20;
        }

        return 0;
    }

    @Override
    public String buildReason(CalculatedMarketIndicators indicators) {
        double spikeThreshold =
                indicators.getAverageVolume() * SPIKE_MULTIPLIER;

        if (indicators.getCurrentVolume() >= spikeThreshold) {
            return "- Volume is at least 50% above average, indicating a volume spike.\n";
        } 
        else if (indicators.getCurrentVolume()
                > indicators.getAverageVolume()) {
            return "- Volume is above average, but not high enough to be considered a spike.\n";
        }

        return "- Volume is at or below average, so the signal has less support.\n";
    }
}