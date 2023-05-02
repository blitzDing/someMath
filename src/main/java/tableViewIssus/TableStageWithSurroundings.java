package tableViewIssus;

import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import guiTools.ColorInterface;

import java.util.ArrayList;
import java.util.List;

public class TableStageWithSurroundings<M>
{

    private TableView<M> tv;
    private ObservableList<M> data;
    private TextArea info;
    private ImageView imageV;
    private javafx.stage.Stage stage;
    private boolean wrapTxt = true;

    public static List<Integer> getMonotoneColumnSizeList(int n, int size)
    {
        List<Integer> columnSizeList = new ArrayList<>();
    	
    	for(int m=0;m<n;m++)columnSizeList.add(size);

    	return columnSizeList;
    }
    
    public TableStageWithSurroundings(double width, double height, ColorInterface<M> colorRule, 
    		String title, List<Button> btnList, List<String> columns, List<String> columnsaccess, 
    		boolean picOrNoPic, int columnSize, int infoBoxWidth)
    {
        this(width, height, colorRule,  title,  btnList,  columns,  columnsaccess,  picOrNoPic, getMonotoneColumnSizeList(columns.size(), columnSize), infoBoxWidth);    	
    }
    
  //Remember: Some code Breaks because of the new Stuff.(for Example Width and Height in the Constructor).
    public TableStageWithSurroundings
            (double width, double height, ColorInterface<M> colorRule, String title, List<Button> btnList,
            		List<String> columns, List<String> columnsaccess, boolean picOrNoPic, 
            		List<Integer> columnSize, int infoBoxWidth)
    {

        int cw[]= new int[columns.size()];
        //Adjusted
        for(int n=0;n<columns.size();n++)cw[n] = columnSize.get(n);//Adjusted
        //It's a must that data is not null when tableSetup is
        //proceeding

        info = new TextArea();
        info.setWrapText(wrapTxt);
        info.getStyleClass().add("generic-node");
        info.getStyleClass().add("info-box");
        info.setPrefWidth(infoBoxWidth);
        
        data = FXCollections.observableArrayList();
        
        tv = TableCreatorFlexible.tableSetup(colorRule, data, columns, columnsaccess, cw);
        tv.setEditable(false);
        tv.autosize();
        tv.getStyleClass().add("generic-node");
        
        Scene scene = new Scene(new Group());
        stage = new Stage();

        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setTitle(title);

        btnSetup(btnList);

        final VBox vBoxCntrl = new VBox();
        vBoxCntrl.getStyleClass().add("generic-node");
        vBoxCntrl.getStyleClass().add("pane-box");
        
        vBoxCntrl.getChildren().addAll(btnList);
        imageV = new ImageView();
        imageV.getStyleClass().add("image-view");
        imageV.getStyleClass().add("generic-node");
        
        final VBox vBoxView = new VBox();
        vBoxView.getStyleClass().add("generic-node");
        vBoxView.getStyleClass().add("pane-box");
        
        final HBox overBox = new HBox();
        overBox.getStyleClass().add("generic-node");
        overBox.getStyleClass().add("pane-box");

        if(!picOrNoPic)overBox.getChildren().addAll(vBoxCntrl, tv, info);
        else
        {
        	vBoxView.getChildren().addAll(info, imageV);
        	vBoxView.getStyleClass().add("pane-box");
        	overBox.getChildren().addAll(vBoxCntrl, tv, vBoxView);
        }
        
        
        VBox.setVgrow(info, Priority.ALWAYS);
        VBox.setVgrow(imageV, Priority.ALWAYS);
        HBox.setHgrow(vBoxCntrl, Priority.ALWAYS);
        HBox.setHgrow(tv, Priority.ALWAYS);
        HBox.setHgrow(vBoxView, Priority.ALWAYS);
        VBox.setVgrow(overBox, Priority.ALWAYS);
        
        ((Group) scene.getRoot()).getChildren().add(overBox);
    }

    private void btnSetup(List<Button> btnList)
    {
        for(Button btn: btnList)
        {
            btn.setMaxWidth(Integer.MAX_VALUE);
        }
    }

    public ObservableList<M> getObservableList()
    {
        return data;
    }

    public TableView<M> getTableView()
    {
        return tv;
    }

    public Stage getStage(){return stage;}

    public void setInfoText(String txt)
    {
        info.setText(txt);
    }

    public void  setImage(Image img)
    {

        imageV.setImage(img);
        imageV.setFitWidth(400);
        imageV.setPreserveRatio(true);
        imageV.setSmooth(true);
    }
}