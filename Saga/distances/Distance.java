package distances;

import dataframe.DataFrame;
import dataframe.Row;
import particles.DistanceParticle;

public abstract class Distance{

    public String distanceType;
    
    public Distance(String theDistanceType) {
        distanceType = theDistanceType;
    }
    
	// is there an easy way to make this generic and work easily with saga?
	// such as produce the same results if plugged in either a dataframe or a 2D int[]
	public abstract double distance(Row r1, Row r2);
	
	/**
	 * Creates a distance matrix given a data frame, and distance formula to run off of.
	 * @param theDataFrame
	 * @param distanceType
	 * @return
	 */
	public static DataFrame distance_matrix(DataFrame theDataFrame, Distance distanceFunction) {
	    DataFrame dataFrame = new DataFrame();
	    for (int i = 0; i < theDataFrame.getNumRows(); i++) //Initializes the columns of the new data frame. 
	        dataFrame.addBlankColumn("R" + i);
	    for (int i = 0; i < theDataFrame.getNumRows(); i++) {
	        Row currentRow = theDataFrame.getRow_byIndex(i);
	        Row newRow = new Row();
	        for (int j = 0; j < theDataFrame.getNumRows(); j++) {
	            double distance = distanceFunction.distance(currentRow, theDataFrame.getRow_byIndex(j));
	            newRow.add(new DistanceParticle(distance, j, distanceFunction.distanceType));
	        }
	        dataFrame.addRow(newRow);
	    }
	    return dataFrame;
	}
	
}
