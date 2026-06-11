
import java.util.ArrayList;
import java.util.HashMap;

public class StockData {

    private ArrayList<PriceBar> bars;

    public StockData(ArrayList<PriceBar> bars) {
        this.bars = bars;
    }

    public ArrayList<PriceBar> getBarsForTicker(String ticker) {
        ArrayList<PriceBar> selectedBars = new ArrayList<>();

        for (PriceBar bar : bars) {
            if (bar.getTickerSymbol().equals(ticker)) {
                selectedBars.add(bar);
            }
        }

        return selectedBars;
    }

    public double[] getClosePrices(String ticker) {
        ArrayList<PriceBar> selectedBars = getBarsForTicker(ticker);

        double[] prices = new double[selectedBars.size()];

        for (int i = 0; i < selectedBars.size(); i++) {
            prices[i] = selectedBars.get(i).getClosePrice();
        }

        return prices;
    }

    public long[] getVolumes(String ticker) {
        ArrayList<PriceBar> selectedBars = getBarsForTicker(ticker);

        long[] volumes = new long[selectedBars.size()];

        for (int i = 0; i < selectedBars.size(); i++) {
            volumes[i] = selectedBars.get(i).getSharesTraded();
        }

        return volumes;
    }

    public HashMap<String, Double> getLatestClosePriceMap() {
        HashMap<String, Double> latestPrices = new HashMap<>();

        for (PriceBar bar : bars) {
            latestPrices.put(bar.getTickerSymbol(), bar.getClosePrice());
        }

        return latestPrices;
    }
}
