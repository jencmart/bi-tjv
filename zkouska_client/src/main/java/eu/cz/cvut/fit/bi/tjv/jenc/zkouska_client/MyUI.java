package eu.cz.cvut.fit.bi.tjv.jenc.zkouska_client;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Address;


import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Animal;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Animal;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Animal;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Zookeeper;
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

   // private final PrispevekClient prispevekClient;
   // private final UzivatelClient uzivatelClient;
    
    private final AnimalClient animalClient;
    private final AddressClient addressClient;
    private final ZookeeperClient zookeeperClient;
    
   // private final Grid<Prispevek> prispevekTable;
   // private final Grid<Uzivatel> uzivatelTable;
    
    private final Grid<Animal> animalTable;
    private final Grid<Address> addressTable;
    private final Grid<Zookeeper> zookeeperTable;
    
    public MyUI() {
      //  prispevekClient = new PrispevekClient();
      //  uzivatelClient = new UzivatelClient();
        animalClient = new AnimalClient();
        addressClient = new AddressClient();
        zookeeperClient = new ZookeeperClient();
       // prispevekTable = new Grid<>(Prispevek.class);
       // prispevekTable.setWidth("100%");
       // uzivatelTable = new Grid<>(Uzivatel.class);
       // uzivatelTable.setWidth("100%");
        animalTable = new Grid<>(Animal.class);
        addressTable = new Grid<>(Address.class);
        zookeeperTable =  new Grid<>(Zookeeper.class);
        animalTable.setWidth("100%");
    }
    
    
