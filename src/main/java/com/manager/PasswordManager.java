package com.manager;

import com.manager.controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PasswordManager extends Application {


    private static String[] argument;
    private ApplicationContext springContext = null;

    public static final String pagePasswordMenager = "pagePasswordMenager";
    public static final String pagePasswordMenagerFile = "scene/ManagerPanel.fxml";

    public static final String pageWelcomePage = "pageWelcomePage";
    public static final String pageWelcomePageFile = "scene/WelcomePage.fxml";


    @Override
    public void start(final Stage primaryStage) throws Exception {
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                springContext = SpringApplication.run(PasswordManager.class, argument);
                return null;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            loadPaiges(primaryStage);
        });
        task.setOnFailed(e -> {
            System.exit(0);
            Platform.exit();
        });
        task.run();

    }

    public void loadPaiges(final Stage primaryStage) {


        PasswordManagerController passwordManagerController = springContext.getBean(PasswordManagerController.class);
        LoginController loginController = springContext.getBean(LoginController.class);

        PageController pageContainer = new PageController();

        pageContainer.loadPageWithContorller(PasswordManager.pagePasswordMenager, PasswordManager.pagePasswordMenagerFile, passwordManagerController);
        pageContainer.setPage(PasswordManager.pagePasswordMenager);
        pageContainer.loadPageWithContorller(PasswordManager.pageWelcomePage, PasswordManager.pageWelcomePageFile,loginController);
        pageContainer.setPage(PasswordManager.pageWelcomePage);
        loginController.stage(primaryStage);
        BorderPane root = new BorderPane();
        root.setCenter(pageContainer);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F12 && !primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(true);
            } else if (event.getCode() == KeyCode.F12 && primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(false);
            }
        });
        primaryStage.setMinHeight(0);
        primaryStage.setMinWidth(0);
        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menedżer haseł");
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        PasswordManager.argument = args;
        launch(args);
    }


}









