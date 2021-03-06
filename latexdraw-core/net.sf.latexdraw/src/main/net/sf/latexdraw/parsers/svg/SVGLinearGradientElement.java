/*
 * This file is part of LaTeXDraw.
 * Copyright (c) 2005-2017 Arnaud BLOUIN
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 */
package net.sf.latexdraw.parsers.svg;

import java.awt.geom.Point2D;
import java.text.ParseException;
import net.sf.latexdraw.models.MathUtils;
import net.sf.latexdraw.models.interfaces.shape.Color;
import net.sf.latexdraw.parsers.svg.parsers.SVGLengthParser;
import org.w3c.dom.Node;

/**
 * Defines the SVG tag <code>linearGradient</code>.
 * @author Arnaud BLOUIN
 */
public class SVGLinearGradientElement extends SVGElement {
	/**
	 * {@link SVGElement#SVGElement(Node, SVGElement)}
	 */
	public SVGLinearGradientElement(final Node n, final SVGElement p) throws MalformedSVGDocument {
		super(n, p);
	}


	/**
	 * Creates an simple SVGLinearGradientElement with the owner document.
	 * @param owner The owner document.
	 * @throws IllegalArgumentException If owner is null.
	 * @since 0.1
	 */
	public SVGLinearGradientElement(final SVGDocument owner) {
		super(owner);

		setNodeName(SVGElements.SVG_LINEAR_GRADIENT);
	}



	@Override
	public boolean checkAttributes() {
		return true;
	}



	@Override
	public boolean enableRendering() {
		return true;
	}



	/**
	 * @return The coordinate system for attributes x1, y1, x2 and y2.
	 * @since 0.1
	 */
	public String getGradientUnits() {
		final String v = getAttribute(getUsablePrefix()+SVGAttributes.SVG_GRADIENT_UNITS);

		return v==null || !SVGAttributes.SVG_UNITS_VALUE_OBJ.equals(v) &&
						!SVGAttributes.SVG_UNITS_VALUE_USR.equals(v) ? SVGAttributes.SVG_UNITS_VALUE_OBJ : v;
	}



	/**
	 * @return The value of the X1 attribute (0 if there it does not exist or it is not a length).
	 * @since 0.1
	 */
	public double getX1() {
		final String v = getAttribute(getUsablePrefix()+SVGAttributes.SVG_X1);
		double x1;

		try { x1 = v==null ? 0 : new SVGLengthParser(v).parseCoordinate().getValue(); }
		catch(final ParseException e) { x1 = 0; }

		return x1;
	}



	/**
	 * @return The value of the Y1 attribute (0 if there it does not exist or it is not a length).
	 * @since 0.1
	 */
	public double getY1() {
		final String v = getAttribute(getUsablePrefix()+SVGAttributes.SVG_Y1);
		double y1;

		try { y1 = v==null ? 0 : new SVGLengthParser(v).parseCoordinate().getValue(); }
		catch(final ParseException e) { y1 = 0; }

		return y1;
	}



	/**
	 * @return The value of the X2 attribute (0 if there it does not exist or it is not a length).
	 * @since 0.1
	 */
	public double getX2() {
		final String v = getAttribute(getUsablePrefix()+SVGAttributes.SVG_X2);
		double x2;

		try { x2 = v==null ? 0 : new SVGLengthParser(v).parseCoordinate().getValue(); }
		catch(final ParseException e) { x2 = 0; }

		return x2;
	}



	/**
	 * @return The value of the Y2 attribute (0 if there it does not exist or it is not a length).
	 * @since 0.1
	 */
	public double getY2() {
		final String v = getAttribute(getUsablePrefix()+SVGAttributes.SVG_Y2);
		double y2;

		try { y2 = v==null ? 0 : new SVGLengthParser(v).parseCoordinate().getValue(); }
		catch(final ParseException e) { y2 = 0; }

		return y2;
	}



