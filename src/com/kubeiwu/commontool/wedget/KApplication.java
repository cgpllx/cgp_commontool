package com.kubeiwu.commontool.wedget;

import com.kubeiwu.commontool.volley.cache.KRequestQueueManager;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class KApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		init(getApplicationContext());
	}

	private void init(Context context) {
		if (PreferenceManager.getDefaultSharedPreferences(context).getInt("screenHeight", -1) < 0) {
			WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			//			getResources();
			DisplayMetrics dm = new DisplayMetrics();
			windowManager.getDefaultDisplay().getMetrics(dm);
			PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("screenWidth", dm.widthPixels).commit();
			PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("screenHeight", dm.heightPixels).commit();
		}
		initImageLoder(context);
	}

	public void initImageLoder(Context context) {
		KRequestQueueManager.getInstance().init(context);

	}

	//	public static void initImageLoader(Context context) {
	//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)//
	//				.threadPriority(Thread.NORM_PRIORITY - 2)//
	//				.denyCacheImageMultipleSizesInMemory()//
	//				.discCacheFileNameGenerator(new Md5FileNameGenerator())//
	//				.tasksProcessingOrder(QueueProcessingType.LIFO)//
	//				.discCacheSize(1024 * 1024 * 1024).discCacheFileCount(3000)//
	//				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) //文件名的格式 （HashCode和md5）
	//				.build();
	//		ImageLoader.getInstance().init(config);
	//	}
}