
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StockCsvReader {

    public static ArrayList<PriceBar> readFile(String filePath) throws FileNotFoundException {
        ArrayList<PriceBar> bars = new ArrayList<>();

        Scanner in = new Scanner(new File(filePath));

        if (in.hasNextLine()) {
            in.nextLine(); // skip header
        }

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] parts = line.split(",");
            String ticker = parts[0];
            String date = parts[1];
            double open = Double.parseDouble(parts[2]);
            double high = Double.parseDouble(parts[3]);
            double low = Double.parseDouble(parts[4]);
            double close = Double.parseDouble(parts[5]);
            long volume = Long.parseLong(parts[6]);
            
            PriceBar bar = new PriceBar(ticker, date, open, high, low, close, volume);
            bars.add(bar);
        }

        in.close();
        return bars;
    }
}
