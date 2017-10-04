package com.manager.controller;

import com.manager.config.BootInitializable;
import com.manager.config.SecurityConfig;
import com.manager.model.PasswordEntity;
import com.manager.services.PasswordCreator;
import com.manager.repository.PasswordRepository;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PasswordManagerController implements BootInitializable {

    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private PasswordCreator passwordCreator;

    @Autowired
    private SecurityConfig securityConfig;

    private ApplicationContext springContext;
    private List<PasswordEntity> passwordEntityList;


    @FXML
    private ScrollPane scrollPane;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFlowLink;

    @FXML
    private TextField textFlowPassword;

    @FXML
    private Button btnAddPassword;

    @FXML
    void addNewPassword(MouseEvent event) {
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setName(textFieldName.getText());
        passwordEntity.setLink(textFlowLink.getText());
        passwordEntity.setPassword(textFlowPassword.getText());
        passwordCreator.saveAndFlush(passwordEntity);
        fillScrollPane();
    }

    @Override
    public void initConstruct() {
    }

    @Override
    public void setPageParrent(PageController parentPage) {

    }

    @Override
    public void stage(Stage primaryStage) {

    }

    @Override
    public Node initView() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillScrollPane();
    }

    void fillScrollPane()
    {
        Accordion accordion = new Accordion();
        passwordEntityList= passwordRepository.findAll();
        for (PasswordEntity x:passwordEntityList) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(x.getName());
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            hBox.setPrefHeight(60);
            hBox.setPadding(new Insets(12,12,12,12));
            hBox.setSpacing(16);
            hBox.setAlignment(Pos.CENTER);
            Text textNazwa = new Text("Nazwa:");
            TextField textFieldNazwa = new TextField(x.getName());


            Text textLink = new Text("Link:");
            TextField textFieldLink = new TextField(x.getLink());

            Text textPass = new Text("Haslo:");
            PasswordField passwordField = new PasswordField();
            passwordField.setText(x.getPassword());

            TextField textField = new TextField();
            textField.setManaged(false);
            textField.setVisible(false);

            CheckBox chkBox = new CheckBox();
            chkBox.setText("pokaż hasło");

            textField.managedProperty().bind(chkBox.selectedProperty());
            textField.visibleProperty().bind(chkBox.selectedProperty());

            passwordField.managedProperty().bind(chkBox.selectedProperty().not());
            passwordField.visibleProperty().bind(chkBox.selectedProperty().not());

            textField.textProperty().bindBidirectional(passwordField.textProperty());

            hBox.getChildren().addAll(textNazwa,textFieldNazwa,textLink,textFieldLink,textPass,passwordField,textField);

            HBox hBoxSecondRow = new HBox();
            Button btnEdit = new Button();
            btnEdit.setText("Zapisz zmiany");
            vBox.setAlignment(Pos.CENTER);
            Button btnEncode = new Button();
            btnEncode.setText("Szyrfuj Hasło");
            hBoxSecondRow.setAlignment(Pos.CENTER);
            Button btnDelete = new Button();
            btnDelete.setText("Usun");
            hBoxSecondRow.setAlignment(Pos.CENTER);
            hBoxSecondRow.setPadding(new Insets(12,12,12,12));

            hBoxSecondRow.setSpacing(8);
            hBoxSecondRow.getChildren().addAll(btnEncode, btnEdit, btnDelete, chkBox);

            vBox.getChildren().addAll(hBox,hBoxSecondRow);
            btnEncode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    passwordField.setText(securityConfig.encode(x.getPassword()));
                    x.setPassword(passwordField.getText());
                    passwordCreator.saveAndFlush(x);
                }
            });
            btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    passwordRepository.delete(x.getIdpassword());
                    fillScrollPane();
                }
            });
            btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //PasswordEntity passwordEntity = new PasswordEntity();
                    x.setName(textFieldNazwa.getText());
                    x.setLink(textFieldLink.getText());
                    x.setPassword(passwordField.getText());
                    passwordCreator.saveAndFlush(x);
                    fillScrollPane();
                }
            });
            titledPane.setContent(vBox);
            accordion.getPanes().add(titledPane);
            scrollPane.setContent(accordion);
        }
    }



}
