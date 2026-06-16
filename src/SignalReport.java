
public class SignalReport {

    public static String buildReport(
            String ticker,
            double currentPrice,
            double simpleMovingAverage,
            double exponentialMovingAverage,
            double volatility,
            String riskLevel,
            long currentVolume,
            double averageVolume,
            String finalSignal,
            String reasons
    ) {
        StringBuilder report = new StringBuilder();

        report.append("===== MARKET SIGNAL REPORT =====\n");
        report.append("Ticker: ").append(ticker).append("\n");
        report.append("Current Price: $").append(String.format("%.2f", currentPrice)).append("\n");
        report.append("Simple Moving Average: $").append(String.format("%.2f", simpleMovingAverage)).append("\n");
        report.append("Exponential Moving Average: $").append(String.format("%.2f", exponentialMovingAverage)).append("\n");
        report.append("Volatility: ").append(String.format("%.2f", volatility)).append("%\n");
        report.append("Risk Level: ").append(riskLevel).append("\n");
        report.append("Current Volume: ").append(currentVolume).append("\n");
        report.append("Average Volume: ").append(String.format("%.2f", averageVolume)).append("\n");
        report.append("Final Signal: ").append(finalSignal).append("\n");

        report.append("===== WHY THIS WAS FLAGGED =====\n");
        report.append(reasons);
        report.append("\n");

        report.append("PSA: This is an educational review only. Not real financial advice.\n");
        report.append("===============================");

        return report.toString();
    }

}
