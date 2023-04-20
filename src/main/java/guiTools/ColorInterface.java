package guiTools;

import javafx.scene.paint.Color;

public interface ColorInterface <C>
{

    public Color getColor(C c);

    public double getHowStrongTheColor(C c);
}
