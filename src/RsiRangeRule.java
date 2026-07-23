/**
 * Evaluates whether the RSI suggests neutral,
 * oversold, or overbought market conditions
 */
public class RsiRangeRule implements TradingRule {

    @Override
    public int calculateScore(CalculatedMarketIndicators indicators) {
        double rsi = indicators.getRsi();

        if (rsi >= 30 && rsi <= 70) {
            return 20;
        } else if (rsi < 30) {
            return 10;
        }

        return 0;
    }

    @Override
    public String buildReason(CalculatedMarketIndicators indicators) {
        double rsi = indicators.getRsi();

        if (rsi < 30) {
            return "- RSI is below 30, suggesting the stock may be oversold.\n";
        } else if (rsi > 70) {
            return "- RSI is above 70, suggesting the stock may be overbought.\n";
        }

        return "- RSI is between 30 and 70, suggesting neutral conditions.\n";
    }
}