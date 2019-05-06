import org.newdawn.slick.SlickException;

/**
 * 
 *
 */
public class Pylon extends Building {
	private static final String PYLON_PATH = "assets/buildings/pylon.png";
	private static final String PYLONACTIVE_PATH = "assets/buildings/pylon_active.png";
	
	public Pylon(double x, double y) throws SlickException {
		super(x, y, PYLON_PATH);
	}

}
