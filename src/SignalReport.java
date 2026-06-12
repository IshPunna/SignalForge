
public class SignalReport {

    public static String buildReport(
            String ticker,
            double currentPrice,
            double movingAverage,
            long currentVolume,
            double averageVolume,
            String finalSignal
    ) {
        StringBuilder report = new StringBuilder();

         report.append("===== SIGNAL REPORT =====\n");
        report.append("Ticker: ").append(ticker).append("\n");
        report.append("Current Price: $").append(String.format("%.2f", currentPrice)).append("\n");
        report.append("Simple Moving Average: $").append(String.format("%.2f", movingAverage)).append("\n");
        report.append("Current Volume: ").append(currentVolume).append("\n");
        report.append("Average Volume: ").append(String.format("%.2f", averageVolume)).append("\n");
        report.append("Final Signal: ").append(finalSignal).append("\n");
        report.append("=========================");

        return report.toString();
    }
}
