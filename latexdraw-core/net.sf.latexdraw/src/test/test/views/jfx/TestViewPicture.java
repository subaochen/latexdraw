package test.views.jfx;

import java.util.concurrent.TimeoutException;
import javafx.geometry.Bounds;
import net.sf.latexdraw.models.ShapeFactory;
import net.sf.latexdraw.models.interfaces.shape.IPicture;
import net.sf.latexdraw.view.jfx.ViewPicture;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.api.FxToolkit;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestViewPicture extends TestViewShape<ViewPicture, IPicture> {
	@BeforeClass
	public static void beforeClass() throws TimeoutException {
		FxToolkit.registerPrimaryStage();
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Override
	protected IPicture createModel() {
		return ShapeFactory.INST.createPicture(ShapeFactory.INST.createPoint(101, 67));
	}

	@Test
	@Override
	public void testOnTranslateX() {
		final Bounds before = view.getBoundsInParent();
		model.translate(17d, 0d);
		view.getChildren().get(0).setTranslateX(-17d);
		assertEquals(before, view.getBoundsInParent());
	}

	@Test
	@Override
	public void testOnTranslateY() {
		final Bounds before = view.getBoundsInParent();
		model.translate(0d, -19d);
		view.getChildren().get(0).setTranslateY(19d);
		assertEquals(before, view.getBoundsInParent());
	}
}
