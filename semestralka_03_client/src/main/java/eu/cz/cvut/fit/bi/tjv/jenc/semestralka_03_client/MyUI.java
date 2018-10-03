package eu.cz.cvut.fit.bi.tjv.jenc.semestralka_03_client;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Typ;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Uzivatel;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Prispevek;
import java.util.Collection;
import java.util.List;

import java.util.Optional;

@Theme("mytheme")
public class MyUI extends UI {
    
    private final PrispevekClient prispevekClient;
    private final UzivatelClient uzivatelClient;
    public final TypClient typClient;
    
    private final Grid<Prispevek> prispevekTable;
    private final Grid<Uzivatel> uzivatelTable;
    public final Grid<Typ> typTable;
    
    public MyUI() {
        prispevekClient = new PrispevekClient();
        uzivatelClient = new UzivatelClient();
        typClient = new TypClient();
        
        prispevekTable = new Grid<>(Prispevek.class);
        prispevekTable.setWidth("100%");
        uzivatelTable = new Grid<>(Uzivatel.class);
        uzivatelTable.setWidth("100%");
        typTable = new Grid<>(Typ.class);
        typTable.setWidth("100%");
    }
    
///*****************************************************************************    
///******************************* T Y P Y *************************************
///*****************************************************************************
    void loadTypy() {
       typTable.setItems( typClient.selectAll());
    }
    
    void loadTypyByName(Grid<Typ> typTable, String s ){
          typTable.setItems(typClient.selectConstrain(s));
    }
    
    private void initTypy(Layout layout) {   
        typTable.removeColumn("typId");
        loadTypy();
        
        Label typHeader = new Label("TYP");
        
        /// CRUD formular
        FormLayout crudFormular = new FormLayout();
        Button createNew = new Button("Create New");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        create.setStyleName(ValoTheme.BUTTON_PRIMARY);
        delete.setStyleName(ValoTheme.BUTTON_DANGER);
        createNew.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        create.setIcon(FontAwesome.CHECK);
        update.setIcon(FontAwesome.EDIT);
        delete.setIcon(FontAwesome.REMOVE);
        
        TextField popis = new TextField("Popis");
        HorizontalLayout actions = new HorizontalLayout(createNew,create, update, delete);
        actions.setSpacing(true);
        crudFormular.addComponents(actions, popis);
     
        /// SELECT ROW
        typTable.addSelectionListener(event -> {
            Optional<Typ> optTyp = typTable.getSelectionModel().getFirstSelectedItem();
            if(optTyp.isPresent())
            {
                Typ typ = typClient.select(optTyp.get().getTypId());
                popis.setValue(typ.getPopis());
                create.setVisible(false);
                createNew.setVisible(true);
                update.setVisible(true);
                delete.setVisible(true);
            }
        });
        
        /// CREATE NEW - OPTION
        createNew.addClickListener((Button.ClickEvent event) -> {
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                popis.clear();
                typTable.deselectAll();
        });
        
        /// CREATE      
        create.addClickListener((Button.ClickEvent event) -> {
            if (! popis.isEmpty()) 
            {
                Typ typ = new Typ();
                typ.setPopis(popis.getValue());
                typClient.createOrUpdate(typ);
                loadTypy();
                popis.clear();
            }
        });
        
        ///UPDATE
        update.addClickListener((Button.ClickEvent event) -> {
            Optional<Typ> typ = typTable.getSelectionModel().getFirstSelectedItem();
            if (typ.isPresent() && !popis.isEmpty()) {
                Typ b = typClient.select(typ.get().getTypId());
                b.setPopis(popis.getValue());
                typClient.createOrUpdate(b);
                loadTypy();
            }
        });
        
        // DELETE
        delete.addClickListener((Button.ClickEvent event) -> {
            Optional<Typ> typ = typTable.getSelectionModel().getFirstSelectedItem();
            if (typ.isPresent() && !popis.isEmpty()) {
                Typ b = typClient.select(typ.get().getTypId()); 
                typClient.remove(b.getTypId());
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                popis.clear();
                typTable.deselectAll();
                loadTypy();
            }
        });
       
        // FILTR FIELD
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        filterButton.addClickListener((Button.ClickEvent event) -> {
            if (filterField.isEmpty())
                loadTypy();
            else
                loadTypyByName(typTable, filterField.getValue());
        });
   
        HorizontalLayout filter = new HorizontalLayout();
        filter.addComponents(filterField, filterButton);
        
        HorizontalLayout tabulkaAndCRUD = new HorizontalLayout();
        tabulkaAndCRUD.addComponents(typTable, crudFormular);
        layout.addComponents(tabulkaAndCRUD);
        layout.addComponents(typHeader, filter, tabulkaAndCRUD);
    }
    
    
    
    
    
///*****************************************************************************    
///***************************** U Z I V A T E L E *****************************
///***************************************************************************** 
    private void loadUzivatele(Grid<Uzivatel> uzivatelTable) {
        uzivatelTable.setItems(uzivatelClient.selectAll());
    }
    
