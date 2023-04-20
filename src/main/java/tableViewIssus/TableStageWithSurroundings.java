package tableViewIssus;

import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import guiTools.ColorInterface;

import java.util.List;

public class TableStageWithSurroundings<M>
{

    private TableView<M> tv;
    private ObservableList<M> data;
    private TextArea info;
    private ImageView imageV;
    private javafx.stage.Stage stage;
    private boolean wrapTxt = true;

    public TableStageWithSurroundings
            (ColorInterface<M> colorRule, String title, List<Button> btnList, List<String> columns, List<String> columnsaccess, boolean picOrNoPic)// throws DataMisfitException
    {

        int cw[]= new int[columns.size()];
        cw[0]=100;cw[columns.size()-1]=300;//Adjusted
        for(int n=1;n<columns.size()-1;n++)cw[n] = 150;//Adjusted
        //It's a must that data is not null when tableSetup is
        //proceeding

        info = new TextArea();
        info.setWrapText(wrapTxt);
        data = FXCollections.observableArrayList();
        tv = TableCreatorFlexible.tableSetup(colorRule, data, columns, columnsaccess, cw);
        tv.setEditable(false);


        Scene scene = new Scene(new Group());
        stage = new Stage();

        stage.setScene(scene);
        stage.setWidth(1400);
        stage.setHeight(650);
        stage.setTitle(title);

        btnSetup(btnList);

        final VBox vBoxCntrl = new VBox();
        vBoxCntrl.setSpacing(5);
        vBoxCntrl.setPadding(new Insets(5, 5, 5, 5));
        vBoxCntrl.getChildren().addAll(btnList);

        imageV = new ImageView();

        final VBox vBoxView = new VBox();
        vBoxView.setSpacing(5);
        vBoxView.setPadding(new Insets(5,5,5,5));

        if(picOrNoPic)vBoxView.getChildren().addAll(info,  imageV);
        if(!picOrNoPic)vBoxView.getChildren().addAll(info);
        VBox.setVgrow(info, Priority.ALWAYS);
        VBox.setVgrow(imageV, Priority.ALWAYS);

        final HBox overBox = new HBox();
        overBox.setSpacing(5);
        overBox.setPadding(new Insets(5, 5, 5, 5));
        overBox.getChildren().addAll(vBoxCntrl, tv, vBoxView);
        HBox.setHgrow(vBoxCntrl, Priority.ALWAYS);
        HBox.setHgrow(tv, Priority.ALWAYS);
        HBox.setHgrow(vBoxView, Priority.ALWAYS);

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
        imageV.setFitWidth(200);
        imageV.setPreserveRatio(true);
        imageV.setSmooth(true);
    }
}
