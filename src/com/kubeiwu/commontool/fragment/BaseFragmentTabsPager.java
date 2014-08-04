package com.kubeiwu.commontool.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.kubeiwu.commontool.R;
import com.kubeiwu.commontool.adapter.TabsPagerAdapter;
import com.kubeiwu.commontool.ctrinterface.HandleTabsPager;
import com.kubeiwu.commontool.view.ViewFactory;
/**
 * @author 耳东    www.kubeiwu.com
 *
 */
public abstract class BaseFragmentTabsPager extends Fragment {
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private TabsPagerAdapter mTabsAdapter;
	private HandleTabsPager mHandleTabsPager;
	private int gravity = Gravity.BOTTOM;

	/**
	 * 子类必须super.onCreateView之前调用
	 * @param mHandleTabsPager
	 */
	protected void initTabs(HandleTabsPager mHandleTabsPager) {
		this.mHandleTabsPager = mHandleTabsPager;
	};

	/**
	 * @param mHandleTabs
	 * @param gravity   tabs的位置，上面Gravity.TOP 下面Gravity.BOTTOM，目前只支持两个 
	 */
	protected void initTabs(HandleTabsPager mHandleTabsPager, int gravity) {
		this.mHandleTabsPager = mHandleTabsPager;
		this.gravity = gravity;
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mTabHost = ViewFactory.getTabHostAndPagerView(inflater.getContext(), gravity);
		mTabHost.setup();
		mViewPager = (ViewPager) mTabHost.findViewById(R.id.pager);
		mTabsAdapter = new TabsPagerAdapter(this, mTabHost, mViewPager);
		if (mHandleTabsPager != null) {
			mHandleTabsPager.addTab(mTabsAdapter, mTabHost);
			for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
				mHandleTabsPager.setTabWidgetChildViewBackground(mTabHost.getTabWidget().getChildAt(i));
			}
			mHandleTabsPager.setTabWidgetBackground(mTabHost.getTabWidget());
		}
		return mTabHost;
	}

}
