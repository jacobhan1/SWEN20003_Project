import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * 
 *
 */
public class Pylon extends Building {
	
	private static final String PYLON_PATH = "assets/buildings/pylon.png";
	private static final String PYLONACTIVE_PATH = "assets/buildings/pylon_active.png";
	private static final int ACTIVE_DISTANCE = 32;
	private Image activeImage;
	
	public Pylon(double x, double y, Camera camera) throws SlickException {
		super(x, y, PYLON_PATH, camera);
	}
	
	// detect whether the unit closes to the pylon
	public void active(World world) {
		
	}
	
	@Override
	public void update(World world) throws SlickException {
		super.update(world);
		for (Sprite sprite: world.sprites) {
			if (sprite instanceof Unit && World.distance(sprite.getX(), sprite.getY(), 
					getX(), getY()) <= ACTIVE_DISTANCE) {
				activeImage = new Image(PYLONACTIVE_PATH);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(activeImage != null) {
		activeImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
		}else {
			super.render(g);		
		}
	}
}
