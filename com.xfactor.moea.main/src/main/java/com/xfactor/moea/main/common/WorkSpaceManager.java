package com.xfactor.moea.main.common;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

import com.xfactor.moea.base.BasicUtils;
import com.xfactor.moea.main.MainContext;

/**
 * 工作区管理器
 * 通过过该类实现主界面上几大工作区的切换
 * 
 * @author liu.yijun
 * @date 2020/09/03
 *
 */

public class WorkSpaceManager {

	// 所有的工作区在这里定义
	public static enum WORKSPACE {
		WS_PROJECT, // 项目工作区
		WS_MODELLING, // 流程建模工作区
		WS_OPTIMIZATION, // 优化仿真工作区
		WS_DATA_ANALYSIS, // 数据分析工作区
		WS_OPTIONS, // 软件设置工作区
		WS_HELP // 帮助&文档工作区
	}

	/**
	 * 默认构造函数 
	 */
	public WorkSpaceManager() {
	}

	/**
	 * 选择需要显示的工作区
	 * 
	 * @param screen
	 * @throws IOException
	 */
	public static void setWorkSpace(WORKSPACE ws) throws IOException {
		String fxmlFile = "";
		switch (ws) {
		case WS_PROJECT:
			fxmlFile = "/views/menu/wsProject.fxml";
			break;
		case WS_MODELLING:
			fxmlFile = "/views/menu/wsModelling.fxml";
			break;
		case WS_OPTIMIZATION:
			fxmlFile = "/views/menu/wsOptimization.fxml";
			break;
		case WS_DATA_ANALYSIS:
			fxmlFile = "/views/menu/wsDataAnalysis.fxml";
			break;
		case WS_OPTIONS:
			fxmlFile = "/views/menu/wsOptions.fxml";
			break;
		case WS_HELP:
			fxmlFile = "/views/menu/wsHelp.fxml";
			break;
		default:
			break;
		}
		// 通过调用 StageManager 的 setWsPane 方法实现工作区的加载
		StageManager.setWsPane(FXMLLoader.load(WorkSpaceManager.class.getResource(fxmlFile)));
		
		StageManager.setWsPane(MainContext.loadFXML(fxmlFile));
	}
}