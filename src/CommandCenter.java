import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CommandCenter extends Building implements Creatable {
	private static final String CENTER_PATH = "assets/buildings/command_centre.png";
	
	public CommandCenter(double x, double y) throws SlickException{
		super(x, y, CENTER_PATH);
	}

}
