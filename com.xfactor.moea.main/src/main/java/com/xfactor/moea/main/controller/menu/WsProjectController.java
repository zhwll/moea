package com.xfactor.moea.main.controller.menu;

import org.springframework.stereotype.Controller;

import com.xfactor.moea.main.common.PaneManager;
import com.xfactor.moea.main.controller.BaseFXMLController;

import javafx.fxml.*;
import javafx.scene.layout.*;

/**
 * "项目" 工作区的控制类
 * @author liu.yijun
 * @date 2020/09/02
 *
 */
@Controller
public class WsProjectController extends BaseFXMLController{

	// 声明按钮控件
	@FXML private HBox btnProjectRecently;
	@FXML private HBox btnProjectNew;
	@FXML private HBox btnProjectOpen;
	@FXML private HBox btnProjectTutorial;
	// 工作区窗口, 注意这个Pane用来动态加载右侧几个子页面，包括
	//  |____最近
	//  |____新建
	//  |____打开
	//  |____案例
	@FXML private AnchorPane wsProjectContainer;

	// FXML文件载入后会立即调用该方法
	public void initialize() {
		// 添加几个子页面到右侧的容器
		btnProjectRecently.setOnMouseClicked(e->{ PaneManager.addPane2Anchorpane(wsProjectContainer, "/views/wsProject/wsProjectRecently.fxml"); });
		btnProjectNew.setOnMouseClicked(e->{ PaneManager.addPane2Anchorpane(wsProjectContainer, "/views/wsProject/wsProjectNew.fxml"); });
		btnProjectOpen.setOnMouseClicked(e->{ PaneManager.addPane2Anchorpane(wsProjectContainer, "/views/wsProject/wsProjectOpen.fxml"); });
		btnProjectTutorial.setOnMouseClicked(e->{ PaneManager.addPane2Anchorpane(wsProjectContainer, "/views/wsProject/wsProjectTutorials.fxml"); });
	}
}