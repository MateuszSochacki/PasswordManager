package com.manager.controller;

import com.manager.PasswordManager;
import com.manager.config.BootInitializable;
import com.manager.model.GlobalPasswordEntity;
import com.manager.services.ChangeMainPassword;
import com.manager.repository.GlobalRepository;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Autowired
    private ChangeMainPassword changeMainPassword;

    @Autowired
    private GlobalRepository globalRepository;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnLogin;

    @FXML
    private Text textFailed;

    @FXML
    private PasswordField textFieldOldPassword;

    @FXML
    private PasswordField textFieldNewPassword;
    @FXML
    private Text textFailedToChange;

    @FXML
    void btnLoginClicked(MouseEvent event) throws NullPointerException{
        try {
            if (passwordField.getText().equals(globalRepository.findByGlobalpassword(passwordField.getText()).getGlobalpassword())) {
                pageController.setPage(PasswordManager.pagePasswordMenager);
            }
        }catch (Exception e)
        {
            textFailed.setText("Błędne hasło");
        }
    }
    @FXML
    void changePassword(MouseEvent event) {
        try {
            if (textFieldOldPassword.getText().equals(globalRepository.findByGlobalpassword(textFieldOldPassword.getText()).getGlobalpassword())) {
                GlobalPasswordEntity globalPasswordEntity = new GlobalPasswordEntity();
                globalPasswordEntity.setIdglobal(1);
                globalPasswordEntity.setGlobalpassword(textFieldNewPassword.getText());
                changeMainPassword.saveAndFlush(globalPasswordEntity);
                textFieldOldPassword.setText("");
                textFieldNewPassword.setText("");
                passwordField.setText("");
                textFailedToChange.setText("Pomyslnie zmieniono haslo");
            }
        }catch (Exception e)
        {
            textFailedToChange.setText("Błędne hasło");
        }
    }

    @Override
    public void initConstruct() {

    }

    @Override
    public void setPageParrent(PageController parentPage) {
        pageController = parentPage;

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
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }
}
