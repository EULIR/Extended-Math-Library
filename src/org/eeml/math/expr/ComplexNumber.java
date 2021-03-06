package org.eeml.math.expr;

import org.eeml.math.exception.DivideZeroException;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * This class mainly implements the math expression complex number including methods like add,
 * multiply etc.The naming principle is the same as java.math.BigInteger.
 * Maintained by<a href="https://github.com/EEML">EEML</a>.see more on<code>eeml.github.io</code>
 *
 * @author EULIR
 * @see org.eeml.math;
 * @since v1.0.0
 */

public class ComplexNumber {
	private double real;
	private double imaginary;

	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * @param a the addend complex number.
	 * @return a new complex number which stands for the sum of two complex numbers.
	 */
	public ComplexNumber add(ComplexNumber a) {
		return new ComplexNumber(this.real + a.real, this.imaginary + a.imaginary);
	}

	/**
	 * @param a the addend integer number.
	 * @return a new complex number which stands for the sum of two numbers.
	 */
	public ComplexNumber add(int a) {
		return new ComplexNumber(this.real + a, this.imaginary);
	}

	/**
	 * @param a the addend real number.
	 * @return a new complex number which stands for the sum of two numbers.
	 */
	public ComplexNumber add(double a) {
		return new ComplexNumber(this.real + a, this.imaginary);
	}

	/**
	 * @param a the minuend complex number.
	 * @return a new complex number which stands for the difference between two complex numbers.
	 */
	public ComplexNumber subtract(ComplexNumber a) {
		return new ComplexNumber(this.real - a.real, this.imaginary - a.imaginary);
	}

	/**
	 * @param a the minuend real number.
	 * @return a new complex number which stands for the difference between two numbers.
	 */
	public ComplexNumber subtract(double a) {
		return new ComplexNumber(this.real - a, this.imaginary);
	}

	/**
	 * @param a the multiplier complex number.
	 * @return a new complex number which stands for the product of two complex numbers.
	 */
	public ComplexNumber multiply(ComplexNumber a) {
		double real = this.real * a.real - this.imaginary * a.imaginary;
		double imaginary = this.real * a.imaginary + this.imaginary * a.real;
		return new ComplexNumber(real, imaginary);
	}

	/**
	 * @param a the multiplier real number.
	 * @return a new complex number which stands for the product of two numbers.
	 */
	public ComplexNumber multiply(double a) {
		return new ComplexNumber(this.real * a, this.imaginary * a);
	}

	/**
	 * @param a the dividend complex number.
	 * @return a new complex number which stands for the quotient of two complex numbers.
	 * @throws DivideZeroException when a complex number is dividing zero.
	 */
	public ComplexNumber divide(ComplexNumber a) {
		if (a.equals(new ComplexNumber(0, 0)))
			throw new DivideZeroException("Complex number cannot divide zero.");
		double real = (this.real * a.real + this.imaginary * a.imaginary) / (a.real * a.real + a.imaginary * a.imaginary);
		double imaginary = (this.imaginary * a.real - this.real * a.imaginary) / (a.real * a.real + a.imaginary * a.imaginary);
		return new ComplexNumber(real, imaginary);
	}

	/**
	 * @param a the dividend real number.
	 * @return a new complex number which stands for the quotient of two numbers.
	 * @throws DivideZeroException when a complex number is dividing zero.
	 */
	public ComplexNumber divide(double a) {
		if (a == 0)
			throw new DivideZeroException("Complex number cannot divide zero.");
		return new ComplexNumber(this.real / a, this.imaginary / a);
	}

	/**
	 * To judge whether the complex number can be transferred into a double value.
	 * When this complex number has no imaginary part, we can say it is transferable.
	 *
	 * @return true if the complex number is transferable, false otherwise.
	 */
	public boolean transferable() {
		return imaginary == 0;
	}

	/**
	 * Transfer the complex number into the double value. If and only if the complex
	 * number is transferable can it be successfully be changed(the complex number
	 * should have no imaginary part when it can be transferred). Therefore, the method
	 * {@code transferable} should almost always be called before calling method {@code toDouble}.
	 *
	 * @return double value of real part of this complex number.
	 * @throws IllegalStateException when this complex number has imaginary part.
	 */
	public double toDouble() {
		if (transferable())
			return real;
		throw new IllegalArgumentException("This complex number is not transferable.");
	}

	/**
	 * toString method overridden from super class
	 *
	 * @return return String formed in
	 * real + imaginary + "i"
	 */
	@Override
	public String toString() {
		if (this.real == 0 && this.imaginary == 0)
			return "0";
		if (this.real == 0)
			return MessageFormat.format((this.imaginary == 1 ? "" : (this.imaginary == -1 ? "-" : "{0}")) + "i", this.imaginary);
		if (this.imaginary == 0)
			return MessageFormat.format("{0}", this.real);
		return MessageFormat.format("{0}" + (this.imaginary > 0 ? "+" : "") + (this.imaginary == 1 ? "" : (this.imaginary == -1 ? "-" : "{1}")) + "i", this.real, this.imaginary);
	}

	/**
	 * equals method overridden form super class
	 *
	 * @param obj the compared object
	 * @return true if two complex numbers are equal, otherwise false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ComplexNumber))
			return false;
		ComplexNumber complexNumber = (ComplexNumber) obj;
		return Math.abs(this.real - complexNumber.real) < 0.001
				&& Math.abs(this.imaginary - complexNumber.imaginary) < 0.001;
	}

	/**
	 * hashCode method overridden from super class
	 *
	 * @return hashCode of the complex number.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(real, imaginary);
	}
}