    void loadUzivateleByName(Grid<Uzivatel> uzivatelTable, String s ){
          uzivatelTable.setItems(uzivatelClient.selectConstrain(s));
    }
    
    private void initUzivatele(Layout layout) 
    {
        uzivatelTable.removeColumn("uzivatelId");
        uzivatelTable.removeColumn("typId");
        loadTypy();
        
        Label typHeader = new Label("UZIVATELE");
        
        /// CRUD formular
        FormLayout crudFormular = new FormLayout();
        Button createNew = new Button("Create New");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        create.setStyleName(ValoTheme.BUTTON_PRIMARY);
        delete.setStyleName(ValoTheme.BUTTON_DANGER);
        createNew.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        create.setIcon(FontAwesome.CHECK);
        update.setIcon(FontAwesome.EDIT);
        delete.setIcon(FontAwesome.REMOVE);
        
        TextField jmeno = new TextField("Jmeno");
        TextField nick = new TextField("Nick");
        TextField email = new TextField("E-mail");

        ComboBox<Typ> role = new ComboBox<>("Vyber Roli");
        role.setItems(typClient.selectAll());
        role.setItemCaptionGenerator(Typ::getPopis);

        HorizontalLayout actions = new HorizontalLayout(createNew,create, update, delete);
        actions.setSpacing(true);
        crudFormular.addComponents(actions, jmeno, nick, email, role);
     
        
        
        /// SELECT ROW
        uzivatelTable.addSelectionListener(event -> {
            Optional<Uzivatel> optTyp = typTable.getSelectionModel().getFirstSelectedItem();
            if(optTyp.isPresent())
            {
                Typ typ = typClient.select(optTyp.get().getTypId());
                jmeno.setValue(typ.getPopis());
                create.setVisible(false);
                createNew.setVisible(true);
                update.setVisible(true);
                delete.setVisible(true);
            }
        });
        
        /// CREATE NEW - OPTION
        createNew.addClickListener((Button.ClickEvent event) -> {
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                popis.clear();
                typTable.deselectAll();
        });
        
        /// CREATE      
        create.addClickListener((Button.ClickEvent event) -> {
            if (! popis.isEmpty()) 
            {
                Typ typ = new Typ();
                typ.setPopis(popis.getValue());
                typClient.createOrUpdate(typ);
                loadTypy();
                popis.clear();
            }
        });
        
        ///UPDATE
        update.addClickListener((Button.ClickEvent event) -> {
            Optional<Typ> typ = typTable.getSelectionModel().getFirstSelectedItem();
            if (typ.isPresent() && !popis.isEmpty()) {
                Typ b = typClient.select(typ.get().getTypId());
                b.setPopis(popis.getValue());
                typClient.createOrUpdate(b);
                loadTypy();
            }
        });
        
        // DELETE
        delete.addClickListener((Button.ClickEvent event) -> {
            Optional<Typ> typ = typTable.getSelectionModel().getFirstSelectedItem();
            if (typ.isPresent() && !popis.isEmpty()) {
                Typ b = typClient.select(typ.get().getTypId()); 
                typClient.remove(b.getTypId());
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                popis.clear();
                typTable.deselectAll();
                loadTypy();
            }
        });
       
        // FILTR FIELD
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        filterButton.addClickListener((Button.ClickEvent event) -> {
            if (filterField.isEmpty())
                loadTypy();
            else
                loadTypyByName(typTable, filterField.getValue());
        });
   
        HorizontalLayout filter = new HorizontalLayout();
        filter.addComponents(filterField, filterButton);
        
        HorizontalLayout tabulkaAndCRUD = new HorizontalLayout();
        tabulkaAndCRUD.addComponents(typTable, crudFormular);
        layout.addComponents(tabulkaAndCRUD);
        layout.addComponents(typHeader, filter, tabulkaAndCRUD);
        
        
        
        
        buttonNewPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<Typ> typ = typTable.getSelectionModel().getFirstSelectedItem();
            
                if(!nameTF.isEmpty() && !nickTF.isEmpty() && !emailTF.isEmpty() && typ.isPresent())
                {
                    Uzivatel uziv = new Uzivatel();

                    uziv.setJmeno(nameTF.getValue());
                    uziv.setNickname(nickTF.getValue());
                    uziv.setEmail(emailTF.getValue());
                    uziv.setTypId(typ.get());
                    
                    uzivatelClient.createOrUpdate(uziv);
                    loadUzivatele(uzivatelTable);
                }
            }
        });
        
        Button edit = new Button("Uprav uzivatele");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Uzivatel> uzivatel = uzivatelTable.getSelectionModel().getFirstSelectedItem();
                if (uzivatel.isPresent()) {
                    Uzivatel b = uzivatelClient.select(uzivatel.get().getUzivatelId());
                    b.setJmeno(nameTF.getValue());
                    b.setNickname(nickTF.getValue());
                    b.setEmail(emailTF.getValue());
                    uzivatelClient.createOrUpdate(b);
                    loadUzivatele(uzivatelTable);
                }
            }
        });
       
    }

    
    
    
    
    
    
