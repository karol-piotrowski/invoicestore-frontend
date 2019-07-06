package com.kodilla.invoicestorefrontend;

import com.kodilla.invoicestorefrontend.domain.User;
import com.kodilla.invoicestorefrontend.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private UserService userService = UserService.getInstance();
    private Grid<User> grid = new Grid<>(User.class);
    private TextField filter = new TextField();
    private Button addNewUser = new Button("Add new user");
    private Button accountants = new Button("Accountants");
    private Button companies = new Button("Companies");

    private UserForm form = new UserForm(this);

    public MainView() {
        filter.setPlaceholder("Filter by partial name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("login", "taxId", "firstname", "lastname");

        addNewUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addNewUser.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setUser(new User());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewUser, accountants, companies);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setUser(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(e -> form.setUser(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(userService.getUsers());

    }

    public void update() {
        grid.setItems(userService.findByLoginOrFirstnameOrLastName(filter.getValue()));


    }



}
