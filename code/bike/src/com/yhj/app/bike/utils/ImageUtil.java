package com.yhj.app.bike.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;

import com.yhj.app.bike.system.Application;

/**
 * 图片工具类
 */
public class ImageUtil {
	/** 图像质量高 */
	public static final int QUALITY_HIGH = 85;

	/** 图像质量中 */
	public static final int QUALITY_MIDDLE = 75;

	/** 图像质量低 */
	public static final int QUALITY_LOW = 70;

	private static float scaleDensity = 0.0f;

	// 获得圆角图片的方法
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		if (bitmap == null) {
			return null;
		}
		try {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
			return output;
		} catch (OutOfMemoryError e) {
			SLog.e(SLog.BIKE, e.toString());
			return null;
		}
	}
	
	public static Drawable getBlackBitmap(Bitmap bm) {
		Drawable[] array = new Drawable[2];    
		array[0] = new BitmapDrawable(bm);
		array[1] = new PaintDrawable(Color.parseColor("#4d000000"));
		LayerDrawable ld = new LayerDrawable(array);
		return ld;
	}
	
	public static Drawable getBlackBitmapForExpress(Bitmap bm) {
		BitmapDrawable drawable = new BitmapDrawable(bm);
		drawable.setBounds(0, 0, drawable.getBitmap().getWidth(),
				drawable.getBitmap().getHeight());
		drawable.mutate();
		drawable.setColorFilter(0x7fffffff, Mode.MULTIPLY);
		return drawable;
	}
	
	public static Drawable getBlackBitmapForExpress(Drawable drawable) {
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.mutate();
		drawable.setColorFilter(0x7fffffff, Mode.MULTIPLY);
		return drawable;
	}
	
	public static Drawable getBlackBitmapForExpress(Context context, int id) {
		Drawable drawable = context.getResources().getDrawable(id);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.mutate();
		drawable.setColorFilter(0x7fffffff, Mode.MULTIPLY);
		return drawable;
	}

	public static float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	
	public static float spacing(MotionEvent event1, MotionEvent event2) {
		float x = event1.getX() - event2.getX();
		float y = event1.getY() - event2.getY();
		return FloatMath.sqrt(x * x + y * y);
	}

	/*
	 * 缩放大图的策略，缩放到固定大小，先使用不占内存的方法将图像缩小至一定范围，再进行读取缩放
	 */
	public static Bitmap reszieBigImage(String filePath, int maxLength) {

		ParcelFileDescriptor pfd;
		try {
			pfd = Application.getInstance().getContentResolver().openFileDescriptor(Uri.parse("file://" + filePath), "r");
		} catch (IOException ex) {
			return null;
		}
		java.io.FileDescriptor fd = pfd.getFileDescriptor();
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 先指定原始大小
		options.inSampleSize = 1;
		// 只进行大小判断
		options.inJustDecodeBounds = true;
		// 调用此方法得到options得到图片的大小
		BitmapFactory.decodeFileDescriptor(fd, null, options);
		// 我们的目标是在480pixel的画面上显示。
		// 所以需要调用computeSampleSize得到图片缩放的比例
		options.inSampleSize = computeSampleSize(options, maxLength);
		// OK,我们得到了缩放的比例，现在开始正式读入BitMap数据
		options.inJustDecodeBounds = false;
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.RGB_565;

		// 根据options参数，减少所需要的内存
		Bitmap sourceBitmap = BitmapFactory.decodeFileDescriptor(fd, null, options);

		// 再对图像进一步进行精确缩放
		double scale = maxLength * 1.0 / (Math.max(sourceBitmap.getWidth(), sourceBitmap.getHeight()));
		int newWidth = (int) (sourceBitmap.getWidth() * scale);
		int newHeight = (int) (sourceBitmap.getHeight() * scale);
		return resizeBitmapSmooth(sourceBitmap, newWidth, newHeight);
	}

	/*
	 * 计算缩放比例
	 */
	private static int computeSampleSize(BitmapFactory.Options options, int target) {
		int w = options.outWidth;
		int h = options.outHeight;

		int length = Math.max(w, h);
		int candidate = length / target;

		if (candidate == 0)
			return 1;

		if (candidate > 1) {
			if ((length > target) && (length / candidate) < target)
				candidate -= 1;
		}

		return candidate;
	}

	/*
	 * 等比缩放图像
	 */
	public static Bitmap resizeBitmapSmooth(Bitmap srcBitmap, int desWidth, int desHeight) {
		try {
			if (srcBitmap == null || desWidth < 0 || desHeight < 0) {
				return null;
			}
			Bitmap dstBitmap = Bitmap.createScaledBitmap(srcBitmap, desWidth, desHeight, true);
			return dstBitmap;
		} catch (OutOfMemoryError e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	/*
	 * 保存图片到文件地址，并且设置图片质量（高、中、低）
	 */
	public static boolean saveBitmap(Bitmap bitmap, String path, int quality) {
		// Bitmap图片保存
		if (bitmap == null || path == null || path.equals("")) {
			return false;
		}
		BufferedOutputStream ostream = null;
		try {
			File file = new File(path);
			FileUtil.makeDIRAndCreateFile(path);
			ostream = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(CompressFormat.JPEG, quality, ostream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			SLog.e(SLog.BIKE, e.toString());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			SLog.e(SLog.BIKE, e.toString());
			return false;
		} finally {
			try {
				if (ostream != null) {
					ostream.flush();
					ostream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				SLog.e(SLog.BIKE, e.toString());
				return false;
			}
		}
		return true;
	}

	public static boolean saveBitmapPNG(Bitmap bitmap, String path, int quality) {
		// Bitmap图片保存
		if (bitmap == null || path == null || path.equals("")) {
			return false;
		}
		BufferedOutputStream ostream = null;
		try {
			File file = new File(path);
			FileUtil.makeDIRAndCreateFile(path);
			ostream = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(CompressFormat.PNG, quality, ostream);
		} catch (FileNotFoundException e) {
			SLog.e(SLog.BIKE, e.toString());
			return false;
		} catch (Exception e) {
			SLog.e(SLog.BIKE, e.toString());
			return false;
		} finally {
			try {
				if (ostream != null) {
					ostream.flush();
					ostream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				SLog.e(SLog.BIKE, e.toString());
				return false;
			}
		}
		return true;
	}

	/*
	 * 保存图片到文件地址，并且设置图片质量（高、中、低）
	 */
	public static void saveBitmapPng(Bitmap bitmap, String path) {
		if (bitmap == null || path == null || path.equals("")) {
			return;
		}
		if (!MobileUtil.isSDCardExists()) {
			return;
		}
		// Bitmap图片保存
		File file = new File(path);
		OutputStream ostream = null;
		try {
			ostream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.PNG, 100, ostream);
		} catch (Exception e) {
			SLog.e(SLog.BIKE, e.toString());
		} finally {
			try {
				if (ostream != null) {
					ostream.flush();
					ostream.close();
				}
			} catch (IOException e) {
				SLog.e(SLog.BIKE, e.toString());
			}
		}
	}

	/*
	 * 旋转图片,90度的倍数
	 */
	public static Bitmap rotateBitmap(Bitmap srcBitmap, int rotateTime) {
		int sw = srcBitmap.getWidth();
		int sh = srcBitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.setRotate(90 * rotateTime, sw / 2.0f, sh / 2.0f);
		return Bitmap.createBitmap(srcBitmap, 0, 0, sw, sh, matrix, true);
	}

	public static float getImageScaleDensity(Context context) {
		if (scaleDensity == 0) {
			DisplayMetrics metric = MobileUtil.getDeviceDisplayMetrics(context);
			int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
			if (densityDpi >= 240) {
				densityDpi = 240;
			}
			scaleDensity = densityDpi / 240.0f;
		}
		return scaleDensity;
	}

	public static Bitmap FromFileToBitmap(String mPath) {
		return FromFileToBitmap(mPath, Bitmap.Config.RGB_565);
	}

	public static Bitmap FromFileToBitmap(String mPath, Config config) {
		Bitmap bitmap = null;
		File fileTmp = new File(mPath);
		if (fileTmp.exists()) {
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 1;
				options.inJustDecodeBounds = false;
				options.inDither = false;
				options.inPreferredConfig = config;
				bitmap = BitmapFactory.decodeFile(mPath, options);
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
				return null;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			}
		}
		return bitmap;
	}

	public static Bitmap FromFileToBitmap(String mPath, int maxLength) {
		Bitmap bitmap = null;
		File fileTmp = new File(mPath);
		if (fileTmp.exists()) {
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 1;
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(mPath, options);

				options.inSampleSize = computeSampleSize(options, maxLength);
				options.inJustDecodeBounds = false;
				options.inDither = false;
				options.inPreferredConfig = Bitmap.Config.RGB_565;
				bitmap = BitmapFactory.decodeFile(mPath, options);
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
				return null;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			}
		}
		return bitmap;
	}

	public static Bitmap FromResToBitmap(Resources res, int id, Config config) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = config;
			bitmap = BitmapFactory.decodeResource(res, id, options);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	public static Bitmap FromResToBitmap(Resources res, int id) {
		return FromResToBitmap(res, id, Bitmap.Config.ARGB_8888);
	}

	public static Bitmap FromResToBitmap(Resources res, int id, int maxLength) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(res, id, options);

			options.inSampleSize = computeSampleSize(options, maxLength);
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap = BitmapFactory.decodeResource(res, id, options);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	public static Bitmap FromByteToBitmap(byte[] response) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap = BitmapFactory.decodeByteArray(response, 0, response.length, options);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	public static Bitmap FromByteToBitmap(byte[] response, int maxLength) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(response, 0, response.length, options);

			options.inSampleSize = computeSampleSize(options, maxLength);
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap = BitmapFactory.decodeByteArray(response, 0, response.length, options);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	public static Bitmap FromStreamToBitmap(InputStream is, Config config) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = config;
			bitmap = BitmapFactory.decodeStream(is, null, options);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	public static Bitmap FromStreamToBitmap(InputStream is) {
		return FromStreamToBitmap(is, Bitmap.Config.RGB_565);
	}

	public static Bitmap FromStreamToBitmap(InputStream is, Rect rect, int maxLength) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is, rect, options);

			options.inSampleSize = computeSampleSize(options, maxLength);
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap = BitmapFactory.decodeStream(is, rect, options);
			;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}
	
	public static void recycleBitmap(Bitmap bm) {
		if (bm != null && !bm.isRecycled()) {
			bm.recycle();
			bm = null;
		}
	}

	/**
	 * 将图片转换成base64编码
	 * @param image
	 * @auther wangzhishou@qq.com
	 * @return
	 */
	public static String encodeTobase64(Bitmap image) {
	    Bitmap immagex=image;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    byte[] b = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
	    Log.e("LOOK", imageEncoded);
	    return imageEncoded;
	}
	
	/**
	 * 将base64编码的图片转化成图片
	 * @param input <String> base64编码字符串
	 * @auther wangzhishou@qq.com
	 * @return
	 */
	public static Bitmap decodeBase64(String input) {
	    byte[] decodedByte = Base64.decode(input, 0);
	    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
	}
}
