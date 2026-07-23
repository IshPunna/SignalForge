/**
 * Evaluates price trends using the SMA and EMA
 */
public class MovingAverageRule implements TradingRule {

    @Override
    public int calculateScore(CalculatedMarketIndicators indicators) {
        int score = 0;

        if (indicators.getCurrentPrice()
                > indicators.getSimpleMovingAverage()) {
            score += 25;
        }

        if (indicators.getExponentialMovingAverage()
                > indicators.getSimpleMovingAverage()) {
            score += 20;
        }

        return score;
    }

    @Override
    public String buildReason(CalculatedMarketIndicators indicators) {
        StringBuilder reason = new StringBuilder();

        if (indicators.getCurrentPrice()
                > indicators.getSimpleMovingAverage()) {
            reason.append(
                    "- Price is above SMA, suggesting an upward trend.\n"
            );
        } 
        else if (indicators.getCurrentPrice()
                < indicators.getSimpleMovingAverage()) {
            reason.append(
                    "- Price is below SMA, suggesting a weaker trend.\n"
            );
        } 
        else {
            reason.append(
                    "- Price is equal to SMA, suggesting a neutral trend.\n"
            );
        }

        if (indicators.getExponentialMovingAverage()
                > indicators.getSimpleMovingAverage()) {
            reason.append(
                    "- EMA is above SMA, suggesting recent momentum is improving.\n"
            );
        } 
        else if (indicators.getExponentialMovingAverage()
                < indicators.getSimpleMovingAverage()) {
            reason.append(
                    "- EMA is below SMA, suggesting recent momentum is weakening.\n"
            );
        } 
        else {
            reason.append(
                    "- EMA and SMA are equal, suggesting neutral momentum.\n"
            );
        }

        return reason.toString();
    }
}