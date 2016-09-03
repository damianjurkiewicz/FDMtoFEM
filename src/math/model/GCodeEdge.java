package math.model;

// TODO: Auto-generated Javadoc
/**
 * The Class edge.
 */
public class Edge {

	/** The v1. */
	private Vertex v1;

	/** The v2. */
	private Vertex v2;

	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	/**
	 * Gets the v1.
	 *
	 * @return the v1
	 */
	public Vertex getV1() {
		return v1;
	}

	/**
	 * Sets the v1.
	 *
	 * @param v1
	 *            the new v1
	 */
	public void setV1(Vertex v1) {
		this.v1 = v1;
	}

	/**
	 * Gets the v2.
	 *
	 * @return the v2
	 */
	public Vertex getV2() {
		return v2;
	}

	/**
	 * Sets the v2.
	 *
	 * @param v2
	 *            the new v2
	 */
	public void setV2(Vertex v2) {
		this.v2 = v2;
	}

	// TODO: test
	/**
	 * Oblicza d³ugoœæ odcinka w przestrzeni
	 *
	 * @return the length
	 */
	public double getLength() {
		double length;
		length = Math.sqrt(Math.pow(v2.getX() - v1.getX(), 2d) + Math.pow(v2.getY() - v1.getY(), 2d)
				+ Math.pow(v2.getZ() - v1.getZ(), 2d));
		return length;
	}

	public boolean isZeroLength() {
		boolean isZeroLength;
		if (getLength() == 0d) {
			isZeroLength = true;
		} else {
			isZeroLength = false;
		}
		return isZeroLength;
	}
}
