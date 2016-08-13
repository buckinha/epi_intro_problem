package hkb.practice.epi;

public abstract class IntroProblem {

	public IntroProblem() {
		// TODO Auto-generated constructor stub
	}
	
	/**Find the profit-maximizing buy and sell dates for a time series of daily stock prices
	 * The problem specifies to use the opening days price for both buying and selling, so the other
	 * two arrays (high and low prices) are ignored.
	 * 
	 * Assumptions: 
	 * 1) input arrays are the same length
	 * 2) input arrays have length >= 2
	 * 
	 * 
	 * @param open_prices: double[] The opening prices on each day
	 * @param low_prices: double[] The low prices on each day
	 * @param high_prices: double[] The high prices on each day
	 * @param mode: int  Values are 0 for brute force, 1 for divide-and-conquer, else for linear-time
	 * @return a two element int[], containing the index of the buy and sell day.
	 */
	public static int[] findMaximumProfit(double[] open_prices, double[] low_prices, double[] high_prices, int mode){
		if      (mode == 0){ return findMaximumProfit_BruteForce(open_prices); }
		else if (mode == 1){ return findMaximumProfit_DivideAndConquer(open_prices); }
		else               { return findMaximumProfit_Linear(open_prices); }
	}
	
	/**Uses the brute force method to find the profit-maximizing buy and sell dates.
	 * 
	 * @param open_prices: The opening prices of each day, when the problem specifies to buy and sell
	 * @return a two element int[], containing the index of the buy and sell day.
	 */
	public static int[] findMaximumProfit_BruteForce(double[] open_prices){
		int[] buy_sell_indices = {0,1};
		
		double max_profit = Double.NEGATIVE_INFINITY;
		
		//loop over all pairs of buy and sell indices to find the max difference
		for (int i = 0; i < open_prices.length - 1; i++){
			for (int j = i+1; j < open_prices.length; j++){
				if (open_prices[j] - open_prices[i] > max_profit){
					max_profit = open_prices[j] - open_prices[i];
					buy_sell_indices[0] = i;
					buy_sell_indices[1] = j;
				}
			}
		}
		return buy_sell_indices;
	}
	
	/**Uses a divide-and-conquer method to find the profit-maximizing buy and sell dates.
	 * 
	 * @param open_prices: The opening prices of each day, when the problem specifies to buy and sell
	 * @return a two element int[], containing the index of the buy and sell day.
	 */
	public static int[] findMaximumProfit_DivideAndConquer(double[] open_prices){
		int[] buy_sell_indices = {0,1};
		
		int[] d_and_c_indices = DivideAndConquer(open_prices, 0, open_prices.length - 1);
		buy_sell_indices[0] = d_and_c_indices[0];
		buy_sell_indices[1] = d_and_c_indices[1];
		
		return buy_sell_indices;
		
	}
	/** 
	 * 
	 * @param open_prices
	 * @param start
	 * @param end
	 * @return int[4] = {index of buy, index of sell, index of min, index of max}
	 */
	private static int[] DivideAndConquer(double[] open_prices, int start, int end){
		int[] indices = new int[4];
		//divide
		if (end - start > 2){
			int[] first_part =  DivideAndConquer(open_prices,                start,  (start + end)/2  );
			int[] second_part = DivideAndConquer(open_prices,  (start + end)/2 + 1,              end  );
			
			double first_val = open_prices[first_part[1]] - open_prices[first_part[0]];
			double second_val = open_prices[second_part[1]] - open_prices[second_part[0]];
			double combined_val = open_prices[second_part[3]] - open_prices[first_part[2]];
			
			if (( combined_val > first_val ) && (combined_val > second_val)) {
				indices[0] = first_part[2];
				indices[1] = second_part[3];
			} else if (second_val > first_val){
				indices[0] = second_part[0];
				indices[1] = second_part[1];
			} else {
				indices[0] = first_part[0];
				indices[1] = first_part[1];
			}
			
			//min
			if ( open_prices[first_part[2]] < open_prices[second_part[2]]) {
				indices[2] = first_part[2];
			}else{
				indices[2] = second_part[2];
			}
			//max
			if ( open_prices[first_part[3]] > open_prices[second_part[3]]) {
				indices[3] = first_part[3];
			}else{
				indices[3] = second_part[3];
			}
			
			return indices;
			
		} else {
			if (start == end-1){
				indices[0] = start;
				indices[1] = end;
				//min and max
				if (open_prices[start] < open_prices[end]){
					indices[2] = start;
					indices[3] = end;
				} else {
					indices[2] = end;
					indices[3] = start;
				}
				
			} else {
				if (open_prices[start] < open_prices[start+1]){
					indices[0] = start;
				} else {
					indices[0] = start + 1;
				}
				if (open_prices[end] > open_prices[end-1]){
					indices[1] = end;
				} else {
					indices[1] = end - 1;
				}
				//min
				double min = Double.POSITIVE_INFINITY;
				double max = Double.NEGATIVE_INFINITY;
				for (int i = start; i<=end; i++){
					if (open_prices[i] < min){
						indices[2] = i;
					}
					if (open_prices[i] > max){
						indices[3] = i;
					}
				}
			}
			
			return indices;
		}
	}
	
	/**Uses a linear time method to find the profit-maximizing buy and sell dates.
	 * 
	 * @param open_prices: The opening prices of each day, when the problem specifies to buy and sell
	 * @return a two element int[], containing the index of the buy and sell day.
	 */
	public static int[] findMaximumProfit_Linear(double[] open_prices){
		int[] buy_sell_indices = {0,1};
		
		int index_of_lowest = 0;
		double max_profit = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i < open_prices.length; i++){
			if (open_prices[i] < open_prices[index_of_lowest]){
				index_of_lowest = i;
			} else {
				if ( open_prices[i] - open_prices[index_of_lowest] > max_profit ){
					max_profit = open_prices[i] - open_prices[index_of_lowest];
					buy_sell_indices[0] = index_of_lowest;
					buy_sell_indices[1] = i;
				}
			}
		}
		
		return buy_sell_indices;
		
	}
}
