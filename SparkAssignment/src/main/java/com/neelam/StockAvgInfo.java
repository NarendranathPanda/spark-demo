package com.neelam;

class StockAvgInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double open;
	private double close;
	private int count;

	public StockAvgInfo(int cnt, double cl, double opn) {
		count = cnt;
		close = cl;
		open = opn;
	}

	public StockAvgInfo(int cnt, double cl) {
		count = cnt;
		close = cl;
	}

	public double getClose() {
		return close;
	}

	public double getOpen() {
		return open;
	}

	public int getCount() {
		return count;
	}

	public String toString() {
		return ("count, close, open " + " " + count + " " + close + open);

	}
}