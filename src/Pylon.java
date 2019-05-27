import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * pylon when unit close to them, it will active
 */
public class Pylon extends Building {
	private static final String PYLON_PATH = "assets/buildings/pylon.png";
	private static final String PYLONACTIVE_PATH = "assets/buildings/pylon_active.png";
	private Image activeImage;
	private int count = 1;
	
	/**
	 * pylon constructor
	 * @param x pylon x coordinate
	 * @param y pylon y coordinate
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Pylon(double x, double y, Camera camera) throws SlickException {
		super(x, y, PYLON_PATH, camera);
	}
	
	/**
	 * when a unit close to it, it will be active forever
	 * @param world game world 
	 */
	@Override
	public void update(World world) throws SlickException {
		super.update(world);
		
		for (Sprite sprite: world.getSprites()) {
			if (sprite instanceof Unit && World.distance(sprite.getX(), sprite.getY(), 
					getX(), getY()) <= World.DISTANCE) {
				activeImage = new Image(PYLONACTIVE_PATH);
			}
			if(sprite instanceof Engineer && activeImage != null && count == 1) {
				count++;
				((Engineer)sprite).setCarryResource(1);
			}
		}
	}
	
	/**
	 * render the pylon or active pylon
	 * @param g Graphics
	 */
	@Override
	public void render(Graphics g) {
		if(activeImage != null) {
			if (isSelect()) {
				getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
			}
			activeImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
			
		}else {
			if (isSelect()) {
				getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
			}
			super.render(g);		
		}
		
	}
}
