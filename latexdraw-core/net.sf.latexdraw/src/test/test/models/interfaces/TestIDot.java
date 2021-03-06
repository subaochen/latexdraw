package test.models.interfaces;

import net.sf.latexdraw.models.ShapeFactory;
import net.sf.latexdraw.models.interfaces.shape.DotStyle;
import net.sf.latexdraw.models.interfaces.shape.IDot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class TestIDot<T extends IDot> extends TestIPositionShape<T> {
	@Test
	public void testGetSetDotStyle() {
		shape.setDotStyle(DotStyle.ASTERISK);
		assertEquals(DotStyle.ASTERISK, shape.getDotStyle());
		shape.setDotStyle(DotStyle.BAR);
		assertEquals(DotStyle.BAR, shape.getDotStyle());
		shape.setDotStyle(DotStyle.DIAMOND);
		assertEquals(DotStyle.DIAMOND, shape.getDotStyle());
		shape.setDotStyle(DotStyle.DOT);
		assertEquals(DotStyle.DOT, shape.getDotStyle());
		shape.setDotStyle(DotStyle.FDIAMOND);
		assertEquals(DotStyle.FDIAMOND, shape.getDotStyle());
		shape.setDotStyle(DotStyle.FPENTAGON);
		assertEquals(DotStyle.FPENTAGON, shape.getDotStyle());
		shape.setDotStyle(DotStyle.FSQUARE);
		assertEquals(DotStyle.FSQUARE, shape.getDotStyle());
		shape.setDotStyle(DotStyle.FTRIANGLE);
		assertEquals(DotStyle.FTRIANGLE, shape.getDotStyle());
		shape.setDotStyle(DotStyle.O);
		assertEquals(DotStyle.O, shape.getDotStyle());
		shape.setDotStyle(DotStyle.OPLUS);
		assertEquals(DotStyle.OPLUS, shape.getDotStyle());
		shape.setDotStyle(DotStyle.OTIMES);
		assertEquals(DotStyle.OTIMES, shape.getDotStyle());
		shape.setDotStyle(DotStyle.PENTAGON);
		assertEquals(DotStyle.PENTAGON, shape.getDotStyle());
		shape.setDotStyle(DotStyle.PLUS);
		assertEquals(DotStyle.PLUS, shape.getDotStyle());
		shape.setDotStyle(DotStyle.SQUARE);
		assertEquals(DotStyle.SQUARE, shape.getDotStyle());
		shape.setDotStyle(DotStyle.TRIANGLE);
		assertEquals(DotStyle.TRIANGLE, shape.getDotStyle());
		shape.setDotStyle(DotStyle.X);
		assertEquals(DotStyle.X, shape.getDotStyle());
		shape.setDotStyle(null);
		assertEquals(DotStyle.X, shape.getDotStyle());
	}

	@Test
	public void testGetSetRadius() {
		shape.setDiametre(22);
		assertEqualsDouble(22., shape.getDiametre());
		shape.setDiametre(1);
		assertEqualsDouble(1., shape.getDiametre());
		shape.setDiametre(0.001);
		assertEqualsDouble(0.001, shape.getDiametre());
		shape.setDiametre(0);
		assertEqualsDouble(0.001, shape.getDiametre());
		shape.setDiametre(-0.001);
		assertEqualsDouble(0.001, shape.getDiametre());
		shape.setDiametre(-1);
		assertEqualsDouble(0.001, shape.getDiametre());
		shape.setDiametre(Double.NaN);
		assertEqualsDouble(0.001, shape.getDiametre());
		shape.setDiametre(Double.POSITIVE_INFINITY);
		assertEqualsDouble(0.001, shape.getDiametre());
		shape.setDiametre(Double.NEGATIVE_INFINITY);
		assertEqualsDouble(0.001, shape.getDiametre());
	}

	@Override
	@Test
	public void testCopy() {
		super.testCopy();

		shape2.setDotStyle(DotStyle.DIAMOND);
		shape2.setDiametre(31);
		shape.copy(shape2);
		assertEquals(shape2.getDotStyle(), shape.getDotStyle());
		assertEqualsDouble(shape2.getDiametre(), shape.getDiametre());
	}

	@Override
	@Test
	public void testGetBottomLeftPoint() {
		// TODO
	}

	@Override
	@Test
	public void testGetBottomRightPoint() {
		// TODO
	}

	@Override
	@Test
	public void testGetTopLeftPoint() {
		// TODO
	}

	@Override
	@Test
	public void testGetTopRightPoint() {
		// TODO
	}

	@Override
	@Test
	public void testMirrorHorizontal() {
		shape.setPosition(-10, -20);
		shape.mirrorHorizontal(ShapeFactory.INST.createPoint(0, 0));
		assertEqualsDouble(10., shape.getPosition().getX());
		assertEqualsDouble(-20., shape.getPosition().getY());
	}

	@Test
	public void testMirrorHorizontal2() {
		shape.setPosition(-10, -20);
		shape.mirrorHorizontal(null);
		assertEqualsDouble(-10., shape.getPosition().getX());
		assertEqualsDouble(-20., shape.getPosition().getY());
	}

	@Test
	public void testMirrorHorizontal3() {
		shape.setPosition(-10, -20);
		shape.mirrorHorizontal(ShapeFactory.INST.createPoint(Double.NaN, Double.POSITIVE_INFINITY));
		assertEqualsDouble(-10., shape.getPosition().getX());
		assertEqualsDouble(-20., shape.getPosition().getY());
	}

	@Override
	@Test
	public void testMirrorVertical() {
		shape.setPosition(-10, -20);
		shape.mirrorVertical(ShapeFactory.INST.createPoint(100, 0));
		assertEqualsDouble(-10., shape.getPosition().getX());
		assertEqualsDouble(20., shape.getPosition().getY());
	}

	@Test
	public void testMirrorVertical2() {
		shape.setPosition(-10, -20);
		shape.mirrorVertical(null);
		assertEqualsDouble(-10., shape.getPosition().getX());
		assertEqualsDouble(-20., shape.getPosition().getY());
	}

	@Test
	public void testMirrorVertical3() {
		shape.setPosition(-10, -20);
		shape.mirrorVertical(ShapeFactory.INST.createPoint(Double.NaN, Double.POSITIVE_INFINITY));
		assertEqualsDouble(-10., shape.getPosition().getX());
		assertEqualsDouble(-20., shape.getPosition().getY());
	}
}
