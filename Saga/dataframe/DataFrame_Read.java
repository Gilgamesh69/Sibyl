package dataframe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import particles.Particle;

public class DataFrame_Read {

    /**
     * Loads a csv formatted file.
     * @param filePath the filepath of the file to read in.
     * @return a fully initialized dataframe. 
     */
	public static DataFrame loadcsv(String filePath) {
	    DataFrame df = new DataFrame();
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(filePath));
	        String[] columnNames = br.readLine().split(",");
	        for (int i = 0; i < columnNames.length; i++) { //Initialize blank columns with column names.
	            df.addBlankColumn(columnNames[i]);
	        }
	        String currentLine = "";
	        int count = 0;
	        while ((currentLine = br.readLine()) != null) { //For each other line inside the file, add them to the dataframe.
	            Row currentRow = new Row();
	            String[] values = currentLine.split(",");
	            for (int i = 0; i < values.length; i++) { //Create a row object out of the current read in line.
	                Particle currentParticle = Particle.resolveType(values[i]);
	                currentParticle.setIndex(count); //Set the index of this particle in the column. 
	                currentRow.add(currentParticle);
	            }
	            df.addRow(currentRow); //Add the current row to the dataframe. This method handles initializing position in columns too.
	            count++;
	        }
            df.numRows = df.rows.size();
            df.numColumns = df.columns.size();
	        for (int i = 0; i < df.numColumns; i++) { //For each column in the row, initialize it's type and it's statistics. 
	            Column c = df.getColumn(i);
	            c.resolveType();
	            df.columnTypes.add(c.getType());
	            if (c.getType() == 'N')
	                df.numericIndexes.add(i);
	            c.setStatistics();
	        }
	        br.close();
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return df;
	}
}
