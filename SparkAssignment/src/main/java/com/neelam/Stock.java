package com.neelam;

class Stock implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String symbol;
	private String timestamp;
	private double high;
	private double close;
	private double open;
	private double low;
	private int volume;

	public Stock(String sym, String time) {
		symbol = sym;
		timestamp = time;
	}

	public Stock() {

	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String sym) {
		symbol = sym;
	}

	public void setTimeStamp(String tmp) {
		timestamp = tmp;
	}

	public void setHigh(double val) {
		high = val;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double val) {
		close = val;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double val) {
		open = val;
	}

	public void setLow(double val) {
		low = val;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int val) {
		volume = val;
	}

	public String toString() {
		return (symbol + " " + timestamp + " " + high + " " + low + " " + "\n" + "\t" + open + " " + close + " "
				+ volume);

	}
}
