package scorer;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import dataframe.Column;
import dataframe.DataFrame;
public class ConfusionMatrix {
	public HashMap<String, HashMap<Object, Integer>> truePositive;
	public HashMap<String, HashMap<Object, Integer>> falsePositive;
	public HashMap<String, HashMap<Object, Integer>> trueNegative;
	public HashMap<String, HashMap<Object, Integer>> falseNegative;
	
	
	private DataFrame df; //dataframe of correct answers
	private HashMap<String, ArrayList<Object>> predictions;
	
	public ConfusionMatrix(DataFrame df, HashMap<String, ArrayList<Object>> predictions) {
		this.df = df;
		System.out.println("CM: "+df.getNumColumns());
		this.predictions = predictions;
		truePositive = new HashMap<String, HashMap<Object, Integer>>();
		falsePositive = new HashMap<String, HashMap<Object, Integer>>();
		trueNegative = new HashMap<String, HashMap<Object, Integer>>();
		falseNegative = new HashMap<String, HashMap<Object, Integer>>();
		setTable();
		test_score();
		
	}
	/**
	 * set up confusion matrix arrays
	 * structure:
	 * truePositive[0] = 1st target column --> hashset { class1 : count , class2 : count ...}
	 * truePositive[1] = (optional)2nd targetColumn  --> hashset { class1 : count , class2 : count ...}
	 * truePositive[N] = Nth target column
	 */
	private void setTable() {
		int cnt = 0;
		for(int i = 0; i < df.getNumColumns();i++) {
			HashMap<Object,Integer> arr0 = new HashMap<Object,Integer>();
			HashMap<Object,Integer> arr1 = new HashMap<Object,Integer>();
			HashMap<Object,Integer> arr2 = new HashMap<Object,Integer>();
			HashMap<Object,Integer> arr3 = new HashMap<Object,Integer>();
			for(Object j : df.getColumn(i).getUniqueValues()) {
				arr0.put(j, 0);
				arr1.put(j, 0);
				arr2.put(j, 0);
				arr3.put(j, 0);
			}
			truePositive.put(df.getColumn(i).getName(),arr0);
			falsePositive.put(df.getColumn(i).getName(),arr1);
			trueNegative.put(df.getColumn(i).getName(),arr2);
			falseNegative.put(df.getColumn(i).getName(),arr3);
			
		}
	}
	/**
	 * finds true positives and negatives
	 */
	private void test_score() {
		int cnt1 = 0;
		int cnt2;
		System.out.println("CM: "+df.getName());
		//for each target column
		for(String i : predictions.keySet()) {
			cnt2 = 0;
			//for each prediction for a column
			for (Object j : predictions.get(i)) {
				//if the predicted value matches the actual
				System.out.println(j+"  "+df.getColumn(cnt1).getName());
				if(j.equals(df.getColumn(cnt1).getParticle(cnt2).getValue())) {
					truePositive.get(df.getColumn(cnt1).getName()).replace(j, truePositive.get(df.getColumn(cnt1).getName()).get(j)+1);
					for(Object x : trueNegative.get(df.getColumn(cnt1).getName()).keySet()) {
						if(x.equals(j)) {
							continue;
						}else {					
							trueNegative.get(df.getColumn(cnt1).getName()).replace(x, trueNegative.get(df.getColumn(cnt1).getName()).get(x)+1);
						}
					}
				}
				//if its prediction isnt right
				else if(!j.equals(df.getColumn(cnt1).getParticle(cnt2).getValue())) {
					//update false positive count
					falsePositive.get(df.getColumn(cnt1).getName()).replace(j, falsePositive.get(df.getColumn(cnt1).getName()).get(j)+1);
					
					for(Object x : falseNegative.get(df.getColumn(cnt1).getName()).keySet()) {
						
						if(x.equals(df.getColumn(cnt1).getParticle(cnt2).getValue())) {
							falseNegative.get(df.getColumn(cnt1).getName()).replace(x, falseNegative.get(df.getColumn(cnt1).getName()).get(x)+1);
							
							continue;
						}
						else if(x.equals(j)){
							
							continue;
						}else {
							
							trueNegative.get(df.getColumn(cnt1).getName()).replace(x, trueNegative.get(df.getColumn(cnt1).getName()).get(x)+1);
						}
					}
				}
				cnt2++;
			}
			cnt1++;
		}
		
		
	}
	public void print_matrix() {
		
		System.out.println("True Positives: "+ truePositive.toString());
		System.out.println("False Positives: " + falsePositive.toString());
		System.out.println("True Negatives: " + trueNegative.toString());
		System.out.println("False Negatives: " + falseNegative.toString());
		
	}

	
	

}
