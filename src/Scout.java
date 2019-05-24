import org.newdawn.slick.SlickException;

public class Scout extends Unit {

	private static final String SCOUT_PATH = "assets/units/scout.png";
	private static final double SPEED = 0.3;
	
	public Scout(double x, double y, Camera camera) throws SlickException {
		super(x, y, SCOUT_PATH, SPEED, camera);
	}
	
	
}
