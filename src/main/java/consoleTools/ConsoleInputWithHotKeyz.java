package consoleTools;

import java.util.Scanner;

public class ConsoleInputWithHotKeyz
{

	public ConsoleInputWithHotKeyz()
	{
		
		// Start a separate thread to listen for hotkeys
        Thread hotkeyThread = new Thread(() -> 
        {
        
        	try
        	{
        		
        		while(true)
        		{

        			int key = System.in.read(); // Read a key from System.in

                    // Check if the user pressed the Up arrow key
//                    if (key == 27 && System.in.read() == 91 && System.in.read() == 65)
                    {
                        System.out.println("You pressed: " + key);
                        // Implement your history functionality here
                    }
                }
            }
        	catch (Exception e)
        	{
                e.printStackTrace();
            }
        });
        
        
        hotkeyThread.setDaemon(true); // Set the thread as daemon so it won't prevent JVM from exiting
        hotkeyThread.start();

        // Main thread waits for line input from Scanner
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.print("Enter a command: ");
            String line = scanner.nextLine();
            // Process the input line
            System.out.println("You entered: " + line);
        }
	}
	
	public static void main(String[] args)
    {

		new ConsoleInputWithHotKeyz();
    }
}