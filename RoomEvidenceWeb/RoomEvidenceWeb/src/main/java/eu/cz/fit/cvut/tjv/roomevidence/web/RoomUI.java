package eu.cz.fit.cvut.tjv.roomevidence.web;

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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Building;
import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Person;
import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Room;
import eu.cz.fit.cvut.tjv.roomevidence.rest_client.BuildingClient;
import eu.cz.fit.cvut.tjv.roomevidence.rest_client.PersonClient;
import eu.cz.fit.cvut.tjv.roomevidence.rest_client.RoomClient;
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
public class RoomUI extends UI {
    
    private final BuildingClient buildingClient;
    private final PersonClient personClient;
    private final RoomClient roomClient;
    private final Grid<Building> buildingTable;
    private final Grid<Person> personTable;
    private final Grid<Room> roomTable;
    
    public RoomUI() {
        buildingClient = new BuildingClient();
        personClient = new PersonClient();
        roomClient = new RoomClient();
        buildingTable = new Grid<>(Building.class);
        personTable = new Grid<>(Person.class);
        roomTable = new Grid<>(Room.class);
    }
    
    private void loadBuildings(Grid<Building> buildTable) {
        buildTable.setItems(buildingClient.selectAll());
    }
    
    private void initBuildings(Layout layout) {      
        Label buildingHeader = new Label("Buildings");
        buildingTable.addComponentColumn(new ValueProvider<Building, Button> () {
            @Override
            public Button apply(Building source) {
                Button button = new Button("Delete");
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        buildingClient.remove(source.getId());
                        loadBuildings(buildingTable);
                    }
                });
                button.setIcon(FontAwesome.REMOVE);                
                return button;
            }
            
        });
        
        loadBuildings(buildingTable);
        TextField addressTF = new TextField("Address");
        
        Button buttonNewBuilding = new Button("Add building");
        buttonNewBuilding.setIcon(FontAwesome.SEND);
        buttonNewBuilding.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Building building = new Building();
                building.setAddress(addressTF.getValue());                
                buildingClient.createOrUpdate(building);
                loadBuildings(buildingTable);
            }
        });
        
        TextField searchAddress = new TextField("Searched address");
        Button search = new Button("Search building");
        search.setIcon(FontAwesome.SEARCH);
        search.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!searchAddress.getValue().isEmpty()) {
                    Collection<Building> buildings = buildingClient.selectAll(searchAddress.getValue());
                    buildingTable.setItems(buildings);
                }
                else
                    loadBuildings(buildingTable);
            }
        });
        
        Button edit = new Button("Edit address");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Building> building = buildingTable.getSelectionModel().getFirstSelectedItem();
                if (building.isPresent()) {
                    Building b = buildingClient.select(building.get().getId());
                    b.setAddress(addressTF.getValue());
                    buildingClient.createOrUpdate(b);
                    loadBuildings(buildingTable);
                }
            }
        });
        
        layout.addComponents(searchAddress, search, buildingHeader, buildingTable, addressTF, buttonNewBuilding, edit);
    }
    
    void loadRooms(Grid<Room> roomTable) {
        roomTable.setItems(roomClient.selectAll());
    }
    
    private void initRooms(Layout layout) {
        Label roomHeader = new Label("Rooms");

        roomTable.addComponentColumn(new ValueProvider<Room, Button> () {
            @Override
            public Button apply(Room source) {
                Button button = new Button("Delete");
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        roomClient.remove(source.getId());
                        loadRooms(roomTable);
                    }
                });
                button.setIcon(FontAwesome.REMOVE);
                return button;
            }
            
        });
        loadRooms(roomTable);
              
        TextField numberTF = new TextField("Number");
        Button buttonNewRoom = new Button("Add new room");
        buttonNewRoom.setIcon(FontAwesome.SEND);
        buttonNewRoom.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                Optional<Building> building = buildingTable.getSelectionModel().getFirstSelectedItem();
                Optional<Person> person = personTable.getSelectionModel().getFirstSelectedItem();
                if (building.isPresent() && person.isPresent()) {
                    Room room = new Room();
                    room.setBuilding(building.get());
                    room.setOwner(person.get());
                    room.setNumber(numberTF.getValue());
                    roomClient.createOrUpdate(room);
                    loadRooms(roomTable);
                }
            }
        });
        
        TextField searchTF = new TextField("Searched number");
        Button search = new Button("Search room");
        search.setIcon(FontAwesome.SEARCH);
        search.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!searchTF.getValue().isEmpty()) {
                    Collection<Room> rooms = roomClient.selectAll(searchTF.getValue());
                    roomTable.setItems(rooms);
                }
                else
                    loadRooms(roomTable);
            }
        });
        
        Button edit = new Button("Edit number");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Room> room = roomTable.getSelectionModel().getFirstSelectedItem();
                if (room.isPresent()) {
                    Room r = roomClient.select(room.get().getId());
                    r.setNumber(numberTF.getValue());
                    roomClient.createOrUpdate(r);
                    loadRooms(roomTable);
                }
            }
        });
 
        layout.addComponents(searchTF, search, roomHeader, roomTable, numberTF, buttonNewRoom, edit);
    }
    
    private void loadPersons(Grid<Person> personTable) {
        personTable.setItems(personClient.selectAll());
    }
    
    private void initPersons(Layout layout) {
        Label personHeader = new Label("People");
        
        personTable.addComponentColumn(new ValueProvider<Person, Button> () {
            @Override
            public Button apply(Person source) {
                Button button = new Button("Delete");
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        personClient.remove(source.getId());
                        loadPersons(personTable);
                    }
                });
                button.setIcon(FontAwesome.REMOVE);
                return button;
            }
            
        });
        
        loadPersons(personTable);
        TextField nameTF = new TextField("Name");
        
        Button buttonNewPerson = new Button("Add person");
        buttonNewPerson.setIcon(FontAwesome.SEND);
        buttonNewPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Person person = new Person();
                person.setName(nameTF.getValue());
                personClient.createOrUpdate(person);
                loadPersons(personTable);
            }
        });
        
        TextField searchTF = new TextField("Searched name");
        Button search = new Button("Search person");
        search.setIcon(FontAwesome.SEARCH);
        search.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!searchTF.getValue().isEmpty()) {
                    Collection<Person> persons = personClient.selectAll(searchTF.getValue());
                    personTable.setItems(persons);
                }
                else
                    loadPersons(personTable);
            }
        });
        
        Button edit = new Button("Edit name");
        edit.setIcon(FontAwesome.EDIT);
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Optional<Person> person = personTable.getSelectionModel().getFirstSelectedItem();
                if (person.isPresent()) {
                    Person p = personClient.select(person.get().getId());
                    p.setName(nameTF.getValue());
                    personClient.createOrUpdate(p);
                    loadPersons(personTable);
                }
            }
        });
        
        layout.addComponents(searchTF, search, personHeader, personTable, nameTF, buttonNewPerson, edit);
        
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        final VerticalLayout buildingLayout = new VerticalLayout();
        initBuildings(buildingLayout);
        
        final VerticalLayout roomLayout = new VerticalLayout();
        initRooms(roomLayout);
        
        final VerticalLayout personLayout = new VerticalLayout();
        initPersons(personLayout);

        
        HorizontalLayout layout = new HorizontalLayout(personLayout, roomLayout, buildingLayout);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "RoomUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = RoomUI.class, productionMode = false)
    public static class RoomUIServlet extends VaadinServlet {
    }
}