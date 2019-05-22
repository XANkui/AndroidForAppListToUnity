package com.example.applisttounity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class FileUtils {
	public synchronized static byte[] BitmapToByte(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public  static String BytesToString(byte[] imagedata) {
		return Base64.encodeToString(imagedata, Base64.DEFAULT);
	}

	public  static Bitmap DrawableToBitmap(Drawable drawable) {
		return (((android.graphics.drawable.BitmapDrawable) drawable).getBitmap());
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
