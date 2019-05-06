import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Sprite {
	
	private double x;
	private double y;
	private Image image;
	private Camera camera;
	
	// Building and resource constructor
	public Sprite(double x, double y, String imageSrc) throws SlickException  {
		setUp(x, y, imageSrc);
	}
	
	// Units constructor
	public Sprite(double x, double y, String imageSrc, Camera camera) throws SlickException{
		setUp(x, y, imageSrc);
		this.camera = camera;
		camera.followSprite(this);
	} 
	
	// Set x, y and image.
	private void setUp(double x, double y, String imageSrc) throws SlickException{
		this.x = x;
		this.y = y;
		image = new Image(imageSrc);
	}
	
	public double getX() {return x;}
	public double getY() { return y;}
	public Camera getCamera() {return camera;}
	public Image getImage() {return image;}
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	public void setImage(String imageSrc) throws SlickException {
		this.image = new Image(imageSrc);
	}

	
	public void update(World world) { }
	
	public void render() {}
}
