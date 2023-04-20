package tableViewIssus;

import java.util.List;

import javafx.collections.ObservableList;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class TableCreator
{

    public static <M> TableView<M> tableSetup(ObservableList<M> data, List<String> columns, List<String> columnAccess, int[] columnWidth)
    {

    	TableView<M> tv = new TableView<>();

    	int n = columns.size();
    	int widthSum = 0;

    	;
    	for(int i=0;i<n;i++)
    	{
    		TableColumn<M, String> tColumns = new TableColumn<>(columns.get(i));
    		//tColumns[i].setStyle("-fx-alignment: CENTER;");
    		final int width = columnWidth[i];
    		tColumns.setPrefWidth(columnWidth[i]);

    		tColumns.setCellValueFactory(new PropertyValueFactory<>(columnAccess.get(i)));
    		widthSum = widthSum+ columnWidth[i];
    		tv.getColumns().add(tColumns);
    		tColumns.setCellFactory(column->
    		{
            
    			Text txt = new Text();
            
    			return new TableCell<M, String>()
    			{
    				@Override
    				protected void updateItem(String item, boolean empty)
    				{
    					super.updateItem(item, empty);
                    
                    
    					if (item == null || empty)
    					{
    						setGraphic(null);
    					}
    					else
    					{
    						txt.setText(item);
    						txt.setWrappingWidth(width-7);
    						setGraphic(txt);
    					}
    				}

    			};
    		});
    	}

    	tv.setPrefWidth(widthSum);
    	tv.setItems(data);
    	return tv;
    }
}