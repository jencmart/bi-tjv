/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.semestralka_03_client;



import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Typ;
import java.util.Optional;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */
public class TypForm extends FormLayout {

    Button save = new Button("Save", this::save);
    Button cancel = new Button("Cancel", this::cancel);
    
    TextField popis = new TextField("Popis");
    TextField lastName = new TextField("Last name");
    TextField phone = new TextField("Phone");
    TextField email = new TextField("Email");
    DateField birthDate = new DateField("Birth date");

    Typ typ;
    

    // Easily bind forms to beans and manage validation and buffering
    Binder<Typ> formFieldBindings;

    public TypForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        /*
         * Highlight primary actions.
         *
         * With Vaadin built-in styles you can highlight the primary save button
         * and give it a keyboard shortcut for a better UX.
         */
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(true);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

        addComponents(actions, popis);
    }


    // CREATE / UPDATE
    public void save(Button.ClickEvent event) {
            // Commit the fields from UI to DAO
            System.out.println(typ.getPopis());
            if(typ != null)
            {
            // Save DAO to backend with direct synchronous service API
            getUI().typClient.createOrUpdate(typ);

            String msg = String.format("Saved '%s'.", typ.getPopis());
            Notification.show(msg, Type.TRAY_NOTIFICATION);
            getUI().loadTypy();
            
            }
            else
            {
                System.out.println("NUUL");
            }
    }

    public void cancel(Button.ClickEvent event) {
        // Place to call business logic.
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        getUI().typTable.select(null);
    }

    void edit(Optional<Typ> t) {
        typ = getUI().typClient.select(t.get().getTypId());

        popis.setValue(typ.getPopis());
    }

    @Override
    public MyUI getUI() {
        return (MyUI) super.getUI();
    }

}
