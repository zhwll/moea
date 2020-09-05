package com.xfactor.moea.main.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

import com.xfactor.moea.main.MainContext;

/**
 * 窗体管理器
 * 通过这个类实现软件的主界面和工作区的管理
 * 
 * @author liu.yijun
 *
 */
public class StageManager {
	//private static Stage stage;
	
	// 工作区窗体的容器，位于主界面右侧的区域 
	// 所有的工作区窗口都应该加在这个 pane 上, 使用下面的 setWsContainer 方法
	private static AnchorPane wsContainer;

	/**
	 * 
	 * @param stage 主舞台
	 * @throws IOException
	 */
	public static void showMainWorkSpace(Stage stage) throws IOException {
		// 这个 stage 作为唯一的舞台
		MainContext.setPrimaryStage(stage);
		
		Parent root = MainContext.loadFXML("/views/menu/menu.fxml");
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		// 添加拖动和缩放的功能
		// 注意只有在标题栏才支持拖动，四个边框和顶点都支持缩放
		new FXResizeHelper(stage, 20, 10);
		
		// 显示软件界面
		stage.toFront();
	}
	
	
	
	
	/**
	 * 设定工作区窗口的容器（在 menu.fxml 中声明） 
	 * @param container
	 */
	public static void setWsContainer(AnchorPane container) {
		StageManager.wsContainer = container;
	}

	/**
	 * 加载工作区窗口 
	 * @param root
	 */
	public static void setWsPane(Parent wsPane) {
		Node ws = (Node) wsPane;
		// 保证让工作区面板充满整个容器
		AnchorPane.setBottomAnchor(ws, 0.0);
		AnchorPane.setLeftAnchor(ws, 0.0);
		AnchorPane.setRightAnchor(ws, 0.0);
		AnchorPane.setTopAnchor(ws, 0.0);
		// 注意每次加载前要清空容器
		StageManager.wsContainer.getChildren().clear();
		StageManager.wsContainer.getChildren().add(ws);
	}

	/**
	 * 设定主界面的窗口
	 * 注意软件的主界面可能更改，比如启动时显示的 splash 界面和启动后的主界面，是两个不同的 root scene
	 * 因此需要这个方法
	 * @param root
	 */
	public static void setRootScene(Parent root) {
		MainContext.getPrimaryStage().getScene().setRoot(root);
	}

	/**
	 * 返回主舞台 
	 * @return
	 */
	public static Stage getStage() {
		return MainContext.getPrimaryStage();
	}
}