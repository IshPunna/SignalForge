# SignalForge

SignalForge is a Java-based market signal analysis project. The goal of this project is to build a beginner-friendly trading signal scanner that analyzes stock price and volume data, calculates technical indicators, scores watchlist opportunities, and explains why a stock was flagged.

This project is for educational and paper-trading review only. It does not provide real financial advice.

## Current Features

- Reads stock price and volume data from a CSV file
- Stores daily stock data using a `PriceBar` class
- Organizes stock data by ticker using `StockData`
- Builds a latest price index using a `HashMap`
- Supports a configurable watchlist of tickers
- Skips tickers that do not have enough data to analyze
- Calculates technical indicators:
  - Simple Moving Average, or SMA
  - Exponential Moving Average, or EMA
  - Percent volatility
  - RSI

- Calculates average volume
- Assigns a basic risk level based on volatility
- Generates bullish, bearish, or neutral-style signals
- Creates a signal score out of 100
- Explains why a stock was flagged
- Ranks valid watchlist results by signal score
- Prints a clean market signal report
- Prints a final watchlist ranking summary

## Current Signal Logic

SignalForge currently uses price, trend, volume, RSI, and risk level to generate a signal and score.

The basic signal logic checks:

- Whether the current price is above or below the moving average
- Whether current volume is above or below average volume
- Whether EMA is above or below SMA
- Whether RSI suggests the stock may be oversold, neutral, or overbought
- Whether volatility suggests low, moderate, or high risk

The scanner also uses configurable settings in `Main.java`, including:

```java
int movingAveragePeriod = 3;
int rsiPeriod = 2;
int minimumDataPoints = 3;
int watchScoreThreshold = 60;
```

If a stock's score is greater than or equal to the watch score threshold, it is marked as `FLAGGED` in the watchlist ranking.

## Project Structure

```text
src/
├── Main.java
├── PriceBar.java
├── StockCsvReader.java
├── CsvParseException.java
├── StockData.java
├── LatestPriceIndex.java
├── StockDataPrinter.java
├── StockMath.java
├── IndicatorCalculator.java
├── SignalIndicator.java
├── SignalResult.java
└── SignalReport.java
```

## File Responsibilities

`Main.java`
Runs the program, loads the CSV data, defines scan settings, loops through the watchlist, creates signal results, and prints reports/rankings.

`PriceBar.java`
Represents one day of stock data, including ticker, date, open price, high price, low price, close price, and volume.

`StockCsvReader.java`
Reads stock data from a CSV file and converts each row into a `PriceBar` object.

`CsvParseException.java`
Custom exception class for CSV parsing errors.

`StockData.java`
Stores a list of `PriceBar` objects and provides methods for filtering by ticker, getting close prices, getting volumes, and building the latest price index.

`LatestPriceIndex.java`
Uses a `HashMap` to store and retrieve the latest close price for each ticker.

`StockDataPrinter.java`
Prints loaded stock data, ticker-specific data, and the latest price index.

`StockMath.java`
Contains general stock math helper methods, such as percent change and average calculations.

`IndicatorCalculator.java`
Calculates technical indicators such as SMA, EMA, volatility, RSI, and average volume.

`SignalIndicator.java`
Handles signal logic, risk level logic, signal scoring, and reason generation.

`SignalResult.java`
Stores the structured result for one ticker, including indicators, score, signal, risk level, and reasons.

`SignalReport.java`
Builds a clean formatted report from a `SignalResult`.

## Example Output

```text
===== LATEST PRICE INDEX =====
MSFT -> $203.0
AAPL -> $108.0

===== SCAN SETTINGS =====
Moving Average Period: 3
RSI Period: 2
Minimum Data Points: 3
Watch Score Threshold: 60
=========================

===== MARKET SIGNAL REPORT =====
Ticker: AAPL
Current Price: $108.00
Simple Moving Average: $106.33
Exponential Moving Average: $107.04
Volatility: 0.98%
RSI: 100.00
Risk Level: Low
Current Volume: 900000
Average Volume: 1033333.33
Final Signal: Weak Bullish Signal
Signal Score: 60/100

----- WHY THIS WAS FLAGGED -----
- Price is above SMA, suggesting an upward trend.
- EMA is above SMA, suggesting recent momentum is improving.
- RSI is above 70, suggesting the stock may be overbought.
- Volume is below average, so the signal is weaker.
- Risk level is Low.

===============================

===== SKIPPED TICKER =====
Ticker: MSFT
Reason: Needs at least 3 data points.
==========================

===== WATCHLIST RANKING =====
1. AAPL | FLAGGED | Weak Bullish Signal | Score: 60/100

PSA: Educational review only. Not real financial advice.
```

## Next Steps

- Add more sample data for multiple tickers
- Make the watchlist user-configurable through command-line input
- Let users choose indicator periods and score thresholds at runtime
- Improve the scoring system with clearer rule weights
- Add a more flexible rule engine
- Add paper-trading journal support
- Add backtesting logic
- Add AI-generated explanations based only on the app’s calculated data
- Build a stronger command-line interface
- Eventually add a simple dashboard or web app interface
