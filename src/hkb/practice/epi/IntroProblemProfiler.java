package hkb.practice.epi;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import hkb.practice.epi.IntroProblem;

public class IntroProblemProfiler {

	public IntroProblemProfiler() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		int[] trial_size = {10,30,60,100,300,600,1000,3000,6000,10000};
		int reps = 100;
		
		double[] times_bf = new double[trial_size.length];
		double[] times_dc = new double[trial_size.length];
		double[] times_ln = new double[trial_size.length];
		
		for (int i = 0; i<trial_size.length; i++){
			
			for (int j = 0; j<reps; j++){
				Random rand = new Random();
							
				double[] prices = new double[trial_size[i]];
				for (int k = 0; k < trial_size[i]; k++){
					prices[k] = rand.nextDouble() * 100;
				}
				
				double time_start = System.nanoTime();
				IntroProblem.findMaximumProfit_BruteForce(prices);
				double time_end = System.nanoTime();
				times_bf[i] = time_end - time_start;
				
				time_start = System.nanoTime();
				IntroProblem.findMaximumProfit_DivideAndConquer(prices);
				time_end = System.nanoTime();
				times_dc[i] = time_end - time_start;
				
				time_start = System.nanoTime();
				IntroProblem.findMaximumProfit_Linear(prices);
				time_end = System.nanoTime();
				times_ln[i] = time_end - time_start;
			
			}
			times_bf[i] = times_bf[i] / (double)reps;
			times_dc[i] = times_dc[i] / (double)reps;
			times_ln[i] = times_ln[i] / (double)reps;
			
		}
		
		//printing to standard out
		System.out.println("Times");
		System.out.println(String.format("%7s %7s %7s %7s","n","BF","DC","LN"));
		for (int i = 0; i<trial_size.length; i++){
			String s = String.format("%7d %7d %7d %7d", Math.round(trial_size[i]), Math.round(times_bf[i]), Math.round(times_dc[i]), Math.round(times_ln[i]));
			System.out.println(s);

		}
		
		//writing to file
		PrintWriter writer;
		try {
			writer = new PrintWriter("IntroProblemProfiler_Output.csv", "UTF-8");
			writer.println("n,BruteForce,DivideNConquer,LinearTime");
			for (int i = 0; i < trial_size.length; i++){
				writer.print(trial_size[i]);
				writer.print(",");
				writer.print(times_bf[i]);
				writer.print(",");
				writer.print(times_dc[i]);
				writer.print(",");
				writer.print(times_ln[i]);
				writer.println();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
