package com.project.doubleshop.web.common.file;

public class FileUtils {
	public static String getExtension(String fileName) {
		if (fileName == null) {
			return null;
		} else {
			int index = indexOfExtension(fileName);
			return index == -1 ? "" : fileName.substring(index + 1);
		}
	}

	public static int indexOfExtension(String fileName) {
		if (fileName == null) {
			return -1;
		} else {
			int extensionPosition = fileName.lastIndexOf(46); // ascii code for .
			int lastSeparator = indexOfLastSeparator(fileName);
			return lastSeparator > extensionPosition ? -1 : extensionPosition;
		}
	}

	public static int indexOfLastSeparator(String fileName) {
		if (fileName == null) {
			return -1;
		} else {
			int unixSeparatorPosition = fileName.lastIndexOf(47); // ascii code for /
			int windowSeparatorPosition = fileName.lastIndexOf(92); // ascii code for \
			return Math.max(unixSeparatorPosition, windowSeparatorPosition);
		}
	}
}
