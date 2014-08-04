package com.kubeiwu.commontool.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kubeiwu.commontool.R;
import com.kubeiwu.commontool.ctrinterface.HandleTabs;
import com.kubeiwu.commontool.view.ViewFactory;
/**
 * @author 耳东    www.kubeiwu.com
 *
 */
public class BaseFragmentTabs extends Fragment {
	private FragmentTabHost mTabHost;
	private HandleTabs mHandleTabs;
	private int gravity = Gravity.BOTTOM;

	protected void initTabs(HandleTabs mHandleTabs) {
		this.mHandleTabs = mHandleTabs;
	} 

	/**
	 * @param mHandleTabs
	 * @param gravity   tabs的位置，上面Gravity.TOP 下面Gravity.BOTTOM，目前只支持两个 
	 */
	protected void initTabs(HandleTabs mHandleTabs, int gravity) {
		this.mHandleTabs = mHandleTabs;
		this.gravity = gravity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mTabHost = ViewFactory.getFragmentTabHostView(inflater.getContext(), gravity);
		mTabHost.setup(inflater.getContext(), getChildFragmentManager(), R.id.realtabcontent);
		if (mHandleTabs != null) {
			mHandleTabs.addTab(mTabHost);
			for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
				mHandleTabs.setTabWidgetChildViewBackground(mTabHost.getTabWidget().getChildAt(i));
			}
			mHandleTabs.setTabWidgetBackground(mTabHost.getTabWidget());
		}
		return mTabHost;
	}

}
