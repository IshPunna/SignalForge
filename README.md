# SignalForge

SignalForge is a Java-based market signal analysis project. The goal is to build a beginner-friendly trading signal scanner that analyzes stock price and volume data, calculates technical indicators, scores watchlist opportunities, and explains why each stock received its result.

This project is for educational and paper-trading review only. It does not provide real financial advice.

## Current Features

- Reads stock price and volume data from a CSV file
- Stores daily stock data using a `PriceBar` class
- Organizes stock data by ticker using `StockData`
- Builds a latest-price index using a `HashMap`
- Supports a configurable watchlist
- Skips tickers that do not have enough data
- Calculates:
  - Simple Moving Average, or SMA
  - Exponential Moving Average, or EMA
  - Percent volatility
  - RSI
  - Average volume
- Groups calculated values into a structured indicator object
- Evaluates indicators using modular trading rules
- Assigns a basic risk level based on volatility
- Generates bullish, bearish, or neutral-style signals
- Creates a signal score out of 100
- Explains the reasoning behind each result
- Ranks valid watchlist results by score
- Marks results as `FLAGGED` or `REVIEW`
- Prints formatted market signal reports
- Includes tests for the evaluator and volume-spike boundary

## Modular Trading Rules

SignalForge separates its scoring logic into four trading-rule classes.

### Moving Average Rule

- Price above SMA: 25 points
- EMA above SMA: 20 points

Maximum: 45 points

### Volume Spike Rule

- Current volume at least 50% above average volume: 20 points
- Volume above average but below the spike threshold: 0 points
- Volume at or below average: 0 points

Maximum: 20 points

### RSI Range Rule

- RSI between 30 and 70: 20 points
- RSI below 30: 10 points
- RSI above 70: 0 points

Maximum: 20 points

### Volatility Rule

- Volatility below 2%: 15 points
- Volatility from 2% to below 5%: 8 points
- Volatility of 5% or higher: 0 points

Maximum: 15 points

The highest possible signal score is 100.

## Signal Classification

The final bullish or bearish signal is based on the current price’s relationship to the moving average and whether the stock has a volume spike.

- Price above SMA with a volume spike: Strong Bullish Signal
- Price above SMA without a volume spike: Weak Bullish Signal
- Price below SMA with a volume spike: Strong Bearish Signal
- Price below SMA without a volume spike: Weak Bearish Signal
- Price equal to SMA: Neutral Signal

A volume spike occurs when current volume is at least 1.5 times the average volume.

## Configurable Scan Settings

The scanner currently uses configurable settings in `Main.java`:

```java
int movingAveragePeriod = 3;
int rsiPeriod = 2;
int minimumDataPoints = 3;
int watchScoreThreshold = 60;
```

If a stock’s score is greater than or equal to the watch score threshold, it is marked as `FLAGGED`. Otherwise, it is marked as `REVIEW`.

## Project Structure

```text
SignalForge/
├── data/
│   └── sample_stock_data.csv
├── src/
│   ├── Main.java
│   ├── PriceBar.java
│   ├── StockCsvReader.java
│   ├── CsvParseException.java
│   ├── StockData.java
│   ├── LatestPriceIndex.java
│   ├── StockDataPrinter.java
│   ├── StockMath.java
│   ├── IndicatorCalculator.java
│   ├── CalculatedMarketIndicators.java
│   ├── TradingRule.java
│   ├── MovingAverageRule.java
│   ├── VolumeSpikeRule.java
│   ├── RsiRangeRule.java
│   ├── VolatilityRule.java
│   ├── MarketSignalEvaluator.java
│   ├── SignalIndicator.java
│   ├── SignalResult.java
│   └── SignalReport.java
├── tests/
│   └── MarketSignalEvaluatorTest.java
└── README.md
```

## Main Class Responsibilities

`Main.java`  
Runs the program, defines the scan settings, loads the CSV data, loops through the watchlist, calculates indicators, creates results, and prints reports and rankings.

`PriceBar.java`  
Represents one day of stock data, including the ticker, date, open price, high price, low price, close price, and volume.

`StockCsvReader.java`  
Reads the CSV file and converts each valid row into a `PriceBar` object.

`CsvParseException.java`  
Provides a custom exception for CSV parsing errors.

`StockData.java`  
Stores the price bars and provides methods for filtering by ticker, retrieving close prices and volumes, and building the latest-price index.

`LatestPriceIndex.java`  
Uses a `HashMap` to store and retrieve the latest close price for each ticker.

`StockDataPrinter.java`  
Prints loaded stock data, ticker-specific data, and the latest-price index.

`StockMath.java`  
Contains general stock-math helper methods, including percent-change and average calculations.

`IndicatorCalculator.java`  
Calculates SMA, EMA, volatility, RSI, and average volume.

`CalculatedMarketIndicators.java`  
Groups all calculated indicator values into one object that can be passed to each trading rule.

`TradingRule.java`  
Defines the methods that every modular trading rule must implement.

`MovingAverageRule.java`  
Evaluates price, SMA, and EMA trend conditions.

`VolumeSpikeRule.java`  
Determines whether the current volume meets the 50% volume-spike threshold.

`RsiRangeRule.java`  
Evaluates whether RSI is oversold, neutral, or overbought.

`VolatilityRule.java`  
Evaluates volatility and assigns points based on risk.

`MarketSignalEvaluator.java`  
Runs every trading rule and combines their scores and explanations.

`SignalIndicator.java`  
Classifies the final signal and assigns the volatility-based risk level.

`SignalResult.java`  
Stores the structured result for one ticker.

`SignalReport.java`  
Builds a formatted market signal report from a `SignalResult`.

`MarketSignalEvaluatorTest.java`  
Tests a 100-point bullish result, a bearish result, and the exact volume-spike boundary.

## Sample Watchlist Ranking

```text
===== WATCHLIST RANKING =====
1. AAPL | FLAGGED | Weak Bullish Signal | Score: 60/100
2. MSFT | REVIEW | Strong Bearish Signal | Score: 45/100

PSA: Educational review only. Not real financial advice.
```

This sample tests two different market conditions:

- AAPL has an upward price trend but lacks a volume spike.
- MSFT has a downward price trend with a major volume spike.

## How to Compile and Run

From the main `SignalForge` folder, compile the source files:

```powershell
javac -d output src/*.java
```

Run the program:

```powershell
java -cp output Main
```

## How to Run the Tests

Compile both the source files and test files:

```powershell
javac -d output src/*.java tests/*.java
```

Run the evaluator tests:

```powershell
java -cp output MarketSignalEvaluatorTest
```

Expected result:

```text
Perfect bullish score passed.
Bearish score passed.
Exact volume-spike boundary passed.
All MarketSignalEvaluator tests passed!
```

## Next Steps

- Make the watchlist configurable through command-line input
- Let users choose indicator periods and thresholds at runtime
- Move more signal classification logic into modular rules
- Add additional evaluator and indicator tests
- Add paper-trading journal support
- Add backtesting logic
- Add AI-generated explanations based only on calculated data
- Build a stronger command-line interface
- Eventually create a simple dashboard or web interface