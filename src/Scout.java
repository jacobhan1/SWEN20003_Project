import org.newdawn.slick.SlickException;

public class Scout extends Unit implements Movable{

	private static final String SCOUT_PATH = "assets/units/scout.png";
	private static final double SPEED = 0.3;
	
	public Scout(double x, double y, String imageSrc, Camera camera) throws SlickException {
		super(x, y, SCOUT_PATH, camera, SPEED);
	}
	
	
}
