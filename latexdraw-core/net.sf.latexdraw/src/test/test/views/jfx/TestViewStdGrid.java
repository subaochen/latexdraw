package test.views.jfx;

import java.util.concurrent.TimeoutException;
import javafx.scene.text.Text;
import net.sf.latexdraw.models.interfaces.shape.IStandardGrid;
import net.sf.latexdraw.view.jfx.ViewStdGrid;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;

import static org.junit.Assert.assertNotEquals;

abstract class TestViewStdGrid<S extends ViewStdGrid<T>, T extends IStandardGrid> extends TestViewShape<S, T> {
	@BeforeClass
	public static void beforeClass() throws TimeoutException {
		FxToolkit.registerPrimaryStage();
	}

	@Test
	public abstract void testChangeGridEndX();

	@Test
	public abstract void testChangeGridEndY();

	@Test
	public abstract void testChangeGridStartX();

	@Test
	public abstract void testChangeGridStartY();

	@Test
	public abstract void testChangeOriginX();

	@Test
	public abstract void testChangeOriginY();

	@Test
	public void testChangeLabelSize() {
		final double sizeBefore = ((Text) view.getLabels().getChildren().get(0)).getFont().getSize();
		model.setLabelsSize(model.getLabelsSize() + 5);
		assertNotEquals(sizeBefore, ((Text) view.getLabels().getChildren().get(0)).getFont().getSize());
	}
}
