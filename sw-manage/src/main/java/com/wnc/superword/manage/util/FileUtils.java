package com.wnc.superword.manage.util;

import com.wnc.tools.FileTypeUtil;

public class FileUtils {

	public static boolean isThumbPic(String pic) {
		return FileTypeUtil.isVideoFile(pic.replace(".jpg", "")) || FileTypeUtil.isVoiceFile(pic.replace(".jpg", ""));
	}

	public static boolean isMedia(String media) {
		return FileTypeUtil.isVideoFile(media) || FileTypeUtil.isVoiceFile(media);
	}

}