///*****************************************************************************    
///******************************* A D R E S Y *********************************
///*****************************************************************************
    void loadAddress(Grid<Address> addressTable) {
        
        Collection<Address> x = addressClient.findAll();
        
        addressTable.setItems(x);  
    }
    
    void loadAddressByName(Grid<Address> typTable, String s ){
          typTable.setItems(addressClient.findByTown(s));
    }
    
    private void initAddress(Layout layout) { 
        
        Label typHeader = new Label("ADDRESSES:");
        addressTable.removeColumn("addressId");
        addressTable.removeColumn("zookeeperCollection");
        
        loadAddress(addressTable);
       
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
        
        TextField street = new TextField("Street");
        TextField town = new TextField("Town");
        
        HorizontalLayout actions = new HorizontalLayout(createNew,create, update, delete);
        actions.setSpacing(true);
        crudFormular.addComponents(actions, street, town);
     
        /// SELECT ROW
        addressTable.addSelectionListener(event -> {
            Optional<Address> optAddress = addressTable.getSelectionModel().getFirstSelectedItem();
            if(optAddress.isPresent())
            {
                Address address = addressClient.select(optAddress.get().getAddressId());
                street.setValue(address.getStreet());
                town.setValue(address.getTown());
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
                
                street.clear();
                town.clear();
                addressTable.deselectAll();
        });
        
        /// CREATE      
        create.addClickListener((Button.ClickEvent event) -> {
            if (! street.isEmpty() && !town.isEmpty()) 
            {
                Address address = new Address();
                
                address.setStreet(street.getValue());
                address.setTown(town.getValue());
                
                addressClient.createOrUpdate(address);
                
                loadAddress(addressTable);
                
                street.clear();
                town.clear();
            }
        });
        
        ///UPDATE
        update.addClickListener((Button.ClickEvent event) -> {
            Optional<Address> tmpAddress = addressTable.getSelectionModel().getFirstSelectedItem();
            if (tmpAddress.isPresent() && !street.isEmpty() && !town.isEmpty()) {
                
                Address address = addressClient.select(tmpAddress.get().getAddressId());
                
                address.setStreet(street.getValue());
                address.setTown  (town.getValue());
                addressClient.createOrUpdate(address);
                loadAddress(addressTable);
            }
        });
        
        // DELETE
        delete.addClickListener((Button.ClickEvent event) -> {
            Optional<Address> tmpAddress = addressTable.getSelectionModel().getFirstSelectedItem();
            if (tmpAddress.isPresent() ) {
                Address address = addressClient.select(tmpAddress.get().getAddressId()); 
                
                addressClient.remove(address.getAddressId());
                
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                
                street.clear();
                town.clear();
                addressTable.deselectAll();
                loadAddress(addressTable);
            }
        });
       
        // FILTR FIELD
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        filterButton.addClickListener((Button.ClickEvent event) -> {
            if (filterField.isEmpty())
                loadAddress(addressTable);
            else
                loadAddressByName(addressTable, filterField.getValue());
        });
   
        VerticalLayout filter = new VerticalLayout();
        filter.addComponents(filterField, filterButton);
        
        HorizontalLayout tabulkaAndCRUD = new HorizontalLayout();
        tabulkaAndCRUD.addComponents(addressTable, crudFormular);
        layout.addComponents(tabulkaAndCRUD);
        layout.addComponents(typHeader, filter, tabulkaAndCRUD);
    }
    



   
///*****************************************************************************    
///*******************************  Z O O K E E P E R  *************************
///*****************************************************************************
    void loadZookeeper(Grid<Zookeeper> zookeeperTable) {
        
        Collection<Zookeeper> x = zookeeperClient.findAll();
        
        zookeeperTable.setItems(x);  
    }
    
    void loadZookeeperByName(Grid<Zookeeper> typTable, String s ){
          typTable.setItems(zookeeperClient.findByName(s));
    }
    
    private void initZookeeper(Layout layout) { 
        
        Label typHeader = new Label("ZOOKEEPERS:");
        zookeeperTable.removeColumn("zookeeperId");
       zookeeperTable.removeColumn("animalCollection");
        
       zookeeperTable.removeColumn("addressId");
        loadZookeeper(zookeeperTable);
       
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
        
        TextField name = new TextField("Name");
        TextField surname = new TextField("Surname");
        
        ComboBox<Address> adresa = new ComboBox<>("Address");
        adresa.setItems(addressClient.findAll());
        adresa.setItemCaptionGenerator(Address::getStreet);

        
        HorizontalLayout actions = new HorizontalLayout(createNew,create, update, delete);
        actions.setSpacing(true);
        crudFormular.addComponents(actions, name, surname, adresa);
     
        /// SELECT ROW
        zookeeperTable.addSelectionListener(event -> {
            Optional<Zookeeper> optZookeeper = zookeeperTable.getSelectionModel().getFirstSelectedItem();
            if(optZookeeper.isPresent())
            {
                Zookeeper zookeeper = zookeeperClient.select(optZookeeper.get().getZookeeperId());
                name.setValue(zookeeper.getName());
                surname.setValue(zookeeper.getSurname());
                adresa.setValue(zookeeper.getAddressId());
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
                
                name.clear();
                surname.clear();
                adresa.clear();
                adresa.setItems(addressClient.findAll());
                zookeeperTable.deselectAll();
        });
        
        /// CREATE      
        create.addClickListener((Button.ClickEvent event) -> {
            if (! name.isEmpty() && !surname.isEmpty()) 
            {
                Zookeeper zookeeper = new Zookeeper();
                zookeeper.setName(name.getValue());
                zookeeper.setSurname(surname.getValue());
                zookeeper.setAddressId(adresa.getValue());
                zookeeperClient.createOrUpdate(zookeeper);
                
                loadZookeeper(zookeeperTable);
                
                name.clear();
                surname.clear();
                adresa.clear();
                adresa.setItems(addressClient.findAll());
            }
        });
        
        ///UPDATE
        update.addClickListener((Button.ClickEvent event) -> {
            Optional<Zookeeper> tmpZookeeper = zookeeperTable.getSelectionModel().getFirstSelectedItem();
            if (tmpZookeeper.isPresent() && !name.isEmpty() && !surname.isEmpty()) {
                
                Zookeeper zookeeper = zookeeperClient.select(tmpZookeeper.get().getZookeeperId());
                
                zookeeper.setName(name.getValue());
                zookeeper.setSurname  (surname.getValue());
                zookeeper.setAddressId(adresa.getValue());
                
                zookeeperClient.createOrUpdate(zookeeper);
                loadZookeeper(zookeeperTable);
            }
        });
        
        // DELETE
        delete.addClickListener((Button.ClickEvent event) -> {
            Optional<Zookeeper> tmpZookeeper = zookeeperTable.getSelectionModel().getFirstSelectedItem();
            if (tmpZookeeper.isPresent() ) {
                Zookeeper zookeeper = zookeeperClient.select(tmpZookeeper.get().getZookeeperId()); 
                
                zookeeperClient.remove(zookeeper.getZookeeperId());
                
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                
                name.clear();
                surname.clear();
                adresa.clear();
                 adresa.setItems(addressClient.findAll());
                 
                zookeeperTable.deselectAll();
                loadZookeeper(zookeeperTable);
            }
        });
       
        // FILTR FIELD
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        filterButton.addClickListener((Button.ClickEvent event) -> {
            if (filterField.isEmpty())
                loadZookeeper(zookeeperTable);
            else
                loadZookeeperByName(zookeeperTable, filterField.getValue());
        });
   
        VerticalLayout filter = new VerticalLayout();
        filter.addComponents(filterField, filterButton);
        
        HorizontalLayout tabulkaAndCRUD = new HorizontalLayout();
        tabulkaAndCRUD.addComponents(zookeeperTable, crudFormular);
        layout.addComponents(tabulkaAndCRUD);
        layout.addComponents(typHeader, filter, tabulkaAndCRUD);
    }    
    
///*****************************************************************************    
///*******************************  Z O O K E E P E R  *************************
///*****************************************************************************
    void loadAnimal(Grid<Animal> animalTable) {
        
        Collection<Animal> x = animalClient.findAll();
        
        animalTable.setItems(x);  
    }
    
    void loadAnimalByName(Grid<Animal> typTable, String s ){
          typTable.setItems(animalClient.findByName(s));
    }
    
    private void initAnimal(Layout layout) { 
        
        Label typHeader = new Label("ANIMALS:");
        animalTable.removeColumn("animalId");
       animalTable.removeColumn("zookeeperId");
        
        loadAnimal(animalTable);
       
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
        
        TextField name = new TextField("Name");
        TextField parentName = new TextField("Parent Name");
        
        ComboBox<Zookeeper> zookpr = new ComboBox<>("Zookeeper");
        zookpr.setItems(zookeeperClient.findAll());
        zookpr.setItemCaptionGenerator(Zookeeper::getName);

        
        HorizontalLayout actions = new HorizontalLayout(createNew,create, update, delete);
        actions.setSpacing(true);
        crudFormular.addComponents(actions, name, parentName, zookpr);
     
        /// SELECT ROW
        animalTable.addSelectionListener(event -> {
            Optional<Animal> optAnimal = animalTable.getSelectionModel().getFirstSelectedItem();
            if(optAnimal.isPresent())
            {
                Animal animal = animalClient.select(optAnimal.get().getAnimalId());
                name.setValue(animal.getName());
                parentName.setValue(animal.getParentName());
                zookpr.setValue(animal.getZookeeperId());
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
                
                name.clear();
                parentName.clear();
                zookpr.clear();
                zookpr.setItems(zookeeperClient.findAll());
                animalTable.deselectAll();
        });
        
        /// CREATE      
        create.addClickListener((Button.ClickEvent event) -> {
            if (! name.isEmpty() && !parentName.isEmpty()) 
            {
                Animal animal = new Animal();
                animal.setName(name.getValue());
                animal.setParentName(parentName.getValue());
                animal.setZookeeperId(zookpr.getValue());
                animalClient.createOrUpdate(animal);
                
                loadAnimal(animalTable);
                
                name.clear();
                parentName.clear();
                zookpr.clear();
                zookpr.setItems(zookeeperClient.findAll());
            }
        });
        
        ///UPDATE
        update.addClickListener((Button.ClickEvent event) -> {
            Optional<Animal> tmpAnimal = animalTable.getSelectionModel().getFirstSelectedItem();
            if (tmpAnimal.isPresent() && !name.isEmpty() && !parentName.isEmpty()) {
                
                Animal animal = animalClient.select(tmpAnimal.get().getAnimalId());
                
                animal.setName(name.getValue());
                animal.setParentName  (parentName.getValue());
                animal.setZookeeperId(zookpr.getValue());
                
                animalClient.createOrUpdate(animal);
                loadAnimal(animalTable);
            }
        });
        
        // DELETE
        delete.addClickListener((Button.ClickEvent event) -> {
            Optional<Animal> tmpAnimal = animalTable.getSelectionModel().getFirstSelectedItem();
            if (tmpAnimal.isPresent() ) {
                Animal animal = animalClient.select(tmpAnimal.get().getAnimalId()); 
                
                animalClient.remove(animal.getAnimalId());
                
                create.setVisible(true);
                createNew.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                
                name.clear();
                parentName.clear();
                zookpr.clear();
                 zookpr.setItems(zookeeperClient.findAll());
                 
                animalTable.deselectAll();
                loadAnimal(animalTable);
            }
        });
       
        // FILTR FIELD
        TextField filterField = new TextField("Filtr");
        Button filterButton = new Button("Filtruj!");
        filterButton.setIcon(FontAwesome.MAGIC);
        filterButton.addClickListener((Button.ClickEvent event) -> {
            if (filterField.isEmpty())
                loadAnimal(animalTable);
            else
                loadAnimalByName(animalTable, filterField.getValue());
        });
   
        VerticalLayout filter = new VerticalLayout();
        filter.addComponents(filterField, filterButton);
        
        HorizontalLayout tabulkaAndCRUD = new HorizontalLayout();
        tabulkaAndCRUD.addComponents(animalTable, crudFormular);
        layout.addComponents(tabulkaAndCRUD);
        layout.addComponents(typHeader, filter, tabulkaAndCRUD);
    }
    
    
    ///*****************************************************************************    
///***************************** I N I T  **************************************
///*****************************************************************************         
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        final VerticalLayout adresaLayout = new VerticalLayout();
        initAddress(adresaLayout);
        
        
         
        final VerticalLayout zookeeperLayout = new VerticalLayout();
        initZookeeper(zookeeperLayout);
        
        
        
        final VerticalLayout animalLayout = new VerticalLayout();
        initAnimal(animalLayout);
        
        
        
   
        
         HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
       // layout.addComponent(titleBar);

        Label title = new Label("ZOLOGICKA ZAHRADA");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        Label titleComment = new Label("author: Martin Jenc");
        titleComment.setSizeUndefined(); // Take minimum space
        titleBar.addComponent(titleComment);
        
        VerticalLayout layout = new VerticalLayout( titleBar, adresaLayout, zookeeperLayout, animalLayout);
        
        setContent(layout);
       
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
