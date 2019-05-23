import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Unit extends Sprite implements Movable, Selectable{
	private double speed;
	// Initially, we don't need to move at all
	private double targetX = getX();
	private double targetY = getY();
	private static final String HIGHLIGHT = "assets/highlight.png";
	private Image selectImage;
	
	// Units constructor
	public Unit(double x, double y, String imageSrc, double speed,
			Camera camera) throws SlickException{
		super(x, y, imageSrc, camera);
		this.speed = speed;
		
	} 
	public double getTargetX() {return targetX;}
	public double getTargetY() {return targetY;}
	public double getSpeed() {return speed;}
	
	public void resetTarget() {
		targetX = getX();
		targetY = getY();		
	}
	
	@Override
	public void deSelect() throws SlickException {
		selectImage = null;
	}
	
	@Override
	public void select() throws SlickException {
		selectImage = new Image(HIGHLIGHT);
	}
	@Override
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	@Override
	public boolean isSelect() {
		return selectImage != null;
	} 
	
	public void moves(World world, double targetX, double targetY) {
		// If we're close to our target, reset to our current position
		if (World.distance(getX(), getY(), targetX, targetY) <= getSpeed()) {
			resetTarget();
		} else {
			// Calculate the appropriate x and y distances
			double theta = Math.atan2(targetY - getY(), targetX - getX());
			double dx = (double)Math.cos(theta) * world.getDelta() * getSpeed();
			double dy = (double)Math.sin(theta) * world.getDelta() * getSpeed();
			// Check the tile is free before moving; otherwise, we stop moving
			if (world.isPositionFree(getX() + dx, getY() + dy)) {
				setX(getX() + dx);
				setY(getY() + dy);
			} else {
				resetTarget();
			}
		}
	}
	
	@Override
	public void update(World world) throws SlickException {
		Input input = world.getInput();
		
		// If the mouse button is being clicked, set the target to the cursor location
		if (isSelect() && input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			
			targetX = getCamera().screenXToGlobalX(input.getMouseX());
			targetY = getCamera().screenYToGlobalY(input.getMouseY());
		}
		
		moves(world, targetX, targetY);
	}
	
	@Override
	public void render(Graphics g) {
		if(isSelect()) {
		selectImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
		}
		super.render(g);
	}
}
