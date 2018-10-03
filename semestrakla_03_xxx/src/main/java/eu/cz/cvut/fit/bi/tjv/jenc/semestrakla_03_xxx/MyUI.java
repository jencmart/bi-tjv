package eu.cz.cvut.fit.bi.tjv.jenc.semestrakla_03_xxx;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Prispevek;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Typ;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Uzivatel;
import java.util.Collection;
import java.util.Optional;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

     
    private final PrispevekClient prispevekClient;
    private final UzivatelClient uzivatelClient;
    private final TypClient typClient;
    
    
    private final Grid<Prispevek> prispevekTable;
    private final Grid<Uzivatel> uzivatelTable;
    private final Grid<Typ> typTable;
    
    public MyUI() {
        prispevekClient = new PrispevekClient();
        uzivatelClient = new UzivatelClient();
        typClient = new TypClient();
        
        
        prispevekTable = new Grid<>(Prispevek.class);
        prispevekTable.setWidth("70%");
        uzivatelTable = new Grid<>(Uzivatel.class);
        uzivatelTable.setWidth("70%");
        typTable = new Grid<>(Typ.class);
        typTable.setWidth("70%");
    }
    
    void loadTypy(Grid<Typ> typTable) 
    {
       Collection<Typ> x = typClient.selectAll();
       typTable.setItems(x);
    }
    
    void loadTypyConstrain(Grid<Typ> typTable, String s )
    {
        Collection<Typ> x = typClient.selectConstrain(s);
          typTable.setItems(x);
    }
    
    private void initTypy(Layout layout) 
    {
        Label typHeader = new Label("Typ");
        
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        
        
        
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadTypy(typTable);
                }
                else
                {
                    loadTypyConstrain(typTable, filterField.getValue()); 
                }
            }
        });
 
        

        typTable.addComponentColumn(new ValueProvider<Typ, Button> () 
        {
            @Override
            public Button apply(Typ source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        typClient.remove(source.getTypId());
                        //typClientJersey.remove(source.getPopis().toString());
                        loadTypy(typTable);
                    }
                });               
                return button;
            }            
        });
       
        loadTypy(typTable);
              
        TextField typPopisTF = new TextField("Typ");
        Button buttonNewTyp = new Button("Pridej typ");
        buttonNewTyp.setIcon(FontAwesome.CHECK);
        buttonNewTyp.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {    
                if (! typPopisTF.isEmpty()) {
                    Typ typ = new Typ();
                 
                    typ.setPopis(typPopisTF.getValue());
                    typClient.createOrUpdate(typ);
                  //typClientJersey.create_XML(typ);
                    loadTypy(typTable);
                }
            }
        });
        
        Button edit = new Button("Uprav typ");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Typ> typ = typTable.getSelectionModel().getFirstSelectedItem();
                if (typ.isPresent()) {
                    Typ b = typClient.select(typ.get().getTypId());
                    b.setPopis(typPopisTF.getValue());
                    
                    typClient.createOrUpdate(b);
                    loadTypy(typTable);
                }
            }
        });
 
        layout.addComponents(filterField, filterButton, typHeader, typTable, typPopisTF, buttonNewTyp, edit);
    }
    
    
    private void loadUzivatel(Grid<Uzivatel> uzivatelTable) {
        uzivatelTable.setItems(uzivatelClient.selectAll());
    }
    
    
    void loadUzivateleConstrain(Grid<Uzivatel> uzivatelTable, String s )
    {
        Collection<Uzivatel> x = uzivatelClient.selectConstrain(s);
          uzivatelTable.setItems(x);
    }
    
    
    private void initUzivatele(Layout layout) 
    {
        Label personHeader = new Label("Uzivatele");
        
        ComboBox<Typ> select = new ComboBox<>("Select Role");
        select.setItems(typClient.selectAll());
        select.setItemCaptionGenerator(Typ::getPopis);

        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
       
        filterButton.setIcon(FontAwesome.MAGIC);
        
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadUzivatel(uzivatelTable);
                }
                else
                {
                    loadUzivateleConstrain(uzivatelTable, filterField.getValue()); 
                }
            }
        });
 
        
      
        uzivatelTable.addComponentColumn(new ValueProvider<Uzivatel, Button> () 
        {
            @Override
            public Button apply(Uzivatel source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        uzivatelClient.remove(source.getUzivatelId());
                        loadUzivatel(uzivatelTable);
                    }
                });
                return button;
            }
        });
        
        loadUzivatel(uzivatelTable);
        
        TextField nameTF = new TextField("Jmeno");
        TextField nickTF = new TextField("Nickname");
        TextField emailTF = new TextField("E-mail");
        
        Button buttonNewPerson = new Button("Pridej Uzivatele");
        buttonNewPerson.setIcon(FontAwesome.CHECK);
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
                    loadUzivatel(uzivatelTable);
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
                    loadUzivatel(uzivatelTable);
                }
            }
        });
        
        layout.addComponents(filterField, filterButton,personHeader, uzivatelTable, nameTF, nickTF, emailTF, select, buttonNewPerson, edit);
    }

    
    private void loadPrispevky(Grid<Prispevek> prispTable) {
        prispTable.setItems(prispevekClient.selectAll());
    }
    
    
    void loadPrispevekConstrain(Grid<Prispevek> prispevekTable, String s )
    {
        Collection<Prispevek> x = prispevekClient.selectConstrain(s);
          prispevekTable.setItems(x);
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

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
