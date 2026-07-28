public class ScanReportPrinter {

    public static void print(ScanReport report) {
        System.out.println();
        System.out.println("===== SCAN SETTINGS =====");
        System.out.println(
                "Moving Average Period: "
                        + report.getMovingAveragePeriod()
        );
        System.out.println(
                "RSI Period: "
                        + report.getRsiPeriod()
        );
        System.out.println(
                "Minimum Data Points: "
                        + report.getMinimumDataPoints()
        );
        System.out.println(
                "Watch Score Threshold: "
                        + report.getWatchScoreThreshold()
        );
        System.out.println("=============x============");

        for (SignalResult result : report.getResults()) {
            System.out.println();
            System.out.println(SignalReport.buildReport(result));
        }

        for (String skippedTicker : report.getSkippedTickers()) {
            System.out.println();
            System.out.println("===== SKIPPED TICKER =====");
            System.out.println(skippedTicker);
            System.out.println("============x==============");
        }

        System.out.println();
        System.out.println("===== WATCHLIST RANKING =====");

        int rank = 1;

        for (SignalResult result : report.getResults()) {
            String watchStatus;

            if (result.getScore()
                    >= report.getWatchScoreThreshold()) {
                watchStatus = "FLAGGED";
            } else {
                watchStatus = "REVIEW";
            }

            System.out.println(
                    rank + ". "
                            + result.getTicker()
                            + " | "
                            + watchStatus
                            + " | "
                            + result.getFinalSignal()
                            + " | Score: "
                            + result.getScore()
                            + "/100"
            );

            rank++;
        }

        System.out.println();
    }
}