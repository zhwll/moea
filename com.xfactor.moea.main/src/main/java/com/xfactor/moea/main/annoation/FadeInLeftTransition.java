package com.xfactor.moea.main.annoation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by Andreas on 6/14/16.
 */

public class FadeInLeftTransition extends ConfigAnimation {

    public FadeInLeftTransition(final Node node) {
        super(
                node,new Timeline(
                                new KeyFrame(Duration.millis(0),
                                        new KeyValue(node.opacityProperty(), 0, WEB_EASE),
                                        new KeyValue(node.translateXProperty(), -100, WEB_EASE)
                                ),
                                new KeyFrame(Duration.millis(700),
                                        new KeyValue(node.opacityProperty(), 1, WEB_EASE),
                                        new KeyValue(node.translateXProperty(), 0, WEB_EASE)
                                )
                        )
        );
        setCycleDuration(Duration.seconds(2));
        setDelay(Duration.seconds(0));
    }
}


