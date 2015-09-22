
package table_models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import mai_n.MySQLConnector;


public class STIPP_Table {
    private final String searchField = "stus_id"; //Имя поля по которому производится поиск товара(первичный ключ)
    private DefaultTableModel subTable;
    private MySQLConnector con;
    private Vector<String> colNames;
    private ObservableList<String> data;

    public STIPP_Table(MySQLConnector con)
    {
        colNames = new Vector<String>();
        colNames.add("Код товара");colNames.add("Поставщик");colNames.add("НаименованиеПоставщика");colNames.add("Цена");colNames.add("Наличие");
        this.con = con;
    }
    
    public void fillTable(String codeFieldName, String catTableName) throws SQLException
    {
        String sqls = "select * from "+catTableName+" where "+searchField+" like \'"+codeFieldName+"\'";
        ResultSet rs = con.getResultSet(sqls);
        ResultSetMetaData data = rs.getMetaData();
        //==================================read-col-names======================
        //Vector<Object> columns = new Vector<>();
        Vector<Vector<Object>> values = new Vector<>();
        int maxColumns = data.getColumnCount();
        //for (int i = 1; i <= maxColumns; i++) 
        //    columns.add(data.getColumnName(i));
        //==================================read-data===========================
        while (rs.next()) {
            Vector<Object> value = new Vector<>();
            for (int i = 2; i <= maxColumns; i++)
                value.add(con.getResultSet().getObject(i));
            values.add(value);
        }
        
        subTable = new DefaultTableModel(values, colNames);
    }

    public void buildData(String codeFieldName, String catTableName){

        setData(FXCollections.observableArrayList());
        try{
            String SQL = "select * from "+catTableName+" where "+searchField+" like \'"+codeFieldName+"\'";
            ResultSet rs = con.getResultSet(SQL);

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
            }

            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                getData().add(String.valueOf(row));
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void closeConec() throws SQLException
    {
        this.con.disconnect();
    }
    
    public DefaultTableModel getSubTab()
    { return subTable;}


    public ObservableList<String> getData() {
        return data;
    }

    public void setData(ObservableList<String> data) {
        this.data = data;
    }
}
