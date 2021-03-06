package org.eeml.math.expr;

import java.text.MessageFormat;
import java.util.Objects;

public class Coordinate {
	private double x;
	private double y;
	private double z;

	private boolean is2D;

	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		is2D = false;
	}

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
		is2D = true;
	}

	public double getDistance() {
		if (is2D)
			return Math.sqrt(x * x + y * y);
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double getDistance(Coordinate k) {
		if (is2D)
			return Math.sqrt((x - k.x) * (x - k.x) + (y - k.y) * (y - k.y));
		return Math.sqrt((x - k.x) * (x - k.x) + (y - k.y) * (y - k.y) + (z - k.z) * (z - k.z));
	}

	public static double getDistance(Coordinate t, Coordinate k) {
		if (t.is2D && k.is2D)
			return Math.sqrt((t.x - k.x) * (t.x - k.x) + (t.y - k.y) * (t.y - k.y));
		if (!t.is2D && !k.is2D)
			return Math.sqrt((t.x - k.x) * (t.x - k.x) + (t.y - k.y) * (t.y - k.y) + (t.z - k.z) * (t.z - k.z));
		throw new IllegalArgumentException("dimension mismatch");
	}

	@Override
	public String toString() {
		return MessageFormat.format("({0},{1}" + (is2D ? "" : ",{2}") + ")", x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate coordinate = (Coordinate) obj;
		return this.x == coordinate.x && this.y == coordinate.y && this.z == coordinate.y && this.is2D == coordinate.is2D;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z, is2D);
	}
}
