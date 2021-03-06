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
package net.sf.latexdraw.actions;

import java.io.File;
import java.util.concurrent.ExecutionException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import net.sf.latexdraw.badaboom.BadaboomCollector;
import net.sf.latexdraw.instruments.PreferencesSetter;
import org.malai.action.ActionsRegistry;
import org.malai.javafx.action.IOAction;
import org.malai.undo.UndoCollector;

/**
 * This action permits to create a new drawing and initialises the application as required.
 * @author Arnaud Blouin
 */
public class NewDrawing extends IOAction<Label> implements Modifying {
	/** The file chooser that will be used to select the location to save. */
	private FileChooser fileChooser;

	/** The instrument used that manage the preferences. */
	private PreferencesSetter prefSetter;

	private File currentFolder;


	@Override
	protected void doActionBody() {
		if(ui.isModified()) {
			final ButtonType type = SaveDrawing.showAskModificationsDialog();

			if(type == ButtonType.NO) {
				newDrawing();
			}else {
				if(type == ButtonType.YES) {
					SaveDrawing.showDialog(fileChooser, true, file, currentFolder, ui).ifPresent(f -> {
						try {
							openSaveManager.save(f.getPath(), progressBar, statusWidget).get();
						}catch(InterruptedException | ExecutionException ex) {
							BadaboomCollector.INSTANCE.add(ex);
						}
						ui.setModified(false);
						newDrawing();
					});
				}
			}
		}else {
			newDrawing();
		}
	}


	protected void newDrawing() {
		ui.reinit();
		UndoCollector.INSTANCE.clear();
		ActionsRegistry.INSTANCE.clear();
		try {
			prefSetter.readXMLPreferences();
		}catch(final Exception exc) {
			BadaboomCollector.INSTANCE.add(exc);
		}
	}


	@Override
	public boolean canDo() {
		return fileChooser != null && ui != null && openSaveManager != null && prefSetter != null;
	}


	@Override
	public void flush() {
		super.flush();
		fileChooser = null;
	}

	/**
	 * @param chooser The file chooser that will be used to select the location to save.
	 */
	public void setFileChooser(final FileChooser chooser) {
		fileChooser = chooser;
	}

	/**
	 * @param setter The instrument used that manage the preferences.
	 */
	public void setPrefSetter(final PreferencesSetter setter) {
		prefSetter = setter;
	}

	public void setCurrentFolder(final File currFolder) {
		currentFolder = currFolder;
	}
}
