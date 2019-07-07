package com.kodilla.invoicestorefrontend;

import com.kodilla.invoicestorefrontend.domain.EmailConfig;
import com.kodilla.invoicestorefrontend.domain.User;
import com.kodilla.invoicestorefrontend.service.EmailConfigService;
import com.kodilla.invoicestorefrontend.service.UserService;
import com.kodilla.invoicestorefrontend.session.EmailConfigForm;
import com.kodilla.invoicestorefrontend.session.SessionVariables;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("edituser")
public class EditUserView extends VerticalLayout {
    private SessionVariables sessionVariables = SessionVariables.getInstance();
    private User user = sessionVariables.getCurrentUser();
    private UserService userService = UserService.getInstance();
    private EmailConfigService emailConfigService = EmailConfigService.getInstance();
    private Button users = new Button("Users");
    private Button accountants = new Button("Accountants");
    private Button companies = new Button("Companies");
    private TextField login = new TextField("Login");
    private TextField taxId = new TextField("Tax ID");
    private TextField firstname = new TextField("First name");
    private TextField lastname = new TextField("Last name");
    private TextField accountant = new TextField("Accountant");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Grid<EmailConfig> grid = new Grid<>(EmailConfig.class);
    private EmailConfigForm form = new EmailConfigForm(this);
    private Binder<User> binder = new Binder<>(User.class);

    public EditUserView() {
        binder.setBean(user);
        grid.setColumns("emailAddress");
        HorizontalLayout toolbar = new HorizontalLayout(users, accountants, companies);
        FormLayout mainContent = new FormLayout(login, taxId, firstname, lastname, accountant);
        HorizontalLayout emailConfigs = new HorizontalLayout(grid, form);
        HorizontalLayout saveAndDeleteButtons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        mainContent.setSizeFull();
        grid.setSizeFull();
        grid.setVisible(user.getUserId()==null ? false : true);
        form.setSizeFull();
        emailConfigs.setSizeFull();
        add(toolbar, mainContent, emailConfigs, saveAndDeleteButtons);
        form.setEmailConfig(null);
        users.addClickListener(event -> users());
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setEmailConfig(grid.asSingleSelect().getValue()));
    }

    private void delete() {
        User user = binder.getBean();
        userService.deleteUser(user);
        delete.getUI().ifPresent(ui -> ui.navigate(""));
    }

    private void save() {
        User user = binder.getBean();
        if(user.getUserId()!=null) {
            user.setEmailConfigId(userService.getUser(user.getUserId()).getEmailConfigId());
        }
        userService.saveUser(user);
        delete.getUI().ifPresent(ui -> ui.navigate(""));
    }

    public void refresh() {
        user = sessionVariables.getCurrentUser();
        if (user.getEmailConfigId() == null) {
            grid.setItems(new EmailConfig(null, "Click here to add email configuration ...", null, 0, null, null, false, null, null));
        } else {
            grid.setItems(emailConfigService.getEmailConfig(user.getEmailConfigId()));
        }
    }

    public void users() {
        users.getUI().ifPresent(ui -> ui.navigate(""));
    }

    public Button getSave() {
        return save;
    }
}
