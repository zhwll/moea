package com.xfactor.moea.main.controller.menu;

import static  com.xfactor.moea.main.common.StageManager.getStage;
import java.io.*;

import org.springframework.stereotype.Controller;

import com.xfactor.moea.main.common.StageManager;
import com.xfactor.moea.main.common.WorkSpaceManager;
import com.xfactor.moea.main.controller.BaseFXMLController;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.*;
import javafx.stage.Screen;

@Controller
public class MenuController extends BaseFXMLController{

	// 声明按钮控件
	@FXML
	private HBox btnWsProject;
	@FXML
	private HBox btnWsModelling;
	@FXML
	private HBox btnWsOptimization;
	@FXML
	private HBox btnWsDataAnalysis;
	@FXML
	private HBox btnWsOptions;
	@FXML
	private HBox btnWsHelp;
	@FXML
	private Button btnWindowMinimize;
	@FXML
	private Button btnWindowMaximize;
	@FXML
	private Button btnWindowClose;
	@FXML
	private Button btnWindowResize;

	// 工作区窗口, 注意这个Pane用来动态加载右侧的工作区
	@FXML
	private AnchorPane wsPane;

	// 用户窗口尺寸的管理
	private Rectangle2D rectangle2D;
	private double width;
	private double height;

	/**
	 * FXML文件载入后会立即调用该方法
	 */
	public void initialize() {

		// 这一步很关键，指定工作区的位置
		// 注意所有的工作区都在这个面板上动态切换
		StageManager.setWsContainer(wsPane);

		rectangle2D = Screen.getPrimary().getBounds();
		width = 0.1;
		height = 0.1;

		/**
		 * 窗口按钮: 最大 & 最小 & 恢复
		 */
		btnWindowMinimize.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
		btnWindowMinimize.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
		btnWindowMaximize.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
		btnWindowMaximize.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
		btnWindowClose.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
		btnWindowClose.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));

		// 项目菜单，设置鼠标形状和单击事件
		btnWsProject.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
		btnWsProject.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
		btnWsProject.setOnMouseClicked(e -> {
			try {
				WorkSpaceManager.setWorkSpace(WorkSpaceManager.WORKSPACE.WS_PROJECT);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// 建模 菜单，设置鼠标形状和单击事件
		btnWsModelling.setOnMouseClicked(e -> {
			try {
				WorkSpaceManager.setWorkSpace(WorkSpaceManager.WORKSPACE.WS_MODELLING);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// 优化 菜单，设置鼠标形状和单击事件
		btnWsOptimization.setOnMouseClicked(e -> {
			try {
				WorkSpaceManager.setWorkSpace(WorkSpaceManager.WORKSPACE.WS_OPTIMIZATION);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// 数据分析 菜单，设置鼠标形状和单击事件
		btnWsDataAnalysis.setOnMouseClicked(e -> {
			try {
				WorkSpaceManager.setWorkSpace(WorkSpaceManager.WORKSPACE.WS_DATA_ANALYSIS);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// 软件设置 菜单，设置鼠标形状和单击事件
		btnWsOptions.setOnMouseClicked(e -> {
			try {
				WorkSpaceManager.setWorkSpace(WorkSpaceManager.WORKSPACE.WS_OPTIONS);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// 帮助文档 菜单，设置鼠标形状和单击事件
		btnWsHelp.setOnMouseClicked(e -> {
			try {
				WorkSpaceManager.setWorkSpace(WorkSpaceManager.WORKSPACE.WS_HELP);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	/**
	 * 最小化窗口 将该方法绑定到 fx:btn_minimize
	 */
	@FXML
	public void modeMinimize() {
		if (StageManager.getStage().isMaximized()) {
			width = rectangle2D.getWidth();
			height = rectangle2D.getHeight();
			StageManager.getStage().setMaximized(false);
			StageManager.getStage().setHeight(height);
			StageManager.getStage().setWidth(width);
			StageManager.getStage().centerOnScreen();
			Platform.runLater(() -> {
				StageManager.getStage().setIconified(true);
			});
		} else {
			StageManager.getStage().setIconified(true);
		}
	}

	/**
	 * 最大化窗口 将该方法绑定到 fx:btn_maximize
	 */
	@FXML
	public void modeMaximize() {
		Stage stage = StageManager.getStage();
		if (!StageManager.getStage().isMaximized()) {
			System.out.println("最大化窗口...");
			stage.setMaximized(true);
			stage.centerOnScreen();
			btnWindowMaximize.getStyleClass().add("decoration-button-restore");
		} else {
			System.out.println("窗口已经在最大的状态!");
			stage.setMaximized(false);
			stage.setHeight(600);
			stage.setWidth(800);
			btnWindowMaximize.getStyleClass().remove("decoration-button-restore");
		}
	}

	/**
	 * 退出程序
	 */
	@FXML
	public void modeClose() {
		Platform.exit();
		System.exit(-1);
	}
	
}