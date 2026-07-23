public class MarketSignalEvaluatorTest {

    public static void main(String[] args) {
        testPerfectBullishScore();
        testBearishScore();
        testExactVolumeSpikeBoundary();

        System.out.println("All MarketSignalEvaluator tests passed!");
    }

    private static void testPerfectBullishScore() {
        CalculatedMarketIndicators indicators =
                new CalculatedMarketIndicators(
                        110,
                        100,
                        105,
                        1,
                        50,
                        1500000,
                        1000000
                );

        MarketSignalEvaluator evaluator =
                new MarketSignalEvaluator();

        int actualScore =
                evaluator.calculateTotalScore(indicators);

        assertScore("Perfect bullish score", 100, actualScore);
    }

    private static void testBearishScore() {
        CalculatedMarketIndicators indicators =
                new CalculatedMarketIndicators(
                        90,
                        100,
                        95,
                        1,
                        20,
                        1500000,
                        1000000
                );

        MarketSignalEvaluator evaluator =
                new MarketSignalEvaluator();

        int actualScore =
                evaluator.calculateTotalScore(indicators);

        assertScore("Bearish score", 45, actualScore);
    }

    private static void testExactVolumeSpikeBoundary() {
        CalculatedMarketIndicators indicators =
                new CalculatedMarketIndicators(
                        100,
                        100,
                        100,
                        1,
                        50,
                        1500000,
                        1000000
                );

        VolumeSpikeRule rule = new VolumeSpikeRule();

        int actualScore = rule.calculateScore(indicators);

        assertScore("Exact volume-spike boundary", 20, actualScore);
    }

    private static void assertScore(
            String testName,
            int expectedScore,
            int actualScore
    ) {
        if (expectedScore != actualScore) {
            throw new AssertionError(
                    testName
                            + " failed. Expected "
                            + expectedScore
                            + ", but received "
                            + actualScore
            );
        }

        System.out.println(testName + " passed.");
    }
}