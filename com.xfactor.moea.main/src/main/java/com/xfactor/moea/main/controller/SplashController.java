package com.xfactor.moea.main.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import java.io.IOException;

import com.xfactor.moea.main.MainContext;
import com.xfactor.moea.main.annoation.FadeInLeftTransition;
import com.xfactor.moea.main.annoation.FadeInRightTransition;
import com.xfactor.moea.main.annoation.FadeInTransition;

/**
 * 
 * @author zhwioio
 *
 */
public class SplashController {

	public static boolean isFinish=false;
	
	
    @FXML private Text TextWelcome;
    @FXML private Text TextCompany;
    @FXML private VBox PaneBottom;
    @FXML private Label LabelClose;
    @FXML private Text TextCopyright;

    public void initialize() {
    	
        LabelClose.setOnMouseEntered(e->MainContext.getPrimaryStage().getScene().setCursor(Cursor.HAND));
        LabelClose.setOnMouseExited(e->MainContext.getPrimaryStage().getScene().setCursor(Cursor.DEFAULT));
        TextCopyright.setText(MainContext.COPYRIGHT);
        LabelClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
                System.exit(-1);
            }
        });
        start();
    }
    private void start() {
    	SplashController.isFinish=false;
        Platform.runLater(()->{
            new FadeInLeftTransition(TextWelcome).play();
            new FadeInRightTransition(TextCompany).play();
            new FadeInTransition(PaneBottom).play();
        });
        
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                SplashController.isFinish=true;
                return null;
            }
        };
        new Thread(sleeper).start();
    }
}
