package gui;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import dataframe.Column;
import particles.DoubleParticle;
import particles.Particle;
import regressionFunctions.Regression;

/**
 * Contains methods for plotting a scatter plot, and regression functions.
 * @author Cade Reynoldson
 * @version 1.0
 */
public class Plot extends JPanel{
	
    /** Serial bullshit */
    private static final long serialVersionUID = 1031343868335382809L;

    /** The two columns to plot against eachother. */
	private Column x,y;
	
	private XYPlot plot;
	
	/** The chartpanel which displays the scatter plot. */
	private ChartPanel panel;

	/**
	 * Creates a plot given two columns.
	 * @param x the x column.
	 * @param y the y column. 
	 */
	public Plot(Column x, Column y) {
		this.x = x;
		this.y = y;
	    plot = createPlot(null, 20);
        JFreeChart chart = new JFreeChart("ScatterPlot of " + x.getName() + " vs. " + y.getName(),
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	    // Create Panel
        ChartPanel view = new ChartPanel(chart);
        this.panel = view;
	}
	
    /**
     * Plots a set of columns with a regression line.
     * @param x the x column.
     * @param y the y column.
     * @param regressionLine the regression line to plot. 
     */
    public Plot(Column x, Column y, HashSet<Regression> regressions, int functionSamples) {
        this.x = x;
        this.y = y;
        plot = createPlot(regressions, functionSamples);
        JFreeChart chart = new JFreeChart("ScatterPlot of " + x.getName() + " vs. " + y.getName(),
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        // Create Panel
        this.panel = new ChartPanel(chart);
    }
    
    /**
     * Returns the created plot in the form of a chartpanel.
     * @return the created plot in the form of a chartpanel. 
     */
    public ChartPanel getPlot() {
        return panel;
    }
	
    /**
     * Creates a plot of the given data. 
     * @param regressions
     * @param functionSamples
     * @return
     */
	private XYPlot createPlot(HashSet<Regression> regressions, int functionSamples) {
	    XYPlot plot = new XYPlot();
	    plot.setDataset(0, getColumnPlot());
	    plot.setRenderer(new XYLineAndShapeRenderer(false, true));
	    NumberAxis xAxis = new NumberAxis(x.getName());
	    NumberAxis yAxis = new NumberAxis(y.getName());
	    plot.setDomainAxis(0, xAxis);
        plot.setRangeAxis(0, yAxis);
	    int count = 1;
	    if (regressions != null) {
	        for (Regression r : regressions) {
	            plot.setDataset(count, getRegressionPlot(r, x.min - x.std, x.max + x.std, functionSamples));
	            plot.setRenderer(count++, new XYLineAndShapeRenderer(true, false));
	        }
	    }
	    xAxis.setLowerBound(x.min - x.std);
	    yAxis.setLowerBound(y.min - y.std);
	    xAxis.setUpperBound(x.max + x.std);
	    yAxis.setUpperBound(y.max + y.std);
	    return plot;
	}
	
	/**
	 * Creates a plot for a given regression.
	 * @param r the regression.
	 * @param startValue the value to begin plotting at.
	 * @param endValue the value to end plotting at.
	 * @param numSamples the number of samples to take from the plot. Higher sample number, the smoother the line.
	 * @return An XYDataset containing a regression plot.
	 */
	private XYDataset getRegressionPlot(Regression r, double startValue, double endValue, int numSamples) {
	    XYSeries rLine = new XYSeries(r.getEquation(), false);
	    double step = (endValue - startValue) / (numSamples - 1);
	    for (double i = startValue; i < endValue; i += step) {
            double yVal = r.predictY(Particle.resolveType(i));
            rLine.add(i, yVal);
        }
	    XYSeriesCollection data = new XYSeriesCollection();
	    data.addSeries(rLine);
	    return data;
	}
	
	/**
	 * Plots a regression. 
	 * @param r the regression.
	 * @param numSamples the number of smaples to take from the plot. Higher sample number, the smoother the line. 
	 */
	public void plotRegression(Regression r, int numSamples) {
        XYSeries rLine = new XYSeries(r.getEquation(), false);
        double startValue = x.min - x.std;
        double endValue = x.max + x.std;
        double step = (endValue - startValue) / (numSamples - 1);
        for (double i = startValue; i < endValue; i += step) {
            double yVal = r.predictY(Particle.resolveType(i));
            rLine.add(i, yVal);
        }
        XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(rLine);
        plot.setDataset(plot.getSeriesCount(), data);
        JFreeChart chart = new JFreeChart("ScatterPlot of " + this.x.getName() + " vs. " + this.y.getName(),
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        panel = new ChartPanel(chart);
        
	}
	
	/**
	 * Plots a given point on the chart. 
	 * @param x the x value to plot. 
	 * @param r the regression function to use to calculate y.
	 */
	public void plotPoint(double x, Regression r) {
	    double y = r.predictY(new DoubleParticle(x));
	    XYSeries point = new XYSeries("x = " + x + ", y = " + y);
	    point.add(x, y);
	    XYSeriesCollection data = new XYSeriesCollection();
	    data.addSeries(point);
	    plot.setDataset(plot.getSeriesCount(), data);
        JFreeChart chart = new JFreeChart("ScatterPlot of " + this.x.getName() + " vs. " + this.y.getName(),
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        panel = new ChartPanel(chart);
	}
	
	/**
	 * Plots the given columns in the axises. 
	 * @return an XYDataset containing the two columns plotted against eachother. 
	 */
	private XYDataset getColumnPlot() {
	    XYSeriesCollection xvsy = new XYSeriesCollection();
        XYSeries series = new XYSeries(x.getName() + " vs. " + y.getName());
        //Plot the points from the two columns. 
        for(int i = 0; i < x.getLength(); i++) {
            series.add(x.getDoubleValue(i), y.getDoubleValue(i));
        }
        xvsy.addSeries(series);
        return xvsy;
	}
}
