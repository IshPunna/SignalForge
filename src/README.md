# Java Trading Bot Project

This project is a Java-based stock market analysis tool. It will read stock price data and calculate useful trading metrics. The goal is to help identify possible trading signals.

## Current Features

- Stores daily stock data using the PriceBar class
- Calculates daily price range
- Calculates percent change between two prices
- Tests calculations through Main.java

## Classes

### PriceBar
Stores one day of stock price data, including ticker symbol, date, open, high, low, close, and shares traded.

### PercentChangeCalculator
Calculates percent change between an old price and a new price.

Formula:

((newPrice - oldPrice) / oldPrice) * 100

## Next Features

- Moving average calculator
- CSV stock data reader
- Basic trading signal logic