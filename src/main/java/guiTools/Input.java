package guiTools;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import someMath.SmallTools;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author blazing
 */

/* Remember: getXYZInput must be able to give back nothing or at least an empty String.
             And the must react on pressing enter.(TextField or Stage. setOnKeyPressed)
 */

public class Input 
{

	public static final String styleClassNodeAlpha = "node_Alpha";
	
    public static final String yesButtonLabel = "Yeah";
    public static final String yesAnswerToQuestion = "Yes";

    public static final String noButtonLabel = "Nah";
    public static final String noAnswerToQuestion = "No";

    public static final String cancelButtonLabel = "Cancel";
    public static final String cancelAnswerToQuestion = "Canceled";
        
    public static void styleStage(Stage stage)
    {
    	
    	stage.getScene().getStylesheets().add(Input.class.getResource("/stageCss.css").toExternalForm());
    }
    
    public static Function<String, String> question = (frage)->
    {
        
    	//Forcing possible
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        
        alert.setHeaderText("Confirmation");
        alert.setTitle("Yes, No or Cancel");
        alert.setContentText(frage);

        ButtonType buttonTypeYes = new ButtonType(yesButtonLabel);
        ButtonType buttonTypeNo = new ButtonType(noButtonLabel);
        ButtonType buttonTypeCancel = new ButtonType(cancelButtonLabel);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);


        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes)return yesAnswerToQuestion;

        if(result.get() == buttonTypeNo)return noAnswerToQuestion;
        else return cancelAnswerToQuestion;
    };

    //Won't give back Null or Nothing.
    public static int getIntInput(String title, String phrase, int min, int max)
    {
    	
        Stage input = new Stage();
        
        Scene scene = new Scene(new Group());
        
        int maxDigist = SmallTools.dezimalstellenVonInt(Integer.MAX_VALUE);
        
        input.setTitle(title);
        input.setWidth(185);
        input.setHeight(150);
        input.setScene(scene);
        styleStage(input);//must be done after the stage has a scene.

        input.initStyle(StageStyle.UNDECORATED);

        Label intLabel = new Label(phrase);
        intLabel.setWrapText(true);
        intLabel.setMaxWidth(175);
        intLabel.getStyleClass().add(styleClassNodeAlpha);
        
        TextField txtF = new TextField();
        txtF.setMaxWidth(175);
        txtF.getStyleClass().add(styleClassNodeAlpha);

        Button okay = new Button("Okay");
        okay.getStyleClass().add(styleClassNodeAlpha);
        
        okay.setMaxWidth(175);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(intLabel, txtF, okay);
        vBox.getStyleClass().add(styleClassNodeAlpha);
        
        int [] value = new int[1];

        okay.setOnAction((event) ->
        {

            String intStrg = txtF.getText().strip();
            String regEx = "-?\\d{1,"+maxDigist+"}";
            boolean isNumber = intStrg.matches(regEx);
     
            value[0] = 0;
            if( isNumber )
            {
            	value[0] = Integer.parseInt(intStrg);
            	if(value[0]<min||value[0]>max)
            	{
            		Output.errorAlert("Nr. out of Bounds.");
            		value[0]=getIntInput(title, phrase, min, max);
            	}
            	
            	input.close();
            }
            else Output.errorAlert("That's no Number.");
        });

        ((Group) scene.getRoot()).getChildren().add(vBox);
        input.showAndWait();

        return value[0];
    }

    //Might give back empty String(Whitespace).
    public static String getTextInput(String title, String phrase, boolean textAreaInput)
    {

        String chosenOne[] = new String[1];

        Stage input = new Stage();
        Scene scene = new Scene(new Group());


        input.setTitle(title);
        input.setWidth(185);
        input.setHeight(250);
        input.setScene(scene);
        
        input.initStyle(StageStyle.UNDECORATED);

        Label intLabel = new Label(phrase);
        intLabel.setWrapText(true);
        intLabel.setMaxWidth(175);
        intLabel.getStyleClass().add(styleClassNodeAlpha);

        TextField txtF = new TextField();
        txtF.setMaxWidth(175);
        txtF.getStyleClass().add(styleClassNodeAlpha);
        
        TextArea txtA = new TextArea();
        txtA.setWrapText(true);
        txtA.setMaxWidth(175);
        txtA.getStyleClass().add(styleClassNodeAlpha);
        
        Button okay = new Button("Okay");
        okay.setMaxWidth(175);
        okay.getStyleClass().add(styleClassNodeAlpha);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.getStyleClass().add(styleClassNodeAlpha);
        
        if(!textAreaInput)//Entweder nur eine Zeile(txtF) oder mehrere(txtA) erscheinen.
        vBox.getChildren().addAll(intLabel, txtF, okay);
        else vBox.getChildren().addAll(intLabel,txtA,okay);


        Consumer<KeyCode> consumer= (kc) ->
        {
            if(textAreaInput)chosenOne[0] = txtA.getText();
            else chosenOne[0] = txtF.getText();
            input.close();
        };

        okay.setOnAction((event) ->
        {
            if(textAreaInput)chosenOne[0] = txtA.getText();
            else chosenOne[0] = txtF.getText();
            input.close();
        });

        ((Group) scene.getRoot()).getChildren().add(vBox);
        input.showAndWait();

        if(textAreaInput)return txtA.getText();

        return txtF.getText();
    }

    //Might give only whitespace!
    public static String getRangeInput(Set<String> setOfOptions, String title, String phrase, boolean editable)
    {

    	String[] chosenOne = new String[1];
    	
    	Stage input = new Stage();
        Scene scene = new Scene(new Group());

        input.setTitle(title);
        input.setWidth(185);
        input.setHeight(250);
        input.setScene(scene);

        input.initStyle(StageStyle.UNDECORATED);

        Label textLabel = new Label(phrase);
        textLabel.setMaxWidth(175);
        textLabel.getStyleClass().add(styleClassNodeAlpha);
        
        ComboBox<String> cbx = new ComboBox<>();
        cbx.setMaxWidth(175);
        cbx.getItems().addAll(setOfOptions);
        if(editable)cbx.setEditable(true);
        else cbx.setEditable(false);
        cbx.getSelectionModel().selectFirst();


        Button okay = new Button("Okay");
        okay.setMaxWidth(175);
        okay.getStyleClass().add(styleClassNodeAlpha);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(textLabel, cbx, okay);
        vBox.getStyleClass().add(styleClassNodeAlpha);
        
        Consumer<KeyCode> consumer= (kc) ->
        {
            chosenOne[0] = cbx.getValue();
            input.close();
        };

        okay.setOnAction((event) ->
        {
            chosenOne[0] = cbx.getValue();
            input.close();
        });

        ((Group) scene.getRoot()).getChildren().add(vBox);
        input.showAndWait();

        return chosenOne[0];
    }

    public static LocalDateTime getDateTimeInput(String title, String phrase)
    {

        LocalDateTime ldt[] = new LocalDateTime[1];

        Stage input = new Stage();
        Scene s = new Scene(new Group());
        input.setTitle(title);
        input.setWidth(185);
        input.setHeight(250);
        input.setScene(s);

        input.initStyle(StageStyle.UNDECORATED);
        Label textLabel = new Label(phrase);
        textLabel.setMaxWidth(175);
        textLabel.getStyleClass().add(styleClassNodeAlpha);
        
        DatePicker dp = new DatePicker(LocalDate.now());
        dp.setMaxWidth(175);

        int minHour = 0;
        int maxHour = 23;
        int hour = getIntInput("Hour", "Please enter Hour: ", minHour, maxHour);

        int minMinute = 0;
        int maxMinute = 59;
        int minute = getIntInput("Minute", "Please enter Minute: ", minMinute, maxMinute);

        Button okay = new Button("Okay");
        okay.setMaxWidth(175);
        okay.getStyleClass().add(styleClassNodeAlpha);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(textLabel, dp, okay);
        vBox.getStyleClass().add(styleClassNodeAlpha);
        
        okay.setOnAction((event) ->
        {
            LocalTime lt = LocalTime.of(hour, minute);
            LocalDate ld = dp.getValue();
            ldt[0] = LocalDateTime.of(ld, lt);
            if(ldt[0]!=null)input.close();
        });

        ((Group) s.getRoot()).getChildren().add(vBox);
        input.showAndWait();

        return ldt[0];
    }
}