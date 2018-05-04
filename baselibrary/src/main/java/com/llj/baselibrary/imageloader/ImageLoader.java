package com.llj.baselibrary.imageloader;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/11 17:24
 *    描述：
 * </pre>
 */
public class ImageLoader {

	public static void loadImage(Context context, String url, ImageView imageView) {
		Glide.with(context).load(url).into(imageView);
	}

	public static void loadImageSkipMemory(Context context, String url, ImageView imageView) {
		Glide.with(context).load(url).skipMemoryCache(true).into(imageView);
	}

	public static void loadImageSkipDisk(Context context, String url, ImageView imageView) {
		Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
	}

	public static void loadImageSkipMemory(Context context, String url, @IntegerRes int placeHolder, @IntegerRes int error, ImageView imageView) {
		Glide.with(context).load(url).placeholder(placeHolder).error(error).skipMemoryCache(true).into(imageView);
	}

	public static void loadImageSkipDisk(Context context, String url, @IntegerRes int placeHolder, @IntegerRes int error, ImageView imageView) {
		Glide.with(context).load(url).placeholder(placeHolder).error(error).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
	}

}
