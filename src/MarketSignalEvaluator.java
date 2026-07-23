import java.util.ArrayList;
import java.util.List;

public class MarketSignalEvaluator {

    private final List<TradingRule> rules;

    public MarketSignalEvaluator() {
        rules = new ArrayList<>();

        rules.add(new MovingAverageRule());
        rules.add(new VolumeSpikeRule());
        rules.add(new RsiRangeRule());
        rules.add(new VolatilityRule());
    }

    public int calculateTotalScore( CalculatedMarketIndicators indicators) {
        int totalScore = 0;

        for (TradingRule rule : rules) {
            totalScore += rule.calculateScore(indicators);
        }

        return totalScore;
    }

    public String buildAllReasons(CalculatedMarketIndicators indicators) {
        StringBuilder allReasons = new StringBuilder();

        for (TradingRule rule : rules) {
            allReasons.append(rule.buildReason(indicators));
        }

        return allReasons.toString();
    }
}