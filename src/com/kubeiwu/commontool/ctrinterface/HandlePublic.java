package com.kubeiwu.commontool.ctrinterface;

import android.view.View;
import android.widget.TabWidget;
/**
 * @author 耳东    www.kubeiwu.com
 *
 */
public interface HandlePublic {
	/**
	 * 设置Widget背景
	 * @param widgetview
	 */
	void setTabWidgetBackground(TabWidget widgetview);

	/**
	 * 设置Widget item的背景
	 * @param widgetview
	 */
	void setTabWidgetChildViewBackground(View widgetchildview);
}