///*****************************************************************************    
///***************************** P R I S P E V K Y *****************************
///*****************************************************************************     
    private void loadPrispevky(Grid<Prispevek> prispTable) {
        prispTable.setItems(prispevekClient.selectAll());
    }
    
    void loadPrispevekConstrain(Grid<Prispevek> prispevekTable, String s ){
          prispevekTable.setItems(prispevekClient.selectConstrain(s));
    }
    
    private void initPrispevky(Layout layout) {      
        Label prispevekHeader = new Label("Prispevky");
        
        
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        
        
         
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadPrispevky(prispevekTable);
                }
                else
                {
                    loadPrispevekConstrain(prispevekTable, filterField.getValue()); 
                }
            }
        });
 
        
        
        
        prispevekTable.addComponentColumn(new ValueProvider<Prispevek, Button> () {
            @Override
            public Button apply(Prispevek source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        prispevekClient.remove(source.getPrispevekId());
                        loadPrispevky(prispevekTable);
                    }
                });
                return button;
            }
            
        });
        
        loadPrispevky(prispevekTable);
        TextField headerTF = new TextField("Nadpis");
        TextArea bodyTF = new TextArea("Telo");
        
        Button buttNewPrispevek = new Button("Pridej Prispevek");
        buttNewPrispevek.setIcon(FontAwesome.CHECK);
        buttNewPrispevek.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<Uzivatel> uz = uzivatelTable.getSelectionModel().getFirstSelectedItem();
                
                if (!headerTF.isEmpty() && !bodyTF.isEmpty() && uz.isPresent()) 
                {
                    
                    
                Prispevek prisp = new Prispevek();
                
                prisp.setNadpis(headerTF.getValue());  
                prisp.setTelo(headerTF.getValue()); 
                prisp.setUzivatelId(uz.get());
                prispevekClient.createOrUpdate(prisp);
                loadPrispevky(prispevekTable);
                }
            }
        });
        
        
        Button edit = new Button("Uprav prispevek");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Prispevek> prisp = prispevekTable.getSelectionModel().getFirstSelectedItem();
                if (prisp.isPresent()) {
                    Prispevek b = prispevekClient.select(prisp.get().getPrispevekId());
                    b.setNadpis(headerTF.getValue());
                    b.setTelo(bodyTF.getValue());
                    prispevekClient.createOrUpdate(b);
                    loadPrispevky(prispevekTable);
                }
            }
        });
        
        layout.addComponents(filterField, filterButton,prispevekHeader, prispevekTable, headerTF, bodyTF, buttNewPrispevek);
    }
    
///*****************************************************************************    
///***************************** I N I T  **************************************
///*****************************************************************************         
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        
        final VerticalLayout typLayout = new VerticalLayout();
        initTypy(typLayout);
        
        final VerticalLayout uzivatelLayout = new VerticalLayout();
        initUzivatele(uzivatelLayout);

        final VerticalLayout prispevekLayout = new VerticalLayout();
        initPrispevky(prispevekLayout);     
        
         HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
       // layout.addComponent(titleBar);

        Label title = new Label("UZIVATELSKE FORUM");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        Label titleComment = new Label("author: Martin Jenc");
        titleComment.setSizeUndefined(); // Take minimum space
        titleBar.addComponent(titleComment);
        
        VerticalLayout layout = new VerticalLayout( titleBar, typLayout, uzivatelLayout, prispevekLayout);
        
        setContent(layout);
       
    }

    @WebServlet(urlPatterns = "/*", name = "RoomUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class RoomUIServlet extends VaadinServlet {
    }
}