import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Sprite {
	// x, y are global coordinates
	private double x;
	private double y;
	private Camera camera;
	// sprites' image
	private Image image;
	
	// sprites' constructor
	public Sprite(double x, double y, String imageSrc, Camera camera) throws SlickException  {
		this.x = x;
		this.y = y;
		image = new Image(imageSrc);
		this.camera = camera;
	}
	
	
	public double getX() {return x;}
	public double getY() { return y;}
	public Image getImage() {return image;}
	public Camera getCamera() {return camera;}
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	public void setImage(Image image)  {
		
	}

	
	public void update(World world) throws SlickException{ 
		Input input = world.getInput();
		
		// Player pressed the left button
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			double mouseX = getCamera().screenXToGlobalX(input.getMouseX());
			double mouseY = getCamera().screenYToGlobalY(input.getMouseY());
			
			// select the Unit or Building
			for (Sprite sprite : world.getSprites()) {
				if (sprite instanceof Selectable && sprite.getX() < mouseX + 32 
						&& sprite.getX() > mouseX - 32 
						&& sprite.getY() < mouseY + 32 && sprite.getY() > mouseY - 32 ) {
					
					// if it is Unit, deselect any Building and then select Unit itself
					// or if it is building, deselect any Unit and then select building itself
					if (sprite instanceof Unit) {
						for (Sprite sprites : world.getSprites()) {
							if (sprites instanceof Building && ((Selectable)sprites).isSelect()) {
								((Selectable)sprites).deSelect();
							}
						}
						((Selectable)sprite).select();
					}else {
						for (Sprite sprites : world.getSprites()) {
							if (sprites instanceof Unit && ((Selectable)sprites).isSelect()) {
								((Selectable)sprites).deSelect();
							}
						}
						((Selectable)sprite).select();
					}	
				}else if (sprite instanceof Selectable && ((Selectable)sprite).isSelect()) {
					((Selectable)sprite).deSelect();
					
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		image.drawCentered((int)camera.globalXToScreenX(x), 
				(int)camera.globalYToScreenY(y));
		
	}
}
