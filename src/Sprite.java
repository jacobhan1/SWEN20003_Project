import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * this abstract class contains all sprites common attributes and methods.
 */
public abstract class Sprite {
	// x, y are global coordinates
	private double x;
	private double y;
	private Camera camera;
	// sprites' image
	private Image image;
	/**
	 * define all the sprite common attribute
	 * @param x sprite 
	 * @param y
	 * @param imageSrc
	 * @param camera
	 * @throws SlickException
	 */
	// sprites' constructor
	public Sprite(double x, double y, String imageSrc, Camera camera) throws SlickException  {
		this.x = x;
		this.y = y;
		image = new Image(imageSrc);
		this.camera = camera;
	}
	
	 /**
	  * get sprite's x coordinate
	  * @return x coordinate
	  */
	public double getX() {return x;}
	/**
	 * get sprite's y coordinate
	 * @return y coordinate
	 */
	public double getY() { return y;}
	/**
	 * get sprite's image
	 * @return sprite's image
	 */
	public Image getImage() {return image;}
	/**
	 * get camera
	 * @return camera
	 */
	public Camera getCamera() {return camera;}
	/**
	 * set sprite's x coordinate
	 * @param x sprite's x coordinate
	 */
	public void setX(double x) {this.x = x;}
	/**
	 * set sprite's y coordinate
	 * @param y  sprite's y coordinate
	 */
	public void setY(double y) {this.y = y;}
	/**
	 * set sprite's  image
	 * @param image sprite's image
	 */
	public void setImage(Image image)  { this.image = image;}

	/**
	 * select sprite in the world 
	 * @param world game world
	 * @throws SlickException
	 */
	public void update(World world) throws SlickException{ 
		Input input = world.getInput();
		
		// Player pressed the left button
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			double mouseX = getCamera().screenXToGlobalX(input.getMouseX());
			double mouseY = getCamera().screenYToGlobalY(input.getMouseY());
			
			// select the Unit or Building
			for (Sprite sprite : world.getSprites()) {
				if (World.distance(sprite.getX(), sprite.getY(), mouseX, mouseY) < World.DISTANCE ) {
					// if it is Unit, deselect any Building and then select Unit itself
					// or if it is building, deselect any Unit and then select building itself
					if (sprite instanceof Selectable) {
						for (Sprite sprites : world.getSprites()) {
							if (sprites instanceof Selectable && ((Selectable)sprites).isSelect()) {
								((Selectable)sprites).deSelect();
							}
						}
						((Selectable)sprite).select();
						sprite.getCamera().followSprite(sprite);
					}
				}else if (sprite instanceof Selectable && ((Selectable)sprite).isSelect()) {
					((Selectable)sprite).deSelect();
					
				}
			}
		}
	}
	
	/**
	 * render the sprite into the world
	 * @param g Graphics
	 */
	public void render(Graphics g) {
		image.drawCentered((int)camera.globalXToScreenX(x), 
				(int)camera.globalYToScreenY(y));
		
	}
}
