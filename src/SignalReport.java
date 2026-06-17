
public class SignalReport {

    public static String buildReport(SignalResult result) {
        StringBuilder report = new StringBuilder();

        report.append("===== MARKET SIGNAL REPORT =====\n");
        report.append("Ticker: ").append(result.getTicker()).append("\n");
        report.append("Current Price: $").append(String.format("%.2f", result.getCurrentPrice())).append("\n");
        report.append("Simple Moving Average: $").append(String.format("%.2f", result.getSimpleMovingAverage())).append("\n");
        report.append("Exponential Moving Average: $").append(String.format("%.2f", result.getExponentialMovingAverage())).append("\n");
        report.append("Volatility: ").append(String.format("%.2f", result.getVolatility())).append("%\n");
        report.append("RSI: ").append(String.format("%.2f", result.getRsi())).append("\n");
        report.append("Risk Level: ").append(result.getRiskLevel()).append("\n");
        report.append("Current Volume: ").append(result.getCurrentVolume()).append("\n");
        report.append("Average Volume: ").append(String.format("%.2f", result.getAverageVolume())).append("\n");
        report.append("Final Signal: ").append(result.getFinalSignal()).append("\n");
        report.append("Signal Score: ").append(result.getScore()).append("/100\n\n");
        report.append("----- WHY THIS WAS FLAGGED -----\n");
        report.append(result.getReasons()).append("\n");

        report.append("===============================");

        return report.toString();
    }
}
