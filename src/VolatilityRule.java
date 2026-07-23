/**
 * Evaluates the stock's risk level based on its volatility
 */
public class VolatilityRule implements TradingRule {

    @Override
    public int calculateScore(CalculatedMarketIndicators indicators) {
        double volatility = indicators.getVolatility();

        if (volatility < 2) {
            return 15;
        } 
        else if (volatility < 5) {
            return 8;
        }

        return 0;
    }

    @Override
    public String buildReason(CalculatedMarketIndicators indicators) {
        double volatility = indicators.getVolatility();

        if (volatility < 2) {
            return "- Volatility is low, indicating a lower level of risk.\n";
        } 
        else if (volatility < 5) {
            return "- Volatility is moderate, indicating a moderate level of risk.\n";
        }

        return "- Volatility is high, indicating a higher level of risk.\n";
    }
}