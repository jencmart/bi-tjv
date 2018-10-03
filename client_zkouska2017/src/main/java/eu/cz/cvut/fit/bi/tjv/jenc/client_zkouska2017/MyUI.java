package eu.cz.cvut.fit.bi.tjv.jenc.client_zkouska2017;

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
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Customer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
import java.util.Arrays;
import java.util.Optional;

@Theme("mytheme")
public class MyUI extends UI {

   
    private final CustomerClient customerClient;
    private final ItemClient itemClient;
    private final ItemCustomerClient itemCustomerClient;
    private final ProducerClient producerClient;
    
    private final Grid<Customer> customerTable;
    private final Grid<Item> itemTable;
    private final Grid<ItemCustomer> itemCustomerTable;
    private final Grid<Producer> producerTable;
    
    public MyUI() {
        customerClient = new CustomerClient();
        itemClient = new ItemClient();
        itemCustomerClient = new ItemCustomerClient();
        producerClient = new ProducerClient();
        
        
        customerTable = new Grid<>(Customer.class);
        customerTable.setWidth("70%");
        itemTable = new Grid<>(Item.class);
        itemTable.setWidth("70%");
        itemCustomerTable = new Grid<>(ItemCustomer.class);
        itemCustomerTable.setWidth("70%");
        
        producerTable = new Grid<>(Producer.class);
        producerTable.setWidth("70%");
        
    }
    
    void loadItemCustomery(Grid<ItemCustomer> itemCustomerTable) 
    {
       itemCustomerTable.setItems(Arrays.asList (itemCustomerClient.findAll_JSON(ItemCustomer[].class) ));
    }
    
    void loadItemCustomeryConstrain(Grid<ItemCustomer> itemCustomerTable, String s )
    {
        itemCustomerTable.setItems(Arrays.asList (itemCustomerClient.findByName_JSON(ItemCustomer[].class, s) ));
    }
    
