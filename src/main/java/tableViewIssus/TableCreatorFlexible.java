package tableViewIssus;


import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.paint.Color;

import javafx.scene.text.Text;


import java.util.List;


import guiTools.ColorInterface;


public class TableCreatorFlexible
{


	private PseudoClass childOfSelected = PseudoClass.getPseudoClass("child-of-selected");
    private PseudoClass parentOfSelected = PseudoClass.getPseudoClass("parent-of-selected");

    public static <M> TableView<M>

    tableSetup(ColorInterface<M> colorRule, ObservableList<M> data, List<String> columns, List<String> columnAccess, int[] columnWidth)
    {

        TableView<M> tv = new TableView<>();
        //List<TableColumn> tableColumnList = new ArrayList<>();
        int n = columns.size();
        int widthSum = 0;

        for(int i=0;i<n;i++)
        {
            TableColumn<M, String> tColumn = createTableColumn(data, colorRule, columnWidth[i], columns.get(i),columnAccess.get(i));
            widthSum = widthSum+ columnWidth[i];
            tv.getColumns().add(tColumn);
        }

        tv.setPrefWidth(widthSum);
        tv.setItems(data);
        return tv;
    }

    public static <M, T> TableColumn<M,T> createTableColumn(ObservableList<M> data, ColorInterface<M> colorRule, int preferedWidth, String name, String accessName)
    {
        TableColumn<M, T> tColumn = new TableColumn<>(name);
        tColumn.setPrefWidth(preferedWidth);
        tColumn.setCellValueFactory(new PropertyValueFactory<>(accessName));

        tColumn.setCellFactory(column->
        {

            Text txt = new Text();

            return (TableCell<M, T>) new TableCell<M, T>()
            {
                @Override
                protected void updateItem(T item, boolean empty)
                {

                    super.updateItem(item, empty);


                    if (item == null || empty)
                    {
                        setGraphic(null);
                    }
                    else
                    {
                        txt.setText(item.toString());
                        txt.setWrappingWidth(preferedWidth-7);
                        setGraphic(txt);

                        TableRow<M> currentRow = getTableRow();
                        int i = currentRow.getIndex();
                        M m = data.get(i);

                        Color color = colorRule.getColor(m);
                        double d = colorRule.getHowStrongTheColor(m);

                        String rgb = String.format("%d, %d, %d",
                                (int) (color.getRed() * 255),
                                (int) (color.getGreen() * 255),
                                (int) (color.getBlue() * 255));

                        //currentRow.getStyleClass().add("");
                        //currentRow.setStyle("-fx-background-color: rgba(" + rgb + ", "+d+");");

                    }
                }

            };
        });

        return tColumn;
    }    
    
}