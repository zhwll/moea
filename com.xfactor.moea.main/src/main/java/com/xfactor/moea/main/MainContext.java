package com.xfactor.moea.main;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.persistence.Basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.xfactor.moea.base.BasicUtils;
import com.xfactor.moea.main.common.WorkSpaceManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

@Component
public class MainContext {
	
	public static String COPYRIGHT="@ 2020 Liu";
	
	private static ApplicationContext applicationContext;
	private static Stage primaryStage;
	public static ResourceBundle resourceBundle;
	
	
	@Autowired
	public void setResourceBundle(ResourceBundle i18nResourceBundle) {
		MainContext.resourceBundle = i18nResourceBundle;
	}
	
	
    public static Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void setPrimaryStage(Stage primaryStage) {
		MainContext.primaryStage = primaryStage;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static void setApplicationContext(ApplicationContext applicationContext) {
		MainContext.applicationContext = applicationContext;
	}


	public static <T> T loadFXML(String fxmlPath){
		// TODO Auto-generated method stub
		try {
			return FXMLLoader.load(MainContext.class.getResource(fxmlPath),MainContext.resourceBundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			BasicUtils.getLogger().error("没有找到FXML文件："+fxmlPath);
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T loadFXML(String fxmlPath,ResourceBundle resourceBundle){
		// TODO Auto-generated method stub
		try {
			return FXMLLoader.load(MainContext.class.getResource(fxmlPath),resourceBundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			BasicUtils.getLogger().error("没有找到FXML文件："+fxmlPath);
			e.printStackTrace();
		}
		return null;
	}
}
