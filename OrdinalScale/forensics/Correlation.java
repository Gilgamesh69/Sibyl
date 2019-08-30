package forensics;
import saga.*;

public abstract class Correlation {
	Column x;
	Column y;
	public Correlation(Column x, Column y) {
		this.x = x;
		this.y = y;
		
	}

	/**
	 * @return the correlation coefficient
	 */
	public abstract double correlation();

}
