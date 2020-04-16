package primitives;

/**
 * class for represent Coordinate in 1D model
 * 
 * 
 */

public class Coordinate {

	private double coord;
	// private final double EPSILON = 0.0000001;
	// It is binary, equivalent to ~1/1,000,000 in decimal (6 digits)
	private static final int ACCURACY = -20;

	/************ Constructors **********/
	public Coordinate(double num) {
		coord = (getExp(num) < ACCURACY) ? 0.0 : num;
		// coord = num;
	}

	public Coordinate(Coordinate other) {
		coord = other.coord;
	}

	/********** Getters/Setters *********/
	double getCoord() {
		return coord;
	}

	/************ Administration *********/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		return (subtract(other.coord) == 0.0);
	}

	// @Override
	// public boolean equals(Object obj) {
	// if (!(obj instanceof Coordinate))
	// return false;
	// Coordinate other = (Coordinate) obj;
	// return Math.abs((coord - other.coord)) < EPSILON;
	// }

	public String tostring() {
		return " " + coord + " ";
	}

	/************ OperatorsAndfunctions *********/

	private double subtract(double other) {
		int otherExp = getExp(other);
		int thisExp = getExp(coord);
		// if other is too small relatively to our coordinate return the
		// original coordinate
		if (otherExp - thisExp < ACCURACY)
			return coord;
		// if our coordinate is too small relatively to other return negative of
		// other
		if (thisExp - otherExp < ACCURACY)
			return -other;
		double result = coord - other;
		// if the result is too small tell that it is zero
		int resultExp = getExp(result);
		return resultExp < ACCURACY ? 0.0 : result;
	}

	private double add(double other) {

		// Coordinate newCoord = new Coordinate(this.getCoord() + x);
		// return newCoord;

		// int otherExp = getExp(coord);
		int otherExp = getExp(other);
		int thisExp = getExp(coord);
		// if other is too small relatively to our coordinate return the
		// original coordinate
		if (otherExp - thisExp < ACCURACY)
			return coord;
		// if our coordinate is too small relatively to other return other
		if (thisExp - otherExp < ACCURACY)
			return other;
		double result = coord + other;
		// if the result is too small tell that it is zero
		int resultExp = getExp(result);
		return resultExp < ACCURACY ? 0.0 : result;

	}

	public Coordinate subtract(Coordinate other) {
		return new Coordinate(subtract(other.coord));
	}

	public Coordinate add(Coordinate other) {
		return new Coordinate(add(other.coord));
	}

	public Coordinate scale(double num) {
		return new Coordinate(_scale(num));
	}

	public Coordinate multiply(Coordinate other) {
		return new Coordinate(_scale(other.coord));
	}

	// double store format: seee eeee eeee (1.)mmmm  mmmm
	// 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa

    private static int getExp(double num) {
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }

    public static boolean isZero(double number) { return getExp(number) < ACCURACY; }

    private double _scale(double num) {
        int deltaExp = getExp(num - 1);
        return deltaExp < ACCURACY ? coord : coord * num;
    }
}