	/**
	 * @return The start colour of the gradient or null.
	 * @since 0.1
	 */
	public Color getStartColor() {
		final SVGNodeList nl = getChildren(SVGElements.SVG_STOP);

		if(nl.getLength()==2) {
			if(((SVGStopElement)nl.item(0)).getOffset()<((SVGStopElement)nl.item(1)).getOffset())
				return ((SVGStopElement)nl.item(0)).getStopColor();

			return ((SVGStopElement)nl.item(1)).getStopColor();
		}
		else
			if(nl.getLength()==3) {
				final SVGStopElement s1 = (SVGStopElement)nl.item(0);
				final SVGStopElement s2 = (SVGStopElement)nl.item(1);
				final SVGStopElement s3 = (SVGStopElement)nl.item(2);

				if(s1.getOffset()<s2.getOffset()) {
					if(s1.getOffset()<s3.getOffset())
						return s1.getStopColor();

					return s3.getStopColor();
				}

				if(s2.getOffset()<s3.getOffset())
					return s2.getStopColor();

				return s3.getStopColor();
			}

		return null;
	}



	/**
	 * @return The end colour of the gradient or null.
	 * @since 0.1
	 */
	public Color getEndColor() {
		final SVGNodeList nl = getChildren(SVGElements.SVG_STOP);

		if(nl.getLength()==2) {
			if(((SVGStopElement)nl.item(0)).getOffset()>((SVGStopElement)nl.item(1)).getOffset())
				return ((SVGStopElement)nl.item(0)).getStopColor();

			return ((SVGStopElement)nl.item(1)).getStopColor();
		}
		else
			if(nl.getLength()==3) {
				final SVGStopElement s1 = (SVGStopElement)nl.item(0);
				final SVGStopElement s2 = (SVGStopElement)nl.item(1);
				final SVGStopElement s3 = (SVGStopElement)nl.item(2);

				if(s1.getOffset()>s2.getOffset()) {
					if(s1.getOffset()<s3.getOffset())
						return s1.getStopColor();

					return s3.getStopColor();
				}

				if(s2.getOffset()<s3.getOffset())
					return s2.getStopColor();

				return s3.getStopColor();
			}

		return null;
	}



	/**
	 * @return The middle point of the gradient (between 0 and 1 inclued) or NaN.
	 * @since 0.1
	 */
	public double getMiddlePoint() {
		double midPoint = Double.NaN;
		final SVGNodeList nl = getChildren(SVGElements.SVG_STOP);

		if(nl.getLength()==2)
			midPoint = Math.max(((SVGStopElement)nl.item(0)).getOffset(), ((SVGStopElement)nl.item(1)).getOffset());
		else
			if(nl.getLength()==3) {
				final SVGStopElement s1 = (SVGStopElement)nl.item(0);
				final SVGStopElement s2 = (SVGStopElement)nl.item(1);
				final SVGStopElement s3 = (SVGStopElement)nl.item(2);

				if(s1.getOffset()>s2.getOffset())
					if(s1.getOffset()<s3.getOffset())
						midPoint = s1.getOffset();
					else
						midPoint = s3.getOffset();
				else
					if(s2.getOffset()<s3.getOffset())
						midPoint = s2.getOffset();
					else
						midPoint = s3.getOffset();
			}

		return midPoint;
	}



	/**
	 * @return The angle of the gradient in radian or NaN.
	 * @since 0.1
	 */
	public double getAngle() {
		final double x1 = getX1();
        final double x2 = getX2();
        final double y1 = getY1();
        final double y2 = getY2();
        double angle = Double.NaN;

		if(MathUtils.INST.equalsDouble(x1, x2))
			if(SVGAttributes.SVG_UNITS_VALUE_OBJ.equals(getGradientUnits()))
				angle = Math.PI/2.;
			else
				angle = 0.;
		else
			if(MathUtils.INST.equalsDouble(y1, y2))
				angle = Math.PI;

		if(Double.isNaN(angle)) {
			final Point2D.Double c = new Point2D.Double((x1+x2)/2., (y1+y2)/2.);
			angle = Math.asin(c.distance(x1, c.y)/c.distance(x1, y1));
		}

		return angle%(2*Math.PI);
	}



	/**
	 * Indicates what happens if the gradient starts or ends inside the bounds of the target rectangle.
	 * @return pad | reflect | repeat
	 * @since 0.1
	 */
	public String getSpreadMethod() {
		final String v = getAttribute(getUsablePrefix()+SVGAttributes.SVG_SPREAD_METHOD);

		return v==null || !SVGAttributes.SVG_PAD.equals(v) && !SVGAttributes.SVG_REFLECT.equals(v) &&
				!SVGAttributes.SVG_REPEAT.equals(v) ? SVGAttributes.SVG_PAD : v;
	}
}
