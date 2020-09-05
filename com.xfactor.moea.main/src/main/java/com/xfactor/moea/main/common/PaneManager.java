package com.xfactor.moea.main.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Command;
import org.springframework.stereotype.Component;

import com.xfactor.moea.main.MainContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;

/**
 * 这个类提供一系列静态方法，用于窗体的管理：
 * 
 * 1. addPane2Anchorpane : 向一个 Anchorpane 中添加子窗体  
 * 
 * @author liu.yijun
 * @date 2020/09/02
 *
 */


public class PaneManager {
	
	/**
	 * 向一个 Anchorpane 中添加一个Pane, 并保证自适应容器的边界 
	 * 
	 * @param container 容器
	 * @param child 待添加的 fxml 文件
	 * @throws IOException 
	 */
	public static void addPane2Anchorpane(AnchorPane container, String fxmlPath){
		// 将 FXML 文件转换成窗体
		Node childPane = MainContext.loadFXML(fxmlPath);
		// 让窗体自适应容器边界
		AnchorPane.setBottomAnchor(childPane,0.0);
		AnchorPane.setTopAnchor(childPane,0.0);
		AnchorPane.setLeftAnchor(childPane,0.0);
		AnchorPane.setRightAnchor(childPane,0.0);
		// 清空容器
		container.getChildren().clear();
		// 添加窗体
		container.getChildren().add(childPane);
	}
	
}