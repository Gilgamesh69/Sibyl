

import dataframe.Column;

/**
 * 
 * @author logan.collier
 *
 */
public class Multi_LinearRegression {
	
	private Column[] x;
	private Column y;
	public LinearRegression[] regressions;
	public double[] slopes;
	public double intercept;
	
	public Multi_LinearRegression(Column[] x, Column y) {
		this.x = x;
		this.y = y;
		setRegressions();
	}
	/**
	 * regression lines for columns
	 * @param target
	 * @return
	 */
	private void setRegressions() {
		regressions = new LinearRegression[x.length];
		slopes = new double[x.length];
		int cnt = 0;
		for(Column i : x) {
			regressions[cnt] = new LinearRegression(y,i);
			intercept = intercept + regressions[cnt].intercept;
			cnt++;
		}
	}
	private void setSlopes() {
		int cnt = 0;
		for(LinearRegression i : regressions) {
			slopes[cnt] = 
			cnt++;
		}
	}
}
