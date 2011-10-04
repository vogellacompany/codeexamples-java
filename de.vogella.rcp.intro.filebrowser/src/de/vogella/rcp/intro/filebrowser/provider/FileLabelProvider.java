package de.vogella.rcp.intro.filebrowser.provider;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class FileLabelProvider extends LabelProvider {
	private static final Image folderImage = AbstractUIPlugin
			.imageDescriptorFromPlugin("de.vogella.rcp.intro.filebrowser",
					"icons/folder.gif").createImage();
	private static final Image driveImage = AbstractUIPlugin
			.imageDescriptorFromPlugin("de.vogella.rcp.intro.filebrowser",
					"icons/filenav_nav.gif").createImage();
	private static final Image fileImage = AbstractUIPlugin
			.imageDescriptorFromPlugin("de.vogella.rcp.intro.filebrowser",
					"icons/file_obj.gif").createImage();

	@Override
	public Image getImage(Object element) {
		File file = (File) element;
		if (file.isDirectory())
			return file.getParent() != null ? folderImage : driveImage;

		// String extension = extension(((File) element).getName());
		// if (extension != null && extension.length() > 0) {
		// ImageData imageData = Program.findProgram(extension).getImageData();
		// return new Image(Display.getDefault(), imageData);
		// }

		return fileImage;
	}

	@Override
	public String getText(Object element) {
		String fileName = ((File) element).getName();
		if (fileName.length() > 0) {
			return fileName;
		}
		return ((File) element).getPath();
	}

	public String extension(String fullPath) {
		int dot = fullPath.lastIndexOf(".");
		return fullPath.substring(dot + 1);
	}
}
