package regressionFunctions;
import dataframe.Column;
import forensics.Stats;
import particles.Particle;

public abstract class Regression {
	protected Column x;
	protected Column y;

	/**
	 *  Y = mean of observed data ( the target ) = 1/n * E y_i
	 *  y_i = an observed value in target column
	 *  f_i = regression function for a given point like y=2x and x=2 => 2*2 = 4
	 *  residuals = e_i = y_i - f_i
	 */
	public double SST; //total sum of squares	= E(y_i - Y_mean)^2
	public double SSY; //total sum of squares	= E(y_i - Y_mean)^2
	public double SSE; //sum of squared errors	= E(y_i - f_i)^2  ||residual sum of squares
	public double SSR; //sum of squared regression = E(f_i - y_i)^2 ||explained sum of squares
	public double SP;  //sum of products
	
	public double R2;  //r squared
	/**
	 * Standard error is the estimated standard deviation. so the regression line is the mean
	 * and the std is calculated from the distance from points to the line aka SSE
	 * so variance is SUM[ (x-mean)^2) would be SUM[(y_i - y_predicted)^2]
	 * and STD or standard error is SQRT(variance/N)
	 */
	public double SE;
	public double SE_r;//standard error of the regression model = sqrt[ (1/n-1)*SSE ]
	public double SE_m;//standard error of the mean
	public double SE_f;//standard error of the forecast
	public double MAE; //mean absolute error
	public double MSE; //mean squared error
	public double RMSD;//Root mean squared deviation
	public double[] coefficents;
	
	/**
	 * abstract regression class for all types of regression functions
	 * slope. the y intercept is always the last index of the double array
	 * @param x
	 * @param y
	 */
	public Regression(Column x, Column y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * set the regression equation
	 */
	protected abstract void setRegression();
	/**
	 * returns string representation of the regression function
	 * @return
	 */
	public abstract String getEquation();
	/**
	 * prints the string representation of the regression function
	 */
	public void printEquation() {
		System.out.println(getEquation());
	}
	
	/**
	 * predict a y value based on an x_value
	 * @param x_val
	 * @return
	 */
	public abstract double predictY(Particle x_val);
	
	
	protected void setMeasures() {
		setSST();
		set_SSR_SSE();
		setRMSD();
		setMSE();
		set_rSquared();
		setSE();
	}
	/**
	 * set total sum of squares
	 */
	protected void setSST() {
		this.SST = Stats.zeroSquaredSum(x);
	}
	/**
	 * get total sum of squares
	 * @return
	 */
	public double getSST() {
		return SST;
	}
	/**
	 * set sum of squared residuals
	 */
	protected void set_SSR_SSE() {
		double ssr = 0;
		double sse = 0;
		double sr = 0;
		
		for(int i = 0; i < x.getLength(); i++) {
			sr = sr + Math.pow( y.getDoubleValue(i) - y.mean,2);
			ssr = ssr + Math.pow( this.predictY(x.getParticle(i)) - y.getDoubleValue(i),2);
			sse = sse + Math.pow( y.getDoubleValue(i) - this.predictY(x.getParticle(i)),2);
		}
		this.SSY = sr;
		this.SSR = ssr;
		this.SSE = sse;
	}
	/**
	 * set root mean squared deviation
	 */
	protected void setRMSD() {
		this.RMSD = Math.sqrt( (this.SSR / x.getLength()) );
	}
	/**
	 * set mean standard error
	 */
	protected void setMSE() {
		this.MSE = this.SSE / (x.getLength()-2);
	}
	/**
	 * set R^2
	 */
	protected void set_rSquared() {
		this.R2 = 1-(this.SSE / this.SSY);
	}
	private void set_SE_r() {
		this.SE_r = Math.sqrt( (1/(x.getLength()-1) * this.SSE) );
	}
	private void set_SE_m() {
		this.SE_m = this.SE_r / Math.sqrt(x.getLength());
	}
	private void set_SE_f() {
		this.SE_f = Math.sqrt( Math.pow(this.SE_r, 2) + Math.pow(this.SE_m, 2) );
	}
	/**
	 * set standard error of the model
	 * "standard error" means "standard deviation of the error" in whatever is being estimated
	 */
	private void setSE() {
		//this.SE = 1- Math.sqrt( (this.SSE / (x.getLength() - 2)) / Math.sqrt(this.getSST()));
		this.SE = Math.sqrt(this.SSE / (x.getLength() - 2));
	}
	
}
