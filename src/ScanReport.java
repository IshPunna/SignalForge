import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScanReport {

    private final ArrayList<SignalResult> results;
    private final ArrayList<String> skippedTickers;
    private final int movingAveragePeriod;
    private final int rsiPeriod;
    private final int minimumDataPoints;
    private final int watchScoreThreshold;

    public ScanReport(
            List<SignalResult> results,
            List<String> skippedTickers,
            int movingAveragePeriod,
            int rsiPeriod,
            int minimumDataPoints,
            int watchScoreThreshold
    ) {
        this.results = new ArrayList<>(results);

        this.results.sort(
                Comparator.comparingInt(SignalResult::getScore)
                        .reversed()
                        .thenComparing(SignalResult::getTicker)
        );

        this.skippedTickers = new ArrayList<>(skippedTickers);
        this.movingAveragePeriod = movingAveragePeriod;
        this.rsiPeriod = rsiPeriod;
        this.minimumDataPoints = minimumDataPoints;
        this.watchScoreThreshold = watchScoreThreshold;
    }

    public ArrayList<SignalResult> getResults() {
        return new ArrayList<>(results);
    }

    public ArrayList<String> getSkippedTickers() {
        return new ArrayList<>(skippedTickers);
    }

    public int getMovingAveragePeriod() {
        return movingAveragePeriod;
    }

    public int getRsiPeriod() {
        return rsiPeriod;
    }

    public int getMinimumDataPoints() {
        return minimumDataPoints;
    }

    public int getWatchScoreThreshold() {
        return watchScoreThreshold;
    }
}