package hkb.practice.epi;

import java.util.Arrays;
import java.util.Random;

import hkb.practice.epi.IntroProblem;

public class IntroProblemTest {

	public IntroProblemTest() {
		// TODO Auto-generated constructor stub
	}

	/**Tests for the introductory problem on EPI pg 1
	 * 
	 */
	public static void test(){
		
		//hand-coded tests
		double[] t1 = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0};
		int[] a1 = {0,7};
		
		double[] t2 = {-1.0,-2.0,-3.0,-4.0,-5.0,-6.0,-7.0,-8.0};
		int[] a2 = {0,1};
		
		double[] t3 = {4.0,2.0,100.0,4.0,5.0,6.0,7.0,8.0};
		int[] a3 = {1,2};
		
		//test brute force
		int[] result1 = IntroProblem.findMaximumProfit_BruteForce(t1);
		if (!Arrays.equals(a1, result1)){
			System.out.println("Test 1 (Brute Force) Failed");
			System.out.println("  Result: " + result1[0] + ", " + result1[1]);
			System.out.println("  Answer: " + a1[0] + ", " + a1[1]);
		}
		int[] result2 = IntroProblem.findMaximumProfit_BruteForce(t2);
		if (!Arrays.equals(a2, result2)){
			System.out.println("Test 2 (Brute Force) Failed");
			System.out.println("  Result: " + result2[0] + ", " + result2[1]);
			System.out.println("  Answer: " + a2[0] + ", " + a2[1]);
		}
		int[] result3 = IntroProblem.findMaximumProfit_BruteForce(t3);
		if (!Arrays.equals(a3, result3)){
			System.out.println("Test 3 (Brute Force) Failed");
			System.out.println("  Result: " + result3[0] + ", " + result3[1]);
			System.out.println("  Answer: " + a3[0] + ", " + a3[1]);
		}
		
		//random tests of increasing size
		for (int i = 1; i<12; i++){
			Random rand = new Random();
			int array_size = (int) Math.pow(2, i);
			
			double[] prices = new double[array_size];
			for (int j = 0; j<array_size; j++){
				prices[j] = rand.nextDouble() * 100;
			}
			int[] ans_bf = IntroProblem.findMaximumProfit_BruteForce(prices);
			int[] ans_dc = IntroProblem.findMaximumProfit_DivideAndConquer(prices);
			int[] ans_ln = IntroProblem.findMaximumProfit_Linear(prices);
			
			boolean dc_correct = Arrays.equals(ans_bf, ans_dc);
			boolean ln_correct = Arrays.equals(ans_bf, ans_ln);
			
			double bf_val = prices[ans_bf[1]] - prices [ans_bf[0]];
			double dc_val = prices[ans_dc[1]] - prices [ans_dc[0]];
			double ln_val = prices[ans_ln[1]] - prices [ans_ln[0]];
			
			if ( !dc_correct || !ln_correct ) {
				System.out.println("Random Test Failed");
				System.out.println("  Array Size: " + array_size);
				System.out.println("  Brute Force:   " + ans_bf[0] + ", " + ans_bf[1] + "  Value: " + bf_val);
				if (!dc_correct){System.out.println("  DivideConquer: " + ans_dc[0] + ", " + ans_dc[1] + "  Value: " + dc_val);}
				if (!ln_correct){System.out.println("  Linear:        " + ans_ln[0] + ", " + ans_ln[1] + "  Value: " + ln_val);}
			}
		}
		
		
		System.out.println("Tests Complete");
	}
	
	public static void main(String[] args) {
		test();
	}

}
