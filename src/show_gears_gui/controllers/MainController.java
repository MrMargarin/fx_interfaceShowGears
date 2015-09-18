package show_gears_gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mai_n.Logic_main;
import mai_n.MyException;
import show_gears_gui.pojo.*;



import java.io.IOException;
import java.sql.SQLException;
/**
 * Created by Rimskii on 12.09.2015.
 */
public class MainController{


    private Logic_main mainThread;



    @FXML
    public GridPane botGridPane;//======private JPanel botGridPane; //панель с кнопками и поиском
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
    public void searchAction(ActionEvent actionEvent) {}
    @FXML
    public void mkOrdAction(ActionEvent actionEvent) {}
    @FXML
    public void disconAction(ActionEvent actionEvent) {
        try {
            mainThread.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        autPane.setVisible(true);

    }
    //public TableView STUSTable, STIPPTable, ProdOrdTable, OrderListTable; //Таблицы












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


