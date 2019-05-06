import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Sprite {
	private static final String SCOUT_PATH = "assets/scout.png";
	private static final double SPEED = 0.25;
	
	private double x = 812;
	private double y = 684;
	
	// Initially, we don't need to move at all
	private double targetX = x;
	private double targetY = y;
	
	private Image image;
	private Camera camera;
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public Sprite(Camera camera) throws SlickException {
		image = new Image(SCOUT_PATH);
		
		this.camera = camera;
		camera.followSprite(this);
	}
	
	public void update(World world) {
		Input input = world.getInput();
		
		// If the mouse button is being clicked, set the target to the cursor location
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			targetX = camera.screenXToGlobalX(input.getMouseX());
			targetY = camera.screenYToGlobalY(input.getMouseY());
		}
		
		// If we're close to our target, reset to our current position
		if (World.distance(x, y, targetX, targetY) <= SPEED) {
			resetTarget();
		} else {
			// Calculate the appropriate x and y distances
			double theta = Math.atan2(targetY - y, targetX - x);
			double dx = (double)Math.cos(theta) * world.getDelta() * SPEED;
			double dy = (double)Math.sin(theta) * world.getDelta() * SPEED;
			// Check the tile is free before moving; otherwise, we stop moving
			if (world.isPositionFree(x + dx, y + dy)) {
				x += dx;
				y += dy;
			} else {
				resetTarget();
			}
		}
	}
	
	private void resetTarget() {
		targetX = x;
		targetY = y;		
	}
	
	public void render() {
		image.drawCentered((int)camera.globalXToScreenX(x),
						   (int)camera.globalYToScreenY(y));
	}
}
