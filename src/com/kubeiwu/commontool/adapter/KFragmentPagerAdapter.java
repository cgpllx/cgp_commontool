package com.kubeiwu.commontool.adapter;

import java.util.HashMap;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class KFragmentPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> data;

	public KFragmentPagerAdapter(FragmentManager fm, List<Fragment> data) {
		super(fm);
		this.data = data;
	}

	@Override
	public Fragment getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public int getCount() {
		return data.size();
	}
	
}
