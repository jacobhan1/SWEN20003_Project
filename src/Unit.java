import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Unit extends Sprite implements Movable{
	private double speed ;
	// Initially, we don't need to move at all
	private double targetX = getX();
	private double targetY = getY();
	
	// four units' constructor
	public Unit(double x, double y, String imageSrc, Camera camera, double speed) throws SlickException {
		super(x, y, imageSrc, camera);
		this.speed = speed;
	}
	
	private void resetTarget() {
		targetX = getX();
		targetY = getY();		
	}
	
	@Override
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	@Override
	public void update(World world) {
		Input input = world.getInput();
		
		// If the mouse button is being clicked, set the target to the cursor location
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			targetX = getCamera().screenXToGlobalX(input.getMouseX());
			targetY = getCamera().screenYToGlobalY(input.getMouseY());
		}
		
		// If we're close to our target, reset to our current position
		if (World.distance(getX(), getY(), targetX, targetY) <= speed) {
			resetTarget();
		} else {
			// Calculate the appropriate x and y distances
			double theta = Math.atan2(targetY - getY(), targetX - getY());
			double dx = (double)Math.cos(theta) * world.getDelta() * speed;
			double dy = (double)Math.sin(theta) * world.getDelta() * speed;
			// Check the tile is free before moving; otherwise, we stop moving
			if (world.isPositionFree(getX() + dx, getY() + dy)) {
				move(dx,dy);
			} else {
				resetTarget();
			}
		}
	}
	
	public void render() {
		getImage().drawCentered((int)getCamera().globalXToScreenX(getX()),
						   (int)getCamera().globalYToScreenY(getY()));
	}
}
