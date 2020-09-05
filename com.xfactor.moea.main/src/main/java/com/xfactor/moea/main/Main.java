package com.xfactor.moea.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.hsqldb.server.Server;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.xfactor.moea.base.BasicUtils;
import com.xfactor.moea.main.common.StageManager;
import com.xfactor.moea.main.exception.MoeaException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage primaryStage) {

		// 显示启动页,我们这里先不加载spring，但是要加载首页的资源文件，所以判断一下是否有外部文件

		try {
			File uipropFile = new File(BasicUtils.getRootPath() + "/config/i18n/ui.properties");
			ResourceBundle bundle = null;
			if (uipropFile.exists()) {
				BasicUtils.getLogger(this.getClass()).info("UI 外部配置存在，使用外部配置！");
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(uipropFile));
				bundle = new PropertyResourceBundle(inputStream);
				inputStream.close();
			} else {
				BasicUtils.getLogger(this.getClass()).info("UI 外部配置不存在，使用内部配置！");
				bundle = ResourceBundle.getBundle("i18n/ui");
			}
			primaryStage.setTitle(bundle.getString("splash.title"));
			//格式化版权 禁止外部修改
			String copyright = bundle.getString("splash.copyright");
			if(copyright.indexOf("{0}")<0){
				throw new MoeaException("版权文字不符合规则！");
			}
			MainContext.COPYRIGHT = MessageFormat.format(copyright, "@ 2020 XFatcor Moea Liu");
			primaryStage.initStyle(StageStyle.UNDECORATED);

			Parent root = MainContext.loadFXML("/views/splash.fxml", bundle);
			Scene s = new Scene(root);
			primaryStage.setScene(s);
			primaryStage.show();
		} catch (Exception e1) {
			// TODO Auto-generated catch block

			BasicUtils.getLogger().error("系统启动失败：", e1);
			Platform.exit();
			System.exit(-1);
			return;

		}

		// 开一个新的任务去执行，以免卡住splash
		Task<?> task = new Task() {
			@Override
			protected Object call() throws Exception {
				initDatas();
				return true;
			}
		};
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				BasicUtils.getLogger(this.getClass()).info("初始化成功完成，开始加载首页！");
				try {
					StageManager.showMainWorkSpace(primaryStage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				BasicUtils.getLogger(this.getClass()).info("初始化失败！");
			}

		});
		new Thread(task).start();

	}

	private void initDatas() {

		// 启动hsqldb
		BasicUtils.getLogger(this.getClass()).info("开始启动HSQLDB");
		Server server = new Server();// 它可是hsqldb.jar里面的类啊。
		server.setDatabaseName(0, "moea");
		server.setDatabasePath(0, BasicUtils.getRootPath() + "/config/db/moea");
		server.setPort(33667);
		server.setSilent(true);
		server.start();

		BasicUtils.getLogger(this.getClass()).info("HSQLDB已经启动，开始加载配置文件");
		String xmlpath = BasicUtils.getRootPath() + "/config/applicationconfig.xml";
		// 这里的设置可以动态使用，如果有外部配置则使用外部配置，没有则使用默认配置
		if (!new File(xmlpath).exists()) {
			BasicUtils.getLogger().info("外部配置文件不存在,使用默认配置！");
			MainContext.setApplicationContext(new ClassPathXmlApplicationContext("applicationconfig.xml"));
		} else {
			BasicUtils.getLogger().info("发现外部配置文件，使用外部配置文件！" + xmlpath);
			MainContext.setApplicationContext(new FileSystemXmlApplicationContext("config/applicationconfig.xml"));
		}
	}

}
