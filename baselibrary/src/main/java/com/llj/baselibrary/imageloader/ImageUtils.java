package com.llj.baselibrary.imageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by llj on 2017/2/6. 图片处理--->压缩
 */

public class ImageUtils {

  private ImageUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 将图片保存到本地时进行压缩, 即将图片从Bitmap形式变为File形式时进行压缩 File形式的图片确实被压缩了,
   * 但是当你重新读取压缩后的file为Bitmap时,它占用的内存并没有改变
   */
  public static void compressBmpToFile(Bitmap bmp, File file) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    FileOutputStream fos = null;
    int options = 80;
    bmp.compress(CompressFormat.JPEG, options, baos);
    while (baos.toByteArray().length / 1024 > 100) {
      //这里让文件压缩到100K以下
      baos.reset();
      options -= 10;
      bmp.compress(CompressFormat.JPEG, options, baos);
    }
    try {
      fos = new FileOutputStream(file);
      fos.write(baos.toByteArray());
      fos.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (baos != null) {
        try {
          baos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 计算BitmapFactory.Options中inSampleSize大小，确定压缩比例
   *
   * @param reqWidth 目标图片宽度
   * @param reqHeight 目标图片高度
   */
  public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                          int reqHeight) {
    //源图片的宽度
    int width = options.outWidth;
    //源图片的高度
    int height = options.outHeight;
    int inSampleSize = 1;
    if (width > reqWidth || height > reqHeight) {
      //计算出源图片和目标图片的宽高比例
      int heightRatio = Math.round((float) height / (float) reqHeight);
      int widthRatio = Math.round((float) width / (float) reqWidth);
      // 选择宽和高中最小的比例作为inSampleSize的值，这样可以保证最终图片的宽和高，一定都会大于等于目标的宽和高。
      inSampleSize = widthRatio < heightRatio ? widthRatio : heightRatio;
    }
    return inSampleSize;

  }

  /**
   * 从资源中加载图片
   *
   * @param res 图片资源
   * @param resId 图片资源id
   * @param resWidth 图片压缩后的宽度
   * @param resHeight 图片压缩后的高度
   */
  public static Bitmap decodeBitmapFromResource(Resources res, int resId, int resWidth,
                                                int resHeight) {
    // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    // 解析一次图片，这时候并没有不再是一个Bitmap对象，而是null。
    // 虽然Bitmap是null了，但是BitmapFactory.Options的outWidth、outHeight和outMimeType属性都会被赋值
    BitmapFactory.decodeResource(res, resId, options);
    //计算inSampleSize值
    options.inSampleSize = calculateInSampleSize(options, resWidth, resHeight);
    options.inPreferredConfig = Config.ARGB_8888;//该模式是默认的,可不设，使用RGB_565内存会减少很多
    /* 下面两个字段需要组合使用,当系统内存不够时候图片自动被回收*/
    options.inPurgeable = true;//5.0之后这两个属性废弃了。。。
    options.inInputShareable = true;
    //使用inSampleSize再次解析图片
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);

  }

  /**
   * 从文件中加载图片
   *
   * @param file File对象
   * @param resWidth 压缩后的图片宽度
   * @param resHeight 压缩后的图片高度
   */
  public static Bitmap decodeBitmapFromFile(File file, int resWidth,
                                            int resHeight) {
    if (file == null) {
      return null;
    }
    InputStream is = null;
    try {
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      is = new BufferedInputStream(new FileInputStream(file));
      BitmapFactory.decodeStream(is, null, options);
      options.inSampleSize = calculateInSampleSize(options, resWidth, resHeight);
      options.inJustDecodeBounds = false;
      return BitmapFactory.decodeStream(is, null, options);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    } finally {
      //关流
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * 从文件路径中加载图片
   *
   * @param filePath 图片的路径
   * @param resWidth 压缩后图片的宽度
   * @param resHeight 压缩后图片的高度
   */
  public static Bitmap decodeBitmapFromFilePath(String filePath, int resWidth,
                                                int resHeight) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(filePath, options);
    options.inSampleSize = calculateInSampleSize(options, resWidth, resHeight);
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(filePath, options);

  }

  /**
   * bitmap转byteArr
   */
  public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format) {
    if (bitmap == null) {
      return null;
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(format, 100, baos);
    return baos.toByteArray();
  }

  /**
   * byteArr转bitmap
   */
  public static Bitmap bytes2Bitmap(byte[] bytes) {
    return (bytes == null || bytes.length == 0) ? null
        : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

  }

  /**
   * drawable转bitmap
   *
   * @param drawable drawable对象
   * @return bitmap
   */
  public static Bitmap drawable2Bitmap(Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
      return ((BitmapDrawable) drawable).getBitmap();
    } else if (drawable instanceof NinePatchDrawable) {
      Bitmap bitmap = Bitmap.createBitmap(
          drawable.getIntrinsicWidth(),
          drawable.getIntrinsicHeight(),
          drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
              : Config.RGB_565);
      Canvas canvas = new Canvas(bitmap);
      drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
      drawable.draw(canvas);
      return bitmap;
    } else {
      return null;
    }
  }

  /**
   * bitmap转drawable
   *
   * @param res resources对象
   * @param bitmap bitmap对象
   * @return drawable
   */
  public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
    return bitmap == null ? null : new BitmapDrawable(res, bitmap);
  }

  /**
   * drawable转byteArr
   *
   * @param drawable drawable对象
   * @param format 格式
   * @return 字节数组
   */
  public static byte[] drawable2Bytes(Drawable drawable, CompressFormat format) {
    return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);
  }

  /**
   * byteArr转drawable
   *
   * @param res resources对象
   * @param bytes 字节数组
   * @return drawable
   */
  public static Drawable bytes2Drawable(Resources res, byte[] bytes) {
    return res == null ? null : bitmap2Drawable(res, bytes2Bitmap(bytes));
  }

  /**
   * Bitmap 转 String
   */
  public String bitmap2String(Bitmap bitmap) {
    String string = null;
    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
    bitmap.compress(CompressFormat.PNG, 100, bStream);
    byte[] bytes = bStream.toByteArray();
    string = Base64.encodeToString(bytes, Base64.DEFAULT);
    return string;
  }

  /**
   * String 转 Bitmap
   */
  public Bitmap stringtoBitmap(String string) {
    Bitmap bitmap = null;
    try {
      byte[] bitmapArray;
      bitmapArray = Base64.decode(string, Base64.DEFAULT);
      bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return bitmap;
  }


  /**
   * 缩放图片
   *
   * @param src 源图片
   * @param newWidth 新宽度
   * @param newHeight 新高度
   * @return 缩放后的图片
   */
  public static Bitmap scale(Bitmap src, int newWidth, int newHeight) {
    return scale(src, newWidth, newHeight);
  }

  /**
   * 缩放图片--->指定缩放后图片的宽度和高度
   *
   * @param bitmap 源图片
   * @param newWidth 新宽度
   * @param newHeight 新高度
   * @return 缩放后的图片
   */
  public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
    if (bitmap == null) {
      return null;
    }
    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
  }

  /**
   * 缩放图片--->按照比例缩放
   *
   * @param src 源图片
   * @param scaleWidth 缩放宽度倍数
   * @param scaleHeight 缩放高度倍数
   * @return 缩放后的图片
   */
  public static Bitmap scaleByPer(Bitmap src, float scaleWidth, float scaleHeight) {
    return scale(src, scaleWidth, scaleHeight);
  }

  /**
   * 缩放图片
   *
   * @param bitmap 源图片
   * @param scaleWidth 缩放宽度倍数
   * @param scaleHeight 缩放高度倍数
   * @return 缩放后的图片
   */
  public static Bitmap scale(Bitmap bitmap, float scaleWidth, float scaleHeight) {
    if (bitmap == null) {
      return null;
    }
    Matrix matrix = new Matrix();
    matrix.setScale(scaleWidth, scaleHeight);
    return Bitmap
        .createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
  }

  /**
   * 转为圆形图片
   *
   * @param src 源图片
   * @return 圆形图片
   */
  public static Bitmap toRoundBitmap(Bitmap src) {
    return toRound(src);
  }

  /**
   * 转为圆形图片
   *
   * @param src 源图片
   * @return 圆形图片
   */
  public static Bitmap toRound(Bitmap src) {
    if (src == null) {
      return null;
    }
    int width = src.getWidth();
    int height = src.getHeight();
    int radius = Math.min(width, height) >> 1;
    Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
    Paint paint = new Paint();
    Canvas canvas = new Canvas(ret);
    Rect rect = new Rect(0, 0, width, height);
    paint.setAntiAlias(true);
    canvas.drawARGB(0, 0, 0, 0);
    canvas.drawCircle(width >> 1, height >> 1, radius, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(src, rect, rect, paint);
    return ret;
  }

  /**
   * 转为圆角图片
   *
   * @param src 源图片
   * @param radius 圆角的度数
   * @return 圆角图片
   */
  public static Bitmap toRoundCornerBitmap(Bitmap src, float radius) {
    return toRoundCorner(src, radius);
  }

  /**
   * 转为圆角图片
   *
   * @param src 源图片
   * @param radius 圆角的度数
   * @return 圆角图片
   */
  public static Bitmap toRoundCorner(Bitmap src, float radius) {
    if (null == src) {
      return null;
    }
    int width = src.getWidth();
    int height = src.getHeight();
    Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
    Paint paint = new Paint();
    Canvas canvas = new Canvas(ret);
    Rect rect = new Rect(0, 0, width, height);
    paint.setAntiAlias(true);
    canvas.drawRoundRect(new RectF(rect), radius, radius, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(src, rect, rect, paint);
    return ret;
  }


}
