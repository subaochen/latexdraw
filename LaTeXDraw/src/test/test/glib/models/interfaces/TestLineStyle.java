package test.glib.models.interfaces;

import junit.framework.TestCase;

import net.sf.latexdraw.glib.models.interfaces.IShape.LineStyle;
import net.sf.latexdraw.glib.views.pst.PSTricksConstants;

import org.junit.Test;

public class TestLineStyle extends TestCase
{
	@Test
	public void getLatexToken() {
		assertEquals(LineStyle.DASHED.getLatexToken(), PSTricksConstants.LINE_DASHED_STYLE);
		assertEquals(LineStyle.DOTTED.getLatexToken(), PSTricksConstants.LINE_DOTTED_STYLE);
		assertEquals(LineStyle.NONE.getLatexToken(), PSTricksConstants.LINE_NONE_STYLE);
		assertEquals(LineStyle.SOLID.getLatexToken(), PSTricksConstants.LINE_SOLID_STYLE);
	}


	@Test
	public void testGetStyle() {
		assertNull(LineStyle.getStyle(null));
		assertNull(LineStyle.getStyle(""));
		assertNull(LineStyle.getStyle("erzter fd"));
		assertNull(LineStyle.getStyle("ez87 fd"));
		assertEquals(LineStyle.getStyle(LineStyle.DASHED.toString()), LineStyle.DASHED);
		assertEquals(LineStyle.getStyle(LineStyle.DOTTED.toString()), LineStyle.DOTTED);
		assertEquals(LineStyle.getStyle(LineStyle.NONE.toString()), LineStyle.NONE);
		assertEquals(LineStyle.getStyle(LineStyle.SOLID.toString()), LineStyle.SOLID);
	}


	@Test
	public void testGetStyleFromLatex() {
		assertNull(LineStyle.getStyleFromLatex(null));
		assertNull(LineStyle.getStyleFromLatex(""));
		assertNull(LineStyle.getStyleFromLatex("fdfd"));
		assertNull(LineStyle.getStyleFromLatex("&é^kd çdu"));
		assertEquals(LineStyle.getStyleFromLatex(PSTricksConstants.LINE_DASHED_STYLE), LineStyle.DASHED);
		assertEquals(LineStyle.getStyleFromLatex(PSTricksConstants.LINE_DOTTED_STYLE), LineStyle.DOTTED);
		assertEquals(LineStyle.getStyleFromLatex(PSTricksConstants.LINE_NONE_STYLE), LineStyle.NONE);
		assertEquals(LineStyle.getStyleFromLatex(PSTricksConstants.LINE_SOLID_STYLE), LineStyle.SOLID);
	}
}