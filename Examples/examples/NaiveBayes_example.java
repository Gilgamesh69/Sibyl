package examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataframe.Column;
import dataframe.DataFrame;
import discreteize.EqualFrequencyBinning;
import machinations.NaiveBayes;
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
        System.out.println(df.numTargets);
        
        
        EqualFrequencyBinning a = new EqualFrequencyBinning(8, df.getColumn(0));
        df.replaceColumn(0, a.binColumn());
        df.setColumnType(0, 'C');
        EqualFrequencyBinning b = new EqualFrequencyBinning(8, df.getColumn(1));
        df.replaceColumn(1, b.binColumn());
        df.setColumnType(1, 'C');
        EqualFrequencyBinning c = new EqualFrequencyBinning(8, df.getColumn(2));
        df.replaceColumn(2, c.binColumn());
        df.setColumnType(2, 'C');
        
        System.out.println("NUM CAT: "+df.numCategorical);
        System.out.println("NUM NUM: "+df.numNumeric);
        System.out.println("NUM COL: "+df.getNumColumns());
        //System.out.println(df.getColumn_byName("species").getUniqueValues());
        //print column means
        
        //df.convertNANS_mean(); // conevert any NAN's to the mean of column 
        //df = Standardize.standardize_df(df); //Standardize the DF into z scores
        df = df.shuffle(df);
        NaiveBayes nb = new NaiveBayes();
        nb.setTrain(df);
        
        nb.printProbTable();

        //nb.initiallize();
        CrossValidation cv = new CrossValidation(df, 3, nb);
        //cv.printScores();
        cv.avgScores();
        cv.printOverAllScore();
        nb.setTrain(df);
        nb.printProbTable();
        //cv.printMatrixs();
        //cv.printMatrixs();
        //cv.printScores();
        //nb.printProbTable();
        //nb.probabilityDF(df1.get(0));
        //nb.predictDF(df);
        //nb.probabilityDF(df1.get(1));
        
	}
}