    private void initItemCustomery(Layout layout) 
    {
        Label itemCustomerHeader = new Label("ItemCustomer");
        
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        
        
        
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadItemCustomery(itemCustomerTable);
                }
                else
                {
                    loadItemCustomeryConstrain(itemCustomerTable, filterField.getValue()); 
                }
            }
        });
 
        

        itemCustomerTable.addComponentColumn(new ValueProvider<ItemCustomer, Button> () 
        {
            @Override
            public Button apply(ItemCustomer source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        itemCustomerClient.remove(source.getItemCustomerId().toString());
                        loadItemCustomery(itemCustomerTable);
                    }
                });               
                return button;
            }            
        });
       
        loadItemCustomery(itemCustomerTable);
              
        TextField itemCustomerPopisTF = new TextField("ItemCustomer");
        Button buttonNewItemCustomer = new Button("Pridej itemCustomer");
        buttonNewItemCustomer.setIcon(FontAwesome.CHECK);
        buttonNewItemCustomer.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {    
                if (! itemCustomerPopisTF.isEmpty()) {
                    ItemCustomer itemCustomer = new ItemCustomer();
                 
                    itemCustomer.setJmeno(itemCustomerPopisTF.getValue());
                    itemCustomerClient.create_JSON(itemCustomer);
                  //itemCustomerClientJersey.create_XML(itemCustomer);
                    loadItemCustomery(itemCustomerTable);
                }
            }
        });
        
        Button edit = new Button("Uprav itemCustomer");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<ItemCustomer> itemCustomer = itemCustomerTable.getSelectionModel().getFirstSelectedItem();
                if (itemCustomer.isPresent()) {
                    ItemCustomer b = itemCustomerClient.find_JSON(ItemCustomer.class, itemCustomer.get().getItemCustomerId().toString());
                    b.setJmeno(itemCustomerPopisTF.getValue());
                    
                    itemCustomerClient.create_JSON(b);
                    loadItemCustomery(itemCustomerTable);
                }
            }
        });
 
        layout.addComponents(filterField, filterButton, itemCustomerHeader, itemCustomerTable, itemCustomerPopisTF, buttonNewItemCustomer, edit);
    }
    
    
    private void loadItem(Grid<Item> itemTable) {
        itemTable.setItems(Arrays.asList (itemClient.findAll_JSON(Item[].class) ));
    }
    
    
    void loadItemeConstrain(Grid<Item> itemTable, String s ) {
        itemTable.setItems(Arrays.asList (itemClient.findByName_JSON(Item[].class,s) ));
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
                   
                    loadItem(itemTable);
                }
                else
                {
                    loadItemeConstrain(itemTable, filterField.getValue()); 
                }
            }
        });
 
        
      
        itemTable.addComponentColumn(new ValueProvider<Item, Button> () 
        {
            @Override
            public Button apply(Item source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        itemClient.remove(source.getItemId().toString());
                        loadItem(itemTable);
                    }
                });
                return button;
            }
        });
        
        loadItem(itemTable);
        
        TextField nameTF = new TextField("Jmeno");
        TextField nickTF = new TextField("Nickname");
        TextField emailTF = new TextField("E-mail");
        
        Button buttonNewPerson = new Button("Pridej Iteme");
        buttonNewPerson.setIcon(FontAwesome.CHECK);
        buttonNewPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<ItemCustomer> itemCustomer = itemCustomerTable.getSelectionModel().getFirstSelectedItem();
            
                if(!nameTF.isEmpty() && !nickTF.isEmpty() && !emailTF.isEmpty() && itemCustomer.isPresent())
                {
                    Item uziv = new Item();

                    uziv.setPopis(nameTF.getValue());
                                    
                    itemClient.create_JSON(uziv);
                    loadItem(itemTable);
                }
            }
        });
        
        Button edit = new Button("Uprav iteme");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Item> item = itemTable.getSelectionModel().getFirstSelectedItem();
                if (item.isPresent()) {
                    Item b = itemClient.find_JSON(Item.class ,item.get().getItemId().toString());
                    b.setPopis(nameTF.getValue());
                    itemClient.create_JSON(b);
                    loadItem(itemTable);
                }
            }
        });
        
        layout.addComponents(filterField, filterButton,personHeader, itemTable, nameTF, nickTF, emailTF, buttonNewPerson, edit);
    }

    
    private void loadPrispevky(Grid<Customer> customerTable) {
        customerTable.setItems(Arrays.asList (customerClient.findAll_JSON(Customer[].class) ));
    }
    
    
    void loadCustomerConstrain(Grid<Customer> customerTable, String s )
    {
        customerTable.setItems(Arrays.asList (customerClient.findByName_JSON(Customer[].class,s) ));
          
    }
    
    
    
    private void initPrispevky(Layout layout) {      
        Label customerHeader = new Label("Prispevky");
        
        
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        
        
         
        filterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) 
            {
               
                if (filterField.isEmpty()) {
                   
                    loadPrispevky(customerTable);
                }
                else
                {
                    loadCustomerConstrain(customerTable, filterField.getValue()); 
                }
            }
        });
 
        
        
        
        customerTable.addComponentColumn(new ValueProvider<Customer, Button> () {
            @Override
            public Button apply(Customer source) {
                Button button = new Button("Delete");
                button.setIcon(FontAwesome.REMOVE);
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        customerClient.remove(source.getCustomerId().toString());
                        loadPrispevky(customerTable);
                    }
                });
                return button;
            }
            
        });
        
        loadPrispevky(customerTable);
        TextField headerTF = new TextField("Nadpis");
        TextArea bodyTF = new TextArea("Telo");
        
        Button buttNewCustomer = new Button("Pridej Customer");
        buttNewCustomer.setIcon(FontAwesome.CHECK);
        buttNewCustomer.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<Item> uz = itemTable.getSelectionModel().getFirstSelectedItem();
                
                if (!headerTF.isEmpty() && !bodyTF.isEmpty() && uz.isPresent()) 
                {
                    
                    
                Customer prisp = new Customer();
                
                prisp.setJmeno(headerTF.getValue());  
                
                customerClient.create_JSON(prisp);
                loadPrispevky(customerTable);
                }
            }
        });
        
        
        Button edit = new Button("Uprav customer");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Customer> prisp = customerTable.getSelectionModel().getFirstSelectedItem();
                if (prisp.isPresent()) {
                    Customer b = customerClient.find_JSON(Customer.class,prisp.get().getCustomerId().toString());
                    b.setJmeno(headerTF.getValue());
                    
                    customerClient.create_JSON(b);
                    loadPrispevky(customerTable);
                }
            }
        });
        
        layout.addComponents(filterField, filterButton,customerHeader, customerTable, headerTF, bodyTF, buttNewCustomer);
    }
    
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        
        final VerticalLayout itemCustomerLayout = new VerticalLayout();
        initItemCustomery(itemCustomerLayout);
        
        final VerticalLayout itemLayout = new VerticalLayout();
        initIteme(itemLayout);

        final VerticalLayout customerLayout = new VerticalLayout();
        initPrispevky(customerLayout);     
        
        
        
        
         HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
       // layout.addComponent(titleBar);

        Label title = new Label("UZIVATELSKE FORUM");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        Label titleComment = new Label("author: Martin Jenc");
        titleComment.setSizeUndefined(); // Take minimum space
        titleBar.addComponent(titleComment);
        
        VerticalLayout layout = new VerticalLayout( titleBar, itemCustomerLayout, itemLayout, customerLayout);
        
        setContent(layout);
        
       
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
