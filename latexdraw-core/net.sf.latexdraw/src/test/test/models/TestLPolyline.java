package test.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.latexdraw.models.ShapeFactory;
import net.sf.latexdraw.models.interfaces.shape.ICircle;
import net.sf.latexdraw.models.interfaces.shape.IModifiablePointsShape;
import net.sf.latexdraw.models.interfaces.shape.IPoint;
import net.sf.latexdraw.models.interfaces.shape.IPolygon;
import net.sf.latexdraw.models.interfaces.shape.IPolyline;
import net.sf.latexdraw.models.interfaces.shape.IRectangle;
import net.sf.latexdraw.models.interfaces.shape.IShape;

import org.junit.Before;
import org.junit.Test;

import test.models.interfaces.TestIPolyline;

public class TestLPolyline extends TestIPolyline<IPolyline> {
	@Before
	public void setUp() {
		shape = ShapeFactory.INST.createPolyline();
		shape2 = ShapeFactory.INST.createPolyline();
		shape.addPoint(ShapeFactory.INST.createPoint(1, 1));
		shape.addPoint(ShapeFactory.INST.createPoint(2, 2));
		shape.addPoint(ShapeFactory.INST.createPoint(3, 3));
		shape2.addPoint(ShapeFactory.INST.createPoint(1, 1));
		shape2.addPoint(ShapeFactory.INST.createPoint(2, 2));
		shape2.addPoint(ShapeFactory.INST.createPoint(3, 3));
	}

	@Override
	@Test
	public void testIsTypeOf() {
		assertFalse(shape.isTypeOf(null));
		assertFalse(shape.isTypeOf(IRectangle.class));
		assertFalse(shape.isTypeOf(ICircle.class));
		assertTrue(shape.isTypeOf(IShape.class));
		assertTrue(shape.isTypeOf(IModifiablePointsShape.class));
		assertTrue(shape.isTypeOf(IPolygon.class));
		assertTrue(shape.isTypeOf(IPolyline.class));
		assertTrue(shape.isTypeOf(shape.getClass()));
	}

	@Test
	public void testConstructor() {
		IPoint pt1 = ShapeFactory.INST.createPoint(1, 1);
		IPoint pt2 = ShapeFactory.INST.createPoint(2, 2);
		IPolyline pol = ShapeFactory.INST.createPolyline(pt1, pt2);

		assertEquals(pt1, pol.getPtAt(0));
		assertEquals(pt2, pol.getPtAt(-1));
	}
}
