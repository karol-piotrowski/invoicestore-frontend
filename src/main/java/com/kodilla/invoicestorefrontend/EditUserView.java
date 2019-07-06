package com.kodilla.invoicestorefrontend;

import com.kodilla.invoicestorefrontend.domain.User;
import com.kodilla.invoicestorefrontend.service.UserService;
import com.kodilla.invoicestorefrontend.session.SessionVariables;
import com.vaadin.flow.component.button.Button;
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
    private Button users = new Button("Users");
    private Button accountants = new Button("Accountants");
    private Button companies = new Button("Companies");
    private TextField login = new TextField("Login");
    private TextField taxId = new TextField("Tax ID");
    private TextField firstname = new TextField("First name");
    private TextField lastname = new TextField("Last name");
    private TextField accountant = new TextField("Accountant");
    private Binder<User> binder = new Binder<>(User.class);


    public EditUserView() {
        binder.setBean(user);
        HorizontalLayout toolbar = new HorizontalLayout(users, accountants, companies);
        FormLayout mainContent = new FormLayout(login, taxId, firstname, lastname, accountant);
        mainContent.setSizeFull();
        add(toolbar, mainContent);
        users.addClickListener(event -> users());
        binder.bindInstanceFields(this);

    }

    private void users() {
        users.getUI().ifPresent(ui -> ui.navigate(""));
    }
}
