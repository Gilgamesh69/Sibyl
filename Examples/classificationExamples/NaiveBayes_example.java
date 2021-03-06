package classificationExamples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bayes.NaiveBayes;
import bayes.NaiveBayes2;
import dataframe.Column;
import dataframe.DataFrame;
import dataframe.Util;
import discreteize.EqualFrequencyBinning;
import particles.Particle;
import regressionFunctions.LinearRegression;
import regressionFunctions.LogRegression;
import scorer.CrossValidation;
import transform.Standardize;

public class NaiveBayes_example {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String file = "testfiles/iris.txt";
        DataFrame df = DataFrame.read_csv(file);
        df.convertNANS_mode();
        df.setColumnType(4, 'T');
        //df.getColumn_byName("species").setType('T'); //set target column
        System.out.println("Number of targets: " + df.numTargets);
        
        System.out.println("Binning columns: ");
        EqualFrequencyBinning a = new EqualFrequencyBinning(8, df.getColumn(0));
        df.replaceColumn(0, a.binColumn());
        EqualFrequencyBinning b = new EqualFrequencyBinning(8, df.getColumn(1));
        df.replaceColumn(1, b.binColumn());
        EqualFrequencyBinning c = new EqualFrequencyBinning(8, df.getColumn(2));
        df.replaceColumn(2, c.binColumn());
        EqualFrequencyBinning d = new EqualFrequencyBinning(8, df.getColumn(3));
        //df.getColumn(3).printCol();
        System.out.println("\nPost binning: ");
        System.out.println("Size of column arraylist: "+ df.columns.size() + " - Recorded Num of columns: " + df.getNumColumns());
        df.replaceColumn(3, d.binColumn());
        System.out.println("Size of column arraylist: "+ df.columns.size() + " - Recorded Num of columns: " + df.getNumColumns());
        df.setColumnType(4, 'T');
        ArrayList<DataFrame[]> classes = setClasses(df);
        for(int i = 0; i< classes.size();i++) {
        	System.out.println("TARGET ITERATION: "+i);
        	System.out.println("SUB-DATAFRAMES: "+classes.get(i).length);
        	for(int j = 0; j < classes.get(i).length; j++) {
        		System.out.println(classes.get(i)[j].getName());
        		System.out.println(classes);
        	}
        }
        //System.out.println(df.getColumn_byName("species").getUniqueValues());
        //print column means
        
        //df.convertNANS_mean(); // conevert any NAN's to the mean of column 
        //df = Standardize.standardize_df(df); //Standardize the DF into z scores
        //df = df.shuffle(df);
        NaiveBayes2 nb = new NaiveBayes2(df);
        //nb.setTrain(df);
        //nb.initiallize();
        //nb.printProbTable();

        //
        CrossValidation cv = new CrossValidation(df, 6, nb);
        //cv.printScores();
        cv.avgScores();
        cv.printOverAllScore();
        cv.sumConfusionMatrix();
        cv.printOverAllMatrix();
        nb.setTrain(df);
        //nb.printProbTable();
        //cv.printMatrixs();
        //cv.printMatrixs();
        //cv.printScores();
        //nb.printProbTable();
        //nb.probabilityDF(df1.get(0));
        //nb.predictDF(df);
        //nb.probabilityDF(df1.get(1));
        
	}
	private static ArrayList<DataFrame[]> setClasses(DataFrame df) {
		ArrayList<DataFrame[]> classes = new ArrayList<DataFrame[]>();
		for(Column i : df.target_columns) {
			classes.add(Util.splitOnTarget(df, i));
		}
		return classes;
	}
}
