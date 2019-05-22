import org.newdawn.slick.SlickException;

/**
 * 
 *
 */
public class Pylon extends Building {
	
	private static final String PYLON_PATH = "assets/buildings/pylon.png";
	private static final String PYLONACTIVE_PATH = "assets/buildings/pylon_active.png";
	private static final int ACTIVE_DISTANCE = 32;
	
	public Pylon(double x, double y, Camera camera) throws SlickException {
		super(x, y, PYLON_PATH, camera);
	}
	
	// detect whether the unit closes to the pylon
	public void active(World world) {
		
	}
	
	@Override
	public void update(World world) {}
}
