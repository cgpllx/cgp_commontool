package com.kubeiwu.commontool.ctrinterface;

import android.support.v4.app.FragmentTabHost;
/**
 * @author 耳东    www.kubeiwu.com
 *
 */
public interface HandleTabs extends HandlePublic{
	/**
	 * eg:mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator(getTabItemView(0)), IndexFragment.class, null);
	 */
	void addTab(FragmentTabHost mFragmentTabHost);
}
