/**
 * Represents one rule used to evaluate calculated market indicators
 */
public interface TradingRule {

    int calculateScore(CalculatedMarketIndicators indicators);

    String buildReason(CalculatedMarketIndicators indicators);
}