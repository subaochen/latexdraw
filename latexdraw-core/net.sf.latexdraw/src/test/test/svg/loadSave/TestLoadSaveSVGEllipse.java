package test.svg.loadSave;

import net.sf.latexdraw.glib.models.ShapeFactory;
import net.sf.latexdraw.glib.models.interfaces.shape.IEllipse;

import org.junit.Before;

public class TestLoadSaveSVGEllipse extends TestLoadSaveSVGRectangularShape<IEllipse> {
	@Before
	@Override
	public void setUp() {
		shape = ShapeFactory.createEllipse(false);
	}
}
