package eu.cz.cvut.fit.bi.tjv.jenc.trenink_client;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;
import java.util.Optional;

import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Customer;
import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Item;
import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Producer;


@Theme("mytheme")
public class MyUI extends UI {

    
    private final CustomerClient prispevekClient;
    private final ItemClient uzivatelClient;
    private final ProducerClient typClient;
    
    private final Grid<Customer> prispevekTable;
    private final Grid<Item> uzivatelTable;
    private final Grid<Producer> typTable;
    
    public MyUI() {
        prispevekClient = new CustomerClient();
        uzivatelClient = new ItemClient();
        typClient = new ProducerClient();

        prispevekTable = new Grid<>(Customer.class);
        
        uzivatelTable = new Grid<>(Item.class);
        
        typTable = new Grid<>(Producer.class);
        
    }
    
    void loadProducery(Grid<Producer> typTable) 
    {
       Collection<Producer> x = typClient.selectAll();
       typTable.setItems(x);
    }
    
    void loadProduceryConstrain(Grid<Producer> typTable, String s )
    {
        Collection<Producer> x = typClient.selectConstrain(s);
          typTable.setItems(x);
    }
    
    private void initProducery(Layout layout) 
    {
        Label typHeader = new Label("Producer");
        
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        
        
        
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadProducery(typTable);
                }
                else
                {
                    loadProduceryConstrain(typTable, filterField.getValue()); 
                }
            }
        });
 
        

        typTable.addComponentColumn(new ValueProvider<Producer, Button> () 
        {
            @Override
            public Button apply(Producer source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        typClient.remove(source.getProducerId());
                        //typClientJersey.remove(source.getPopis().toString());
                        loadProducery(typTable);
                    }
                });               
                return button;
            }            
        });
       
        loadProducery(typTable);
              
        TextField typPopisTF = new TextField("Producer");
        Button buttonNewProducer = new Button("Pridej typ");
        buttonNewProducer.setIcon(FontAwesome.CHECK);
        buttonNewProducer.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {    
                if (! typPopisTF.isEmpty()) {
                    Producer typ = new Producer();
                 
                    typ.setPopis(typPopisTF.getValue());
                    typClient.createOrUpdate(typ);
                  //typClientJersey.create_XML(typ);
                    loadProducery(typTable);
                }
            }
        });
        
        Button edit = new Button("Uprav typ");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Producer> typ = typTable.getSelectionModel().getFirstSelectedItem();
                if (typ.isPresent()) {
                    Producer b = typClient.select(typ.get().getProducerId());
                    b.setPopis(typPopisTF.getValue());
                    
                    typClient.createOrUpdate(b);
                    loadProducery(typTable);
                }
            }
        });
 
        layout.addComponents(filterField, filterButton, typHeader, typTable, typPopisTF, buttonNewProducer, edit);
    }
    
    
    private void loadItem(Grid<Item> uzivatelTable) {
        uzivatelTable.setItems(uzivatelClient.selectAll());
    }
    
    
    void loadItemeConstrain(Grid<Item> uzivatelTable, String s )
    {
        Collection<Item> x = uzivatelClient.selectConstrain(s);
          uzivatelTable.setItems(x);
    }
    
    
    private void initIteme(Layout layout) 
    {
        Label personHeader = new Label("Iteme");
        
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        
        
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadItem(uzivatelTable);
                }
                else
                {
                    loadItemeConstrain(uzivatelTable, filterField.getValue()); 
                }
            }
        });
 
        
      
        uzivatelTable.addComponentColumn(new ValueProvider<Item, Button> () 
        {
            @Override
            public Button apply(Item source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        uzivatelClient.remove(source.getItemId());
                        loadItem(uzivatelTable);
                    }
                });
                return button;
            }
        });
        
        loadItem(uzivatelTable);
        
        TextField nameTF = new TextField("Jmeno");
        TextField nickTF = new TextField("Nickname");
        TextField emailTF = new TextField("E-mail");
        
        Button buttonNewPerson = new Button("Pridej Iteme");
        buttonNewPerson.setIcon(FontAwesome.CHECK);
        buttonNewPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<Producer> typ = typTable.getSelectionModel().getFirstSelectedItem();
            
                if(!nameTF.isEmpty() && !nickTF.isEmpty() && !emailTF.isEmpty() && typ.isPresent())
                {
                    Item uziv = new Item();

                    uziv.setJmeno(nameTF.getValue());
                    uziv.setNickname(nickTF.getValue());
                    uziv.setEmail(emailTF.getValue());
                    uziv.setProducerId(typ.get());
                    
                    uzivatelClient.createOrUpdate(uziv);
                    loadItem(uzivatelTable);
                }
            }
        });
        
        Button edit = new Button("Uprav uzivatele");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Item> uzivatel = uzivatelTable.getSelectionModel().getFirstSelectedItem();
                if (uzivatel.isPresent()) {
                    Item b = uzivatelClient.select(uzivatel.get().getItemId());
                    b.setJmeno(nameTF.getValue());
                    b.setNickname(nickTF.getValue());
                    b.setEmail(emailTF.getValue());
                    uzivatelClient.createOrUpdate(b);
                    loadItem(uzivatelTable);
                }
            }
        });
        
        layout.addComponents(filterField, filterButton,personHeader, uzivatelTable, nameTF, nickTF, emailTF, buttonNewPerson, edit);
    }

    
    private void loadPrispevky(Grid<Customer> prispTable) {
        prispTable.setItems(prispevekClient.selectAll());
    }
    
    
    void loadCustomerConstrain(Grid<Customer> prispevekTable, String s )
    {
        Collection<Customer> x = prispevekClient.selectConstrain(s);
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
                    loadCustomerConstrain(prispevekTable, filterField.getValue()); 
                }
            }
        });
 
        
        
        
        prispevekTable.addComponentColumn(new ValueProvider<Customer, Button> () {
            @Override
            public Button apply(Customer source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        prispevekClient.remove(source.getCustomerId());
                        loadPrispevky(prispevekTable);
                    }
                });
                return button;
            }
            
        });
        
        loadPrispevky(prispevekTable);
        TextField headerTF = new TextField("Nadpis");
        TextArea bodyTF = new TextArea("Telo");
        
        Button buttNewCustomer = new Button("Pridej Customer");
        buttNewCustomer.setIcon(FontAwesome.CHECK);
        buttNewCustomer.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<Item> uz = uzivatelTable.getSelectionModel().getFirstSelectedItem();
                
                if (!headerTF.isEmpty() && !bodyTF.isEmpty() && uz.isPresent()) 
                {  
                    
                Customer prisp = new Customer();
                
                prisp.setNadpis(headerTF.getValue());  
                prisp.setTelo(headerTF.getValue()); 
                prisp.setItemId(uz.get());
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
                Optional<Customer> prisp = prispevekTable.getSelectionModel().getFirstSelectedItem();
                if (prisp.isPresent()) {
                    Customer b = prispevekClient.select(prisp.get().getCustomerId());
                    b.setNadpis(headerTF.getValue());
                    b.setTelo(bodyTF.getValue());
                    prispevekClient.createOrUpdate(b);
                    loadPrispevky(prispevekTable);
                }
            }
        });
        
        layout.addComponents(filterField, filterButton,prispevekHeader, prispevekTable, headerTF, bodyTF, buttNewCustomer);
    }
    
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout typLayout = new VerticalLayout();
        initProducery(typLayout);
        
        final VerticalLayout uzivatelLayout = new VerticalLayout();
        initIteme(uzivatelLayout);

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
