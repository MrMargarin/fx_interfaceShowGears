package show_gears_gui.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import mai_n.Logic_main;
import mai_n.MyException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import show_gears_gui.Main;




/**
 * Created by Rimskii on 12.09.2015.
 */
public class MainController extends AnchorPane{


    private show_gears_gui.Main2 application;
    private Logic_main mainThread;



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    public GridPane botGridPane;//======private JPanel botGridPane; //������ � �������� � �������
    @FXML
    public AnchorPane stusPane;//======JTable STUSTable
    @FXML
    public AnchorPane stippPane;//=*=
    @FXML
    public SplitPane global_split_pane;//=====private JSplitPane global_split_pane;
    @FXML
    public SplitPane stus_stipp_pane;//==========private JSplitPane stus_stipp_pane
    @FXML
    public AnchorPane sidePane; // panel for make orders;
    @FXML
    public TextField searchField;//===========private JTextField searchField;

    //public ComboBox Kategorii;//=========private JComboBox Kategorii;

    //public ScrollPane scPfSTUSTable, scPfSTIPPTable, scPfPrOrdTable, scPfOrdLstTable; //   "scPf" - scroll panel for

    @FXML private TableView stus_TabV, ordProdTabV, stippTabV;


    @FXML private TextField loginField;
    @FXML private PasswordField passField;


    @FXML AnchorPane autPane;

    @FXML
    public Button showOrdersList;
    @FXML
    public Button searchBtn;
    @FXML
    public Button mkOrdBtn;
    @FXML
    public Button disconnBtn;
    @FXML
    public Button connBtn;

    @FXML private ComboBox Kategorii;


    @FXML
    public void showOrderLstAction(ActionEvent actionEvent) {
        try {
            mainThread.build_Order_Tables();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        mainThread.getOrderListTabMod().buildData();
        orderListTabV.setItems(mainThread.getOrderListTabMod().getData());

        //Add change listener
        orderListTabV.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (orderListTabV.getSelectionModel().getSelectedItem() != null) {
                //some///
            }
        });

        orderListTabV.setVisible(true);
    }
    @FXML
    public void searchAction(ActionEvent actionEvent) {
        try {
            mainThread.mkSTUSsearch(searchField.getText(), Kategorii.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList colList = new ArrayList();
        colList.add("Номер");colList.add("Наименование");colList.add("Категория");





       // stus_TabV.setItems(mainThread.getMainTableMod().getData());

        stus_TabV.getItems().addAll(mainThread.getMainTableMod().getData());
        //stus_TabV.itemsProperty().bind((ObservableValue) mainThread.getMainTableMod().getData());
        stus_TabV.setVisible(true);
        stusPane.getChildren().add(stus_TabV);

    }
    @FXML
    public void mkOrdAction(ActionEvent actionEvent) {}
    @FXML
    public void disconAction(ActionEvent actionEvent) {
        application.logOut();

    }
    //public TableView STUSTable, STIPPTable, ProdOrdTable, OrderListTable; //�������

    public void setApp(show_gears_gui.Main2 application, Logic_main mainThread){
        this.application = application;
        this.mainThread = mainThread;
        if(mainThread.getUser().getType()==1)
            setSaleInterface();
        else{
            setPrchInterface();
        }
    }

    private void setSaleInterface()
    {
        stus_TabV = new TableView();

        try {
            mainThread.build_STUSnSTIPP();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Kategorii.setItems(mainThread.getCats().getValuesOL());
        /*stus_TabV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // just in case you didnt already set the selection model to multiple selection.
        stus_TabV.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                if (change.getList().size() == 1) {

                    try {
                        mainThread.mkSTIPP(change.getList().get(0).toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

        });*/

        searchField.setVisible(true);
        Kategorii.setVisible(true);
        disconnBtn.setVisible(true);
        mkOrdBtn.setVisible(true);
        searchBtn.setVisible(true);
        stippPane.setVisible(true);
        stusPane.setVisible(true);




    }

    private void setPrchInterface()
    {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    }











    @FXML
    private TableView orderListTabV;

    public void connectAction(ActionEvent actionEvent) {
        try {
            mainThread = new Logic_main(loginField.getText(), passField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        autPane.setVisible(false);
        disconnBtn.setVisible(true);

        if(mainThread.getUser().getType()==1)//sale
        {
            searchField.setVisible(true);
            searchBtn.setVisible(true);
            Kategorii.setVisible(true);

        }
        if(mainThread.getUser().getType()==2)//prch
        {
            showOrdersList.setVisible(true);
            showOrdersList.setDisable(false);
        }

    }

    public void mousClickActionOrdLst(Event event) {
    }
    //@FXML
    //private TableView<OrderList_ob> orderListTabV;
    //@FXML
    //private TableColumn<OrderList_ob, Integer> id;
    //@FXML
    //private TableColumn<OrderList_ob, String> comment;
  //  @FXML
//    private TableColumn<OrderList_ob, String> manager;







}


