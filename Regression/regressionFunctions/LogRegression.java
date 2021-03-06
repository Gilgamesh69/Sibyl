package regressionFunctions;


import dataframe.Column;
import particles.Particle;

/**
 * 
 * @author logan.collier
 * Logrithmic regression
 * http://mathworld.wolfram.com/LeastSquaresFittingLogarithmic.html
 *
 */
public class LogRegression extends Regression{
	

	private double sum_ylnx = 0;
	private double sum_lnx = 0;
	private double sum_lnx_squared = 0;
	
	protected double slope;
	protected double intercept;
	
	public LogRegression(Column x, Column y) {
		super(x, y,"Logrithmic");
		setRegression();
		setMeasures();
	}
	/**
	 * return the slop of the best fitted logrithm using least squares fitting , E y_i  - b * E(ln(x_i)) / n
	 * @return
	 */
	public double log_slopeM() {
		return ((x.getLength() * sum_ylnx) - (y.sum * sum_lnx)) / ((x.getLength() * sum_lnx_squared) - Math.pow(sum_lnx, 2));
	}
	public double intercept_b() {
		return (y.sum - (log_slopeM() * sum_lnx)) / x.getLength();
	}
	private void setVars() {
		double x_1 = 0;
		double y_1 = 0;
		for(int i = 0; i < x.getLength(); i++) {
			if(x.getParticle(i).getValue() instanceof Double) {
				x_1 = (double)x.getParticle(i).getValue();
			}
			else if(x.getParticle(i).getValue() instanceof Integer) {
				x_1 = (int)x.getParticle(i).getValue();
			}
			if(y.getParticle(i).getValue() instanceof Double) {
				y_1 = (double)y.getParticle(i).getValue();
			}
			else if(y.getParticle(i).getValue() instanceof Integer) {
				y_1 = (int)y.getParticle(i).getValue();
			}
			sum_ylnx = sum_ylnx + y_1 * Math.log(x_1);
			sum_lnx = sum_lnx + Math.log(x_1);
			sum_lnx_squared = sum_lnx_squared + Math.pow(Math.log(x_1), 2);
		}
	}
	
	public double predictY(Particle x_val) {
		return (slope * Math.log( x_val.getDoubleValue())) + intercept;
	}
	@Override
	protected void setRegression() {
		super.coefficents = new double[2];
		setVars();
		this.slope = log_slopeM();
		this.intercept = intercept_b();
		super.coefficents[0] = this.intercept;
		super.coefficents[1] = this.slope;
		
	}
	@Override
	public String getEquation() {
		return "Y = " + this.slope + "log("+"X"+")"+ " + "+this.intercept;
	}
	@Override
	protected void set_se_ofCoefficents() {
		super.coefficent_se = new double[2];
		double f = super.RMSD / super.x.getLength();
		super.coefficent_se[0] = f  * ( 1+(Math.pow(x.mean, 2)/x.variance) );
		super.coefficent_se[1] = f * (1/x.std);
		
	}
	@Override
	protected void set_T_ofCoeffiecents() {
		super.coefficent_t_scores = new double[2];
		super.coefficent_t_scores[0] = super.coefficents[0] / super.coefficent_se[0];
		super.coefficent_t_scores[1] = super.coefficents[1] / super.coefficent_se[1];
	}
	@Override
	protected void setDegFree() {
		super.degree_freedom = super.x.getLength()-1;
	}

}
