package com.neelam;
class StockProfitInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double profit;
	private double loss;
	private int count;

	public StockProfitInfo(int cnt, double pt, double ls) {
		profit = pt;
		loss = ls;
		count = cnt;
	}

	public double getProfit() {
		return profit;
	}

	public double getLoss() {
		return loss;
	}

	public int getCount() {
		return count;
	}

	public String toString() {
		return ("count, profit, loss " + " " + count + " " + profit + loss);

	}
}