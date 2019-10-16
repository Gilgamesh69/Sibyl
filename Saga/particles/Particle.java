package particles;

/**
 * Particle abstract class, represents various types of data to be stored in the data frame. 
 * 
 * @author Logan Collier
 * @author Cade Reynoldson
 * 
 */
public abstract class Particle implements Comparable<Particle>{
    
    /** The value of the Object */
    public Object value;
    
    /** A string representation of the type of object 
     * TYPE CHARACTERS:
     * 'i' : Integer
     * 'd' : Double
     * 's' : String
     * 'n' : Nan/Null
     * 'o' : Object
     * 'c' : Categorical
     * 'e' : Distance
     * 'g' : Object
     * */
    public char type;
    
    /** The next particle, acting as a linked list of rows. */
    public Particle nextParticle;
    
    /**
     * Creates a new instance of a particle with a given value and type.
     * @param theValue
     * @param theType
     */
    public Particle(Object theValue, char theType) {
        value = theValue;
        type = theType;
        nextParticle = null;
    }
     
    /**
     * @return the type
     */
    public char getType() {
        return type;
    }
    
    /**
     * Links this particle another particle.
     * @param p the particle to link to.
     */
    public void linkTo(Particle p) {
        nextParticle = p;
    }
    
    
    public String toString() {
        return type + "=" + value;
    }
    
    public boolean equals(Particle p) {
        if (value.equals(p.value) && type == p.type)
            return true;
        else
            return false;
    }
    
    /**
     * TO DO: UPDATE FOR SUPPORT WITH ORDINAL & OBJECT PARTICLES.
     * Resolves the type of a value from a string.
     * @param value the string to resolve the type of.
     */
    public static Particle resolveType(String value) {
        Particle newParticle;
        if(isNumeric(value)) { //If the passed string is numeric.
            if(isInteger(value))
                newParticle = new IntegerParticle(Integer.parseInt(value));
            else
                newParticle = new DoubleParticle(Double.parseDouble(value));
        } else { //If the passed string is just a string.
            if(value.isEmpty() || value.toUpperCase().contentEquals("NAN") || value.toUpperCase().contentEquals("NULL")) 
                newParticle = new NANParticle(value);
            else 
                newParticle = new StringParticle(value);
        }
        return newParticle;
    }
    
   /**
     * TO DO: UPDATE FOR SUPPORT WITH ORDINAL & OBJECT PARTICLES.
     * Resolves the type of a value from a string.
     * @param value the value to resolve the type of.
     */
    public static Particle resolveType(Object value) {
        Particle newParticle;
        if (value instanceof Integer)
            newParticle = new IntegerParticle((Integer) value);
        else if (value instanceof Double)
            newParticle = new DoubleParticle((Double) value);
        else if (value instanceof String) {
            String s = (String) value;
            if(s.isEmpty() || s.toUpperCase().contentEquals("NAN") || s.toUpperCase().contentEquals("NULL")) 
                newParticle = new NANParticle((String) value);
            else 
                newParticle = new StringParticle((String) value);
        } else {
            return null; //UPDATE FOR ORDINAL, OBJECT AND DISTANCE.
        }
        return newParticle;
    }
    
    /**
     * Checks if a passed string is an integer.
     * @param strNum the string which may contain an integer.
     * @return true if the string is an integer, false otherwise.
     */
    private static boolean isInteger(String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     * Checks if a passed string is a type of numeric value.
     * @param strNum the string which may contain a numeric value.
     * @return true of the string is numeric
     */
    private static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    public double getDoubleValue() {
    	double num = 0;
    	if(type == 'i') {
    		num = (int)value;
    		return (double) num;
    	}else {
    		return (double)value;
    	}
    }
    
    /**
     * Returns the value of the particle.
     * @return the value of the particle.
     */
    public abstract Object getValue();
    
    /**
     * Changes the value of the particle.
     * @param newValue 
     */
    public abstract void setValue(Object newValue);
    
    /**
     * Returns a deep copy of a given particle.
     * @return a deep copy of a given particle.
     */
    public abstract Particle deepCopy();
    
    public abstract int compareTo(Particle p);
}
