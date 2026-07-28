import java.util.ArrayList;

public class SignalForgeTests {

    private static int passedTests = 0;

    public static void main(String[] args) {
        testBullishEvaluation();
        testExactVolumeSpikeThreshold();
        testScannerAndSkippedTicker();
        testAlertQueue();

        System.out.println(
                "All " + passedTests + " SignalForge tests passed."
        );
    }

    private static void testBullishEvaluation() {
        CalculatedMarketIndicators indicators =
                new CalculatedMarketIndicators(
                        110.0,
                        100.0,
                        105.0,
                        1.0,
                        50.0,
                        2000,
                        1000.0
                );

        MarketSignalEvaluator evaluator =
                new MarketSignalEvaluator();

        check(
                evaluator.calculateTotalScore(indicators) == 100,
                "Bullish indicators should receive 100 points."
        );
    }

    private static void testExactVolumeSpikeThreshold() {
        CalculatedMarketIndicators indicators =
                new CalculatedMarketIndicators(
                        100.0,
                        100.0,
                        100.0,
                        1.0,
                        50.0,
                        1500,
                        1000.0
                );

        VolumeSpikeRule rule = new VolumeSpikeRule();

        check(
                rule.calculateScore(indicators) == 20,
                "Exactly 1.5 times average volume should earn 20 points."
        );

        check(
                rule.buildReason(indicators).contains("volume spike"),
                "The reason should identify the exact threshold as a volume spike."
        );
    }

    private static void testScannerAndSkippedTicker() {
        ArrayList<PriceBar> bars = new ArrayList<>();

        bars.add(
                new PriceBar(
                        "AAPL",
                        "2026-01-01",
                        99,
                        101,
                        98,
                        100,
                        1000
                )
        );

        bars.add(
                new PriceBar(
                        "AAPL",
                        "2026-01-02",
                        100,
                        106,
                        99,
                        105,
                        1200
                )
        );

        bars.add(
                new PriceBar(
                        "AAPL",
                        "2026-01-03",
                        105,
                        111,
                        104,
                        110,
                        3000
                )
        );

        bars.add(
                new PriceBar(
                        "SHORT",
                        "2026-01-01",
                        10,
                        11,
                        9,
                        10,
                        100
                )
        );

        SignalScanner scanner =
                new SignalScanner(
                        new StockData(bars),
                        3,
                        2,
                        3,
                        60
                );

        ScanReport report =
                scanner.scan(
                        new String[]{"AAPL", "SHORT"}
                );

        check(
                report.getResults().size() == 1,
                "Only one ticker should have enough data."
        );

        check(
                report.getResults().get(0)
                        .getTicker()
                        .equals("AAPL"),
                "AAPL should appear in the scan results."
        );

        check(
                report.getSkippedTickers().size() == 1,
                "The scanner should record the skipped ticker."
        );
    }

    private static void testAlertQueue() {
        SignalResult firstResult =
                createTestResult("AAPL", 80);

        SignalResult secondResult =
                createTestResult("MSFT", 70);

        AlertQueue alertQueue = new AlertQueue(1);

        check(
                alertQueue.addAlert(firstResult),
                "The first alert should be added."
        );

        check(
                !alertQueue.addAlert(secondResult),
                "The queue should reject an alert when full."
        );

        check(
                alertQueue.peekNextAlert()
                        .getTicker()
                        .equals("AAPL"),
                "AAPL should be the next alert."
        );

        check(
                alertQueue.removeNextAlert() == firstResult,
                "Alerts should be removed in FIFO order."
        );

        check(
                alertQueue.isEmpty(),
                "The queue should be empty after removal."
        );
    }

    private static SignalResult createTestResult(
            String ticker,
            int score
    ) {
        return new SignalResult(
                ticker,
                100.0,
                95.0,
                97.0,
                1.0,
                50.0,
                "Low",
                1500,
                1000.0,
                score,
                "Strong Bullish Signal",
                "Test reason."
        );
    }

    private static void check(
            boolean condition,
            String failureMessage
    ) {
        if (!condition) {
            throw new AssertionError(failureMessage);
        }

        passedTests++;
    }
}