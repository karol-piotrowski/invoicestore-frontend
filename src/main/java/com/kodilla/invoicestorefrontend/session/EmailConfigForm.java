package com.kodilla.invoicestorefrontend.session;

import com.kodilla.invoicestorefrontend.EditUserView;
import com.kodilla.invoicestorefrontend.domain.EmailConfig;
import com.kodilla.invoicestorefrontend.domain.EncryptionType;
import com.kodilla.invoicestorefrontend.domain.User;
import com.kodilla.invoicestorefrontend.service.EmailConfigService;
import com.kodilla.invoicestorefrontend.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class EmailConfigForm extends FormLayout {
    private SessionVariables sessionVariables = SessionVariables.getInstance();
    private EmailConfigService emailConfigService = EmailConfigService.getInstance();
    private UserService userService = UserService.getInstance();
    private EmailField emailAddress = new EmailField("Email address");
    private TextField smtpServer = new TextField("SMTP server");
    private NumberField smtpPort = new NumberField("SMTP port");
    private TextField username = new TextField("Username");
    private PasswordField password = new PasswordField("Password");
    private Checkbox isAuthReq = new Checkbox("Authentication required");
    private ComboBox<EncryptionType> encryptionType = new ComboBox<>("Encryption type");
    private Button save = new Button("Save Email Config");
    private Button delete = new Button("Delete Email Config");
    private Binder<EmailConfig> binder = new Binder<>(EmailConfig.class);
    private EditUserView editUserView;


    public EmailConfigForm(EditUserView ev) {
        editUserView = ev;
        encryptionType.setItems(EncryptionType.values());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(emailAddress, smtpServer, smtpPort, username, password, isAuthReq, encryptionType, buttons);
        binder.forField(smtpPort).withConverter(Double::intValue, Integer::doubleValue ).bind(EmailConfig::getSmtpPort, EmailConfig::setSmtpPort);
        binder.forField(isAuthReq).bind(EmailConfig::isAuthReq, EmailConfig::setAuthReq);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    public void delete() {
    }

    public void save() {
        EmailConfig emailConfig = binder.getBean();
        emailConfigService.saveEmailConfig(emailConfig);
        Long userId = sessionVariables.getCurrentUser().getUserId();
        sessionVariables.setCurrentUser(userService.getUser(userId));
        editUserView.refresh();
        setEmailConfig(null);
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        try{
            binder.setBean(emailConfig);
        } catch (NullPointerException e) {

        }
        if(emailConfig == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }

    }
}
