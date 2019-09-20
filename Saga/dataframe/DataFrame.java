package dataframe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import particles.DoubleParticle;
import particles.IntegerParticle;
import particles.NANParticle;
import particles.Particle;
/**
 * DataFrame
 * the main object for data manipulation, most functions and all models will contructed with his object as input
 * Its structure is a array list of column objects which themselves are generic array list representing columns in a table
 * 
 * @author logan.collier
 * @author Cade Reynoldson
 */
public class DataFrame {
	
    /** The names of the columns */
	private ArrayList<String> columnNames;
	
	/** The type of each column */
	private ArrayList<Character> columnTypes;
	
	/** The ArrayList of columns */
	private ArrayList<Column> columns;
	
	/** The ArrayList of rows */
	private ArrayList<Row> rows;
	
	/** The number of rows in the data frame */
	private int numRows;
	
	/** The number of columns in the data frame */
	private int numColumns;
	
	/**
	 * Create a new, empty data frame.
	 */
	public DataFrame() {
		this.columns = new ArrayList<Column>();
		this.rows = new ArrayList<Row>();
		this.columnNames = new ArrayList<String>();
		this.columnTypes = new ArrayList<Character>();
		numRows = 0;
		numColumns = 0;
		
	}
	
	/**
	 * Construct a dataframe directly from a csv file, auto assumes there is a header line which it uses as the column names.
	 * @param file  - csv file name or path
	 * @param types - an array of strings to set the type attribute of the column, needs work and automization but will be usefully
	 * for managing which functions to use on which type of column
	 * 
	 */
	public void loadcsv(String file) {
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        	line =  br.readLine(); //get column names
        	String[] colNames = line.split(cvsSplitBy);
        	for(int i = 0;i < colNames.length;i++) //Initialize column names.
        		columnNames.add(colNames[i]);
            for (int i = 0; i < columnNames.size(); i++) { // initializing column objects
            	Column c = new Column(columnNames.get(i));
            	columns.add(c);
            }
        	while ((line = br.readLine()) != null) { //Read in each line, create row objects and initialize data.
                String[] lines = line.split(cvsSplitBy);
                Row row = new Row();
                for(int i=0;i<columnNames.size();i++) { //load data into columns and rows
                	Particle p = Particle.resolveType(lines[i]);
                	columns.get(i).type = p.getType();
                	columns.get(i).addToColumn(p);
                	row.addToRow(p);
                }
                rows.add(row);
        	}
        	for(int i = 0;i < columnNames.size();i++) {
        	    getColumn_byIndex(i).resolveType();
                columnTypes.add(columns.get(i).type);
        	}
        	this.numRows = rows.size();
        	this.numColumns = columns.size();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	/**
	 * Converts all NANS in the data frame to their mean value, excluding non numeric values, which will
	 * be converted to the most occouring value in the data frame.
	 */
	public void convertNANS_mean() {
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Particle p = getColumn_byIndex(i).getParticle_atIndex(j);
                if (p instanceof NANParticle) {
                    if (getColumn_byIndex(i).type == 'i')
                        p = Particle.resolveType((int) Math.round(getColumn_byIndex(i).mean()));
                    else if (getColumn_byIndex(i).type == 'd')
                        p = Particle.resolveType(getColumn_byIndex(i).mean());
                    else
                        p = Particle.resolveType(getColumn_byIndex(i).mode());
                    getColumn_byIndex(i).changeValue(j, p);
                    getRow_byIndex(j).changeValue(i, p);
                }
            }
        }
	}
	
	/**
	 * Converts all NANS in the data frame to their mode value.
	 */
	public void convertNANS_mode() {
	    for (int i = 0; i < numColumns; i++) {
	        for (int j = 0; j < numRows; j++) {
	            Particle p = getColumn_byIndex(i).getParticle_atIndex(j);
	            if (p instanceof NANParticle) {
	                p = Particle.resolveType(getColumn_byIndex(i).mode());
	                getColumn_byIndex(i).changeValue(j, p);
	                getRow_byIndex(j).changeValue(i, p);
	            }
	        }
	    }
	}
	
	/**
	 * Creates a new deep copied data frame internally from a list of column names.
	 * @param columnNames the list of column name.
	 * @return the newly created data frame.
	 */
	public DataFrame dataFrameFromColumns_DeepCopy(List<String> columnNames) {
	    DataFrame newDataFrame = new DataFrame();
	    for (String name : columnNames) { // Create the columns
	        Column c = new Column(getColumn_byName(name));
	        newDataFrame.columnNames.add(c.name);
	        newDataFrame.columnTypes.add(c.type);
	        newDataFrame.numColumns++;
	        newDataFrame.columns.add(new Column(getColumn_byName(name)));
	    }
	    newDataFrame.numRows = newDataFrame.columns.get(0).getLength();
	    for (int i = 0; i < newDataFrame.numRows; i++) { // Initialize row pointers
	        Row row = new Row(); 
	        for (int j = 0; j < newDataFrame.numColumns; j++) {
	            row.addToRow(newDataFrame.columns.get(j).getParticle_atIndex(i));
	        }
	        newDataFrame.rows.add(row);
	    }
	    return newDataFrame;
	}
	
	/**
	 * Creates a new shallow copied data frame internally from a list of column names.
	 * @param columnNames the names of the columns to be added to the new data frame
	 * @return a new DataFrame consisting of the columns passed to the method.
	 */
	public DataFrame dataFrameFromColumns_ShallowCopy(List<String> columnNames) {
	    DataFrame newDataFrame = new DataFrame();
	    for (String name : columnNames) {
	        Column c = getColumn_byName(name);
            newDataFrame.columnNames.add(c.name);
            newDataFrame.columnTypes.add(c.type);
            newDataFrame.numColumns++;
	        newDataFrame.columns.add(c);
	    }
	    newDataFrame.numRows = newDataFrame.columns.get(0).getLength();
	    for (int i = 0; i < newDataFrame.numRows; i++) {
	        Row row = new Row();
	        for (int j = 0; j < newDataFrame.numColumns; j++)
	            row.addToRow(newDataFrame.columns.get(j).getParticle_atIndex(i));
	        newDataFrame.rows.add(row);
	    }
	    return newDataFrame;
	}
	
	   /**
     * Creates a new shallow copied data frame internally from a list of column names.
     * @param columnNames the names of the columns to be added to the new data frame
     * @return a new DataFrame consisting of the columns passed to the method.
     */
    public DataFrame dataFrameFromColumns_ShallowCopy(TreeSet<Character> columnTypes) {
        DataFrame newDataFrame = new DataFrame();
        for (Column c : columns) {
            if (columnTypes.contains(c.type)) {
                newDataFrame.columnNames.add(c.name);
                newDataFrame.columnTypes.add(c.type);
                newDataFrame.numColumns++;
                newDataFrame.columns.add(c);
                if (newDataFrame.numRows == 0) 
                    newDataFrame.numRows = c.getLength();
                
            }
        }
        for (int i = 0; i < newDataFrame.numRows; i++) {
            Row row = new Row();
            for (int j = 0; j < newDataFrame.numColumns; j++)
                row.addToRow(newDataFrame.columns.get(j).getParticle_atIndex(i));
            newDataFrame.rows.add(row);
        }
        return newDataFrame;
    }
	
	/**
	 * Creates a new deep copied data frame internally from a list of row indexes.
	 * @param rowIndexes the set of row indexes to create a new data frame with.
	 * @return a new DataFrame consisting of the rows passed to the method.
	 */
	public DataFrame dataFrameFromRows_DeepCopy(Set<Integer> rowIndexes) {
	    DataFrame newDataFrame = new DataFrame();
	    for (Column c : columns) { //Initialize blank columns in new data frame.
	        newDataFrame.addBlankColumn(c.name, c.type);
	    }
	    for (Integer rowIndex : rowIndexes) {
	        Row r = new Row(rows.get(rowIndex));
	        newDataFrame.rows.add(r);
	        for (int i = 0; i < r.getlength(); i++)
	            newDataFrame.columns.get(i).addToColumn(r.getParticle(i));
	    }
	    newDataFrame.numRows = rowIndexes.size();
	    return newDataFrame;
	}
	
	/**
	 * Creates a new shallow copied data frame internally from a list of row indexes.
	 * @param rowIndexes the set for row indexes to create a new data frame with.
	 * @return a new DataFrame consisting of the rows passed to the method.
	 */
	public DataFrame dataFrameFromRows_ShallowCopy(Set<Integer> rowIndexes) {
	    DataFrame newDataFrame = new DataFrame();
	    for (Column c : columns) 
	        newDataFrame.addBlankColumn(c.name, c.type);
	    for (Integer rowIndex : rowIndexes) {
	        Row r = rows.get(rowIndex);
	        newDataFrame.rows.add(r);
	        for (int i = 0; i < r.getlength(); i++) {
	            newDataFrame.columns.get(i).addToColumn(r.getParticle(i));
	        }
	    }
	    newDataFrame.numRows = rowIndexes.size();
	    return newDataFrame;
	    
	}
	
	public ArrayList<Column> getColumns() {
	    return columns;
	}
	
	public ArrayList<Row> getRows() {
	    return rows;
	}
    
    /**
     * Updates the number of rows in this column. Note: this does not create new rows when changing the size.
     */
    public void updateNumRows() {
        if (numRows == rows.size())
            return;
        else if (numRows < rows.size())
            numRows = rows.size();
        else if (numRows == 0 && numColumns != 0)
            numRows = getColumn_byIndex(0).getLength();
    }
    
	/**
	 * Returns an indexed row from the data frame.
	 * @param index the index of the row.
	 * @return A row at a specified index.
	 */
	public Row getRow_byIndex(int index) {
	    return rows.get(index);
	}
	
	
	/**
	 * getColumn returns a single column by name from the dataframe.
	 * @param name The name of the desired column.
	 * @return
	 */
	public Column getColumn_byName(String name){
		int index = 0;
		for(int i = 0; i < columnNames.size(); i++) {
			if(columnNames.get(i).contentEquals(name)){
				index = i;
				break;
			}
		}
		return columns.get(index);
	}
	
   /**
     * Returns the column at a specified index.
     * @param index the index of the column.
     * @return the column at the index.
     */
    public Column getColumn_byIndex(int index) {
        return columns.get(index);
    }
    
    
    /**
     * Add a new column from an array.
     * @param name The name of the column.
     * @param arr The array to be added into a column.
     */
    public void addColumnFromArray(String name, Object arr[]) {
    	Particle p = Particle.resolveType(arr[0]);
    	Column c = new Column(name, p.type);
    	c.addToColumn(p);
    	rows.get(0).addToRow(p);
    	for(int i = 1; i < arr.length;i++) {
    		p = Particle.resolveType(arr[i]);
            rows.get(i).addToRow(p);
    		c.addToColumn(p);
    	}
    	columnNames.add(name);
    	columnTypes.add(c.type);
    	columns.add(c);
    	numColumns++;
    }
    
    public void addColumnName(String name) {
        if (columnNames.size() == columns.size() - 1)
            columnNames.add(name);
        else
            throw new IllegalArgumentException("Addition of new column name would cause uneven column names length vs column length.");
    }
    
    public void addColumnType(char type) {
        if (columnTypes.size() == columns.size() - 1)
            columnTypes.add(type);
        else
            throw new IllegalArgumentException("Addition of new column name would cause uneven column types length vs column length.");
    }
    
    /**
     * Adds a row to the data frame from an array. Mostly used by the distance matrix method.
     * @param arr the new row to be added. 
     */
    public void addRowFromArray(Object arr[]) { //THIS NEEDS WORK! 
        Particle p = Particle.resolveType(arr[0]);
        Row r = new Row();
        r.addToRow(p);
        columns.get(0).addToColumn(p);
        for (int i = 1; i < arr.length; i++) {
            p = Particle.resolveType(arr[i]);
            columns.get(i).addToColumn(p);
            r.addToRow(p);
        }
        rows.add(r);
        numRows++;
        
    }
    
    /**
     * Adds a row to the data frame. Assumes columns for the row to initialize have already been created.
     * @param r the row to be added.
     */
    public void addRow(Row r) {
        rows.add(r);
        numRows++;
        for (int i = 0; i < r.getlength(); i++) 
            columns.get(i).addToColumn(r.getParticle(i));
    }
    
    /**
     * Adds a column to the data frame.
     * @param c the column to be added.
     */
    public void addColumn(Column c) {
        columns.add(c);
        columnNames.add(c.name);
        columnTypes.add(c.type);
        numColumns++;
        for (int i = 0; i < c.getLength(); i++) {
            try {
                rows.get(i).addToRow(c.getParticle_atIndex(i));
            } catch (Exception e) {
                rows.add(new Row());
                numRows++;
                rows.get(i).addToRow(c.getParticle_atIndex(i));
            }
        }
    }

    /**
	 * Adds a new empty column to the data frame.
	 * @param name The name of the new column.
	 */
	public void addBlankColumn(String name) {
		Column c = new Column(name);
		columnNames.add(name);
		columnTypes.add('n');
		columns.add(c);
		numColumns++;
	}
	
	/**
	 * Adds a new empty column of a certain data type to the data frame.
	 * @param name the name of the new column.
	 * @param dataType the data type of the new column.
	 */
	public void addBlankColumn(String name, char dataType) {
	    Column c = new Column(name, dataType);
	    columnNames.add(name);
	    columnTypes.add(dataType);
	    columns.add(c);
	    numColumns++;
	}
	
	/**
	 * Replaces a particle at a given row index (position in row) and column index (position in column).
	 * @param rowIndex the position of the particle in the row being replaced.
	 * @param columnIndex the position of the particle in the column being replaced.
	 * @param p the new particle to be inserted.
	 */
	public void replace(int rowIndex, int columnIndex, Particle p) {
	    if ((rowIndex >= numColumns) || (rowIndex < 0) || ((columnIndex >= numRows) || (columnIndex < 0)))
	        throw new IllegalArgumentException("Invalid indexes to be replaced - \nRow Index: " + rowIndex + 
	                        "\nLength of rows: " + numColumns + "\nColumn Index: " + columnIndex + "Length of columns: " + numRows);
	    else if ((p instanceof DoubleParticle || p instanceof IntegerParticle)
	            && (columns.get(columnIndex).type == 'i') || columns.get(columnIndex).type == 'd') {
	        rows.get(columnIndex).changeValue(rowIndex, p);
	        columns.get(rowIndex).changeValue(columnIndex, p);
	    } else if (columns.get(columnIndex).type == p.type) {
	        throw new IllegalArgumentException("Particle to be replaced does not match the column's type.\nColumn type: " 
	                            + columns.get(rowIndex).type + "\nParticle Type: " + p.type);
	    } 
	}
	
	/**
	 * Returns an array of the column names from the data frame.
	 * @return an array of the column names from the data frame.
	 */
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}
	
	/**
	 * Returns the amount of rows in the data frame.
	 * @return the amount of rows in the data frame.
	 */
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
	    return numColumns;
	}
	
	/**
	 * Returns the names of the columns in the data frame in a single string.
	 * @return the names of the columns in the data frame in a single string.
	 */
	public String columnNamesToString() {
		return columnNames.toString();
	}
	
	/**
	 * Set column to certain type given the column's name.
	 * @param columnName the name of the column.
	 * @param newType the new data type of the column.
	 */
	public void setColumnType(String columnName, char newType) {
		int index = 0;
		for(int i = 0; i < columnNames.size(); i++) {
			if(columnNames.get(i).contentEquals(columnName)){
				index = i;
				break;
			}
		}
		getColumn_byIndex(index).setType(newType);
		columnTypes.set(index, newType);
	}
	
	/**
	 * Set a column at a specified index's data type. 
	 * @param columnIndex the index of the column.
	 * @param newType the new data type of the column.
	 */
	public void setColumnType(int columnIndex, char newType) {
        getColumn_byIndex(columnIndex).setType(newType);
        columnTypes.set(columnIndex, newType);
    }
	
	/**
	 * Loops through the entire data frame to check if the data frame is square (could be optimized).
	 * @return true if the data frame is "square" (n x n), false otherwise.
	 */
	public boolean isSquare() {
	    Set<Integer> rowLengths = new TreeSet<Integer>();
	    Set<Integer> columnLengths = new TreeSet<Integer>();
	    for (int i = 0; i < numRows; i++)
	        rowLengths.add(rows.get(i).rowLength);
	    for (int i = 0; i < numColumns; i++)
	        columnLengths.add(columns.get(i).columnLength);
	    return (rowLengths.equals(columnLengths)) && numRows == numColumns;
	}
	/**
	 * Method to get a dataframe with only specified types
	 * @param List<String> types
	 * @return shallow copy
	 */
	public DataFrame include(List<Character> types) {
		List<String> cols = new ArrayList<String>();
		for(Column i : columns) {
			if(types.contains(i.type)) {
				cols.add(i.name);
			}
		}
		return dataFrameFromColumns_ShallowCopy(cols);
	}
	
	/**
	 * Method to get a data frame with all columns excluding the specified column indexes in the parameter's list.
	 * Uses a set (prefferably treeset) for nlog(n) element access time (contains()).
	 * @param columnIndex the list of column indexes to exclude.
	 * @return a new (shallow copied) data frame with the specified columns excluded.
	 */
	public DataFrame exclude(Set<Integer> columnIndexes) {
	    List<String> columnNames = new ArrayList<String>();
	    for (int i = 0; i < numColumns; i++) {
	        if (!columnIndexes.contains(i))
	            columnNames.add(columns.get(i).name);
	    }
	    return dataFrameFromColumns_ShallowCopy(columnNames);
	}
	
   /**
     * Method to get a data frame with all columns excluding the specified column index.
     * @param columnIndex the list of column indexe to exclude.
     * @return a new (shallow copied) data frame with the specified column excluded.
     */
    public DataFrame exclude(int columnIndex) {
        List<String> columnNames = new ArrayList<String>();
        for (int i = 0; i < numColumns; i++) {
            if (i != columnIndex)
                columnNames.add(columns.get(i).name);
        }
        return dataFrameFromColumns_ShallowCopy(columnNames);
    }
    
	/**
	 * returns a list of columns of the specified type
	 * @param String type
	 * @return List<Column>
	 */
	public List<Column> getColumnByTypes(char type){
		List<Column> cols = new ArrayList<Column>();
		for(Column i : columns) {
			if(i.type == type) {
				cols.add(i);
			}
		}
		return cols;
	}
	
    /**
     * Prints the data frame.
     */
    public void printDataFrame() {
        for(int i = 0; i < columnNames.size(); i++) {
            System.out.print(columnNames.get(i) + " ");
        }
        System.out.println();
        for(int z = 0 ; z < numRows; z++) {
            rows.get(z).printRow();
            System.out.println();
        }
    }
    
}