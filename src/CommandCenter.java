import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class CommandCenter extends Building implements Creatable {
	private static final String CENTER_PATH = "assets/buildings/command_centre.png";
	
	public CommandCenter(double x, double y) throws SlickException{
		super(x, y, CENTER_PATH);
	}
	
	@Override
	public Unit create(int input, double x, double y) {
		// KEY_1, KEY_2, KEY_3 different
			try {
				return new Truck(getX(), getY());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		
		return null;
	}
}
