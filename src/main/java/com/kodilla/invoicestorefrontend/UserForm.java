package com.kodilla.invoicestorefrontend;

import com.kodilla.invoicestorefrontend.domain.User;
import com.kodilla.invoicestorefrontend.service.UserService;
import com.kodilla.invoicestorefrontend.session.SessionVariables;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends VerticalLayout {
    private SessionVariables sessionVariables = SessionVariables.getInstance();
    private TextField login = new TextField("Login");
    private TextField taxId = new TextField("Tax ID");
    private TextField firstname = new TextField("First name");
    private TextField lastname = new TextField("Last name");
    private Binder<User> binder = new Binder<>(User.class);
    private UserService service = UserService.getInstance();
    private Button viewInvoices = new Button("View Invoices");
    private Button edit = new Button("Edit");
    private Button delete = new Button("Delete");
    private MainView mainView;

    public UserForm(MainView mv) {
        mainView = mv;
        login.setEnabled(false);
        taxId.setEnabled(false);
        firstname.setEnabled(false);
        lastname.setEnabled(false);

        HorizontalLayout saveAndDeleteButtons = new HorizontalLayout(edit, delete);
        viewInvoices.addThemeVariants(ButtonVariant.LUMO_LARGE);
        viewInvoices.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(login, taxId, firstname, lastname, viewInvoices, saveAndDeleteButtons);
        binder.bindInstanceFields(this);
        viewInvoices.addClickListener(event -> viewInvoices());
        edit.addClickListener(event -> edit());
        delete.addClickListener(event -> delete());
    }

    private void edit() {
        User user = binder.getBean();
        sessionVariables.setCurrentUser(user);
        edit.getUI().ifPresent(ui -> ui.navigate("edituser"));
    }

    private void viewInvoices() {
        User user = binder.getBean();
    }

    private void save() {
        User user = binder.getBean();
        service.saveUser(user);
        mainView.refresh();
        setUser(null);
    }

    private void delete() {
        User user = binder.getBean();
        service.deleteUser(user);
        mainView.refresh();
        setUser(null);
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
