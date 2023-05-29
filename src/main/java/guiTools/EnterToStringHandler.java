package guiTools;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

public class EnterToStringHandler implements EventHandler<KeyEvent>
{

    Consumer<KeyCode> consumer;

    public EnterToStringHandler(Consumer<KeyCode> consumer)
    {
        this.consumer = consumer;
    }

    @Override
    public void handle(KeyEvent event)
    {
        KeyCode kc = event.getCode();

        if((kc.equals(KeyCode.ENTER)))consumer.accept(kc);
    }
}
