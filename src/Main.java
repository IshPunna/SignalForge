import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
            throws FileNotFoundException {

        String[] watchlist = {"AAPL", "MSFT"};

        int movingAveragePeriod = 3;
        int rsiPeriod = 2;
        int minimumDataPoints = 3;
        int watchScoreThreshold = 60;

        ArrayList<PriceBar> bars =
                StockCsvReader.readFile(
                        "data/sample_stock_data.csv"
                );

        StockData stockData = new StockData(bars);

        LatestPriceIndex latestPriceIndex =
                stockData.buildLatestPriceIndex();

        StockDataPrinter.printLatestPriceIndex(
                latestPriceIndex
        );

        SignalScanner scanner =
                new SignalScanner(
                        stockData,
                        movingAveragePeriod,
                        rsiPeriod,
                        minimumDataPoints,
                        watchScoreThreshold
                );

        ScanReport report = scanner.scan(watchlist);

        ScanReportPrinter.print(report);

        AlertQueue alertQueue = buildAlertQueue(report);

        printAlerts(alertQueue);

        System.out.println(
                "PSA: Educational review only. "
                        + "Not real financial advice."
        );
    }

    private static AlertQueue buildAlertQueue(
            ScanReport report
    ) {
        int capacity =
                Math.max(1, report.getResults().size());

        AlertQueue alertQueue =
                new AlertQueue(capacity);

        for (SignalResult result : report.getResults()) {
            if (result.getScore()
                    >= report.getWatchScoreThreshold()) {
                alertQueue.addAlert(result);
            }
        }

        return alertQueue;
    }

    private static void printAlerts(
            AlertQueue alertQueue
    ) {
        System.out.println("===== FLAGGED ALERTS =====");

        if (alertQueue.isEmpty()) {
            System.out.println(
                    "No stocks met the alert threshold."
            );
        }

        while (!alertQueue.isEmpty()) {
            SignalResult result =
                    alertQueue.removeNextAlert();

            System.out.println(
                    result.getTicker()
                            + " | "
                            + result.getFinalSignal()
                            + " | Score: "
                            + result.getScore()
                            + "/100"
            );
        }

        System.out.println("==========================");
        System.out.println();
    }
}