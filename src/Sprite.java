import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Sprite {
	// x, y are global coordinates
	private double x;
	private double y;
	
	// sprites' image
	private Image image;
	
	// sprites' constructor
	public Sprite(double x, double y, String imageSrc) throws SlickException  {
		this.x = x;
		this.y = y;
		image = new Image(imageSrc);
	}
	
	
	public double getX() {return x;}
	public double getY() { return y;}
	public Image getImage() {return image;}
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	public void setImage(Image image)  {
		this.image = image;
	}

	
	public void update(World world) { 
		
	}
	
	public void render() {
		image.drawCentered((int)x, (int)y);
		
	}
}
