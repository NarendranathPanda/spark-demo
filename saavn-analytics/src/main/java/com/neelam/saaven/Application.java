package com.neelam.saaven;

import org.apache.spark.sql.AnalysisException;

import com.neelam.saaven.app.SaavnUserClustering;

public class Application {

	public static void main(String[] args) {
		System.out.println("Neelam Poddar | email: poddar.neelam@gmail.com | ");
		try {
			// change here for you solution 
			SaavnUserClustering.start(args);
		} catch (AnalysisException e) {
			System.err.println("Application error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
