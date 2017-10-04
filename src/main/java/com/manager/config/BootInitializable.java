package com.manager.config;

import com.manager.controller.PageController;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContextAware;

public interface BootInitializable extends Initializable, ApplicationContextAware {

    void initConstruct();
    void setPageParrent(PageController parentPage);

    void stage(Stage primaryStage);

    Node initView();
}
