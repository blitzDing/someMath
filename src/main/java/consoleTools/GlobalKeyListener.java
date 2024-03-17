package consoleTools;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class GlobalKeyListener
{

	public GlobalKeyListener()
	{
        // Create an instance of the KeyEventDispatcher
        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher()
        {
        	
            @Override
            public boolean dispatchKeyEvent(KeyEvent e)
            {
                // Handle the key event
                if (e.getID() == KeyEvent.KEY_PRESSED)
                {
                    // Key pressed event
                    int keyCode = e.getKeyCode();
                    // Handle key press based on keyCode
                    System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));
                }
                // Return false to allow further processing of the event
                return false;
            }
        };

        // Register the KeyEventDispatcher with the KeyboardFocusManager
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }
}
