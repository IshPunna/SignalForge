# SignalForge

SignalForge is a Java-based stock signal analysis project. The goal of this project is to build a beginner-friendly trading signal tool that analyzes stock price and volume data, calculates technical indicators, and generates simple bullish, bearish, or neutral signals.

## Current Features

* Stores daily stock price data using a `PriceBar` class
* Calculates percent change
* Calculates average price
* Calculates average volume
* Calculates a simple moving average
* Compares current price to the moving average
* Compares current volume to average volume
* Generates a combined trading signal
* Prints a clean signal report

## Current Signal Logic

SignalForge currently uses price and volume to generate a basic signal.

* If the current price is above the moving average and current volume is above average volume, the signal is **Strong Bullish**
* If the current price is above the moving average but current volume is not above average volume, the signal is **Weak Bullish**
* If the current price is below the moving average and current volume is above average volume, the signal is **Strong Bearish**
* If the current price is below the moving average but current volume is not above average volume, the signal is **Weak Bearish**
* If the current price equals the moving average, the signal is **Neutral**

## Project Structure

```text
src/
├── Main.java
├── PriceBar.java
├── StockMath.java
├── SignalIndicator.java
└── SignalReport.java
```

### File Responsibilities

`Main.java`
Runs the program and tests the current version of the project.

`PriceBar.java`
Represents one day of stock data, including ticker, date, open price, high price, low price, close price, and volume.

`StockMath.java`
Handles stock-related calculations such as percent change, average price, average volume, and simple moving average.

`SignalIndicator.java`
Generates trading signals based on price and volume conditions.

`SignalReport.java`
Builds a clean formatted report for the final signal output.

## Example Output

```text
===== SIGNAL REPORT =====
Ticker: AAPL
Current Price: $18.0
Simple Moving Average: $16.0
Current Volume: 2000
Average Volume: 1400.0
Final Signal: Strong Bullish Signal
=========================
```

## Next Steps

* Read stock price and volume data from a CSV file
* Support multiple tickers
* Add more technical indicators
* Add backtesting logic
* Improve signal scoring
* Add AI-generated explanations for signals
* Build a stronger command-line interface
