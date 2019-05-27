import org.newdawn.slick.SlickException;
/**
 * scout is the fastest sprite in the game world
 */
public class Scout extends Unit {

	private static final String SCOUT_PATH = "assets/units/scout.png";
	private static final double SPEED = 0.3;
	
	/**
	 * Scout constructor
	 * @param x scout x coordinate
	 * @param y scout y coordinate
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Scout(double x, double y, Camera camera) throws SlickException {
		super(x, y, SCOUT_PATH, SPEED, camera);
	}
	
	
}
