package regressionFunctions;

import particles.Particle;

/**
 * confidence intervals for regressions
 * 
 * for E(y | x*) , the avg expected value of y for a given x* is
 * 
 * ~y = +- t*_n-2 * s_y * sqrt( 1/n + ( (x*-x_mean)^2 / (n-1) * s_x^2) )
 * 
 * s_y = std deviation of residuals = sqrt( sum[ (y_i - predicted_y)^2 ] / n-2)
 * 
 * 
 * https://www2.isye.gatech.edu/~yxie77/isye2028/lecture12.pdf
 * @author logan.collier
 *
 */

public class ConfidenceIntervals {
	
	private Regression function;
	private float alpha;
	private int confidence_level;
	private float critical_prob;
	private float SE_y; 
	private float[] SE_x; //must calculate standard error for all coefficients
	private float[] T; 
	public ConfidenceIntervals(Regression f) {
		this.function = f;
	}
	private void setAlpha() {
		this.alpha = 1-(this.confidence_level / 100);
	}
	private void setCritical_prob() {
		this.critical_prob = 1 - (this.alpha/2);
	}
	private void setSE_y() {
		this.SE_y = (float) Math.sqrt((1/this.function.y.getLength()) + (this.function.SSE/(this.function.y.getLength()-2)));

	}
	private void set_SE_coefficents() {
		m = 
	}
	private void upper_intervalY(double predicted_y, double x_val) {
		return predicted_y + 
	}
	private void set_Tvalue() {
		for(int i = 0; i < this.function.function.length; i++) {
			
		}
	}

	

}
