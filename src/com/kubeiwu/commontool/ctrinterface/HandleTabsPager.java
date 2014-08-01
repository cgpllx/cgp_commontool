package com.kubeiwu.commontool.ctrinterface;

import android.widget.TabHost;

import com.kubeiwu.commontool.adapter.TabsPagerAdapter;
/**
 * @author 耳东    www.kubeiwu.com
 *
 */
public interface HandleTabsPager extends HandlePublic{
	/**
	 * eg:mTabsAdapter.addTab(mTabHost.newTabSpec("名称").setIndicator(getTabItemView(0)), MyDownloadFragment.class, null);
	 */
	void addTab(TabsPagerAdapter mTabsAdapter, TabHost mTabHost);
}
