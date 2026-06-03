public class Main {

    public static void main(String args[]) {

        String ticker = "AAPL";

        double[] price = {10, 12, 14, 16, 18};
        long[] volume = {1000, 1200, 1300, 1500, 2000};

        double sma = StockMath.calculateSimpleMovingAverage(price, 3);
        double currentPrice = price[price.length - 1];

        double averageVolume = StockMath.calculateAvgVolume(volume);
        long currentVolume = volume[volume.length - 1];

        String combinedSignal = SignalIndicator.getCombinedSignal(
                currentPrice,
                sma,
                currentVolume,
                averageVolume
        );

        String report = SignalReport.buildReport(
                ticker,
                currentPrice,
                sma,
                currentVolume,
                averageVolume,
                combinedSignal
        );

        System.out.println(report);
    }
}