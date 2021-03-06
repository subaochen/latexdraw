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
package net.sf.latexdraw.models.interfaces.shape;

/**
 * The API for Bezier curves.
 * @author Arnaud BLOUIN
 */
public interface IBezierCurve extends IControlPointShape, IArrowableShape {
	/**
	 * @return the isClosed.
	 */
	boolean isClosed();

	/**
	 * Defines if the shape is closed.
	 * @param isClosed True: the shape will be closed.
	 * @since 3.0
	 */
	void setIsClosed(final boolean isClosed);
}
