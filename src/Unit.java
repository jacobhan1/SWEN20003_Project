import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Unit in the world has four types: scout, engineer, truck, builder
 * it could be selected, move and may could mine or create new sprite
 */
public abstract class Unit extends Sprite implements Selectable{
	private double speed;
	// Initially, we don't need to move at all
	private double targetX = getX();
	private double targetY = getY();
	private static final String HIGHLIGHT = "assets/highlight.png";
	private Image selectImage;
	
	/**
	 * Unit constructor
	 * @param x unit x coordinate
	 * @param y unit y coordinate
	 * @param imageSrc unit image address
	 * @param speed unit speed
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Unit(double x, double y, String imageSrc, double speed,
			Camera camera) throws SlickException{
		super(x, y, imageSrc, camera);
		this.speed = speed;
		
	} 
	
	/**
	 * if mouse is right button down and unit is selected, go move!
	 * @param world game world
	 */
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
	
	/**
	 * move the unit in the world
	 * @param world game world
	 * @param targetX mouse x coordinate
	 * @param targetY mouse y coordinate
	 */
	public void moves(World world, double targetX, double targetY) {
		// If we're close to our target, reset to our current position
		if (World.distance(getX(), getY(), targetX, targetY) <= getSpeed()) {
			resetTarget();
		} else {
			// Calculate the appropriate x and y distances
			double theta = Math.atan2(targetY - getY(), targetX - getX());
			double dx = (double)Math.cos(theta) * world.getDelta() * speed;
			double dy = (double)Math.sin(theta) * world.getDelta() * speed;
			// Check the tile is free before moving; otherwise, we stop moving
			if (world.isPositionFree(getX() + dx, getY() + dy)) {
				setX(getX() + dx);
				setY(getY() + dy);
			} else {
				resetTarget();
			}
		}
	}
	
	/**
	 * render the unit in the world
	 * @param g Graphics
	 */
	@Override
	public void render(Graphics g) {
		if(isSelect()) {
		selectImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
		}
		super.render(g);
	}
	
	/**
	 * if mouse is right down, record the mouse x coordinate
	 * @return mouse x coordinate
	 */
	public double getTargetX() {return targetX;}
	
	/**
	 * if mouse is right down, record the mouse y coordinate 
	 * @return mouse y coordinate
	 */
	public double getTargetY() {return targetY;}
	
	/**
	 * get sprite's speed
	 * @return sprite's speed
	 */
	public double getSpeed() {return speed;}
	
	/**
	 * reset the target coordinate to unit coordinate
	 */
	public void resetTarget() {
		targetX = getX();
		targetY = getY();		
	}
	
	/**
	 * deSelect the unit
	 */
	@Override
	public void deSelect() throws SlickException {
		selectImage = null;
	}
	
	/**
	 * select the unit
	 */
	@Override
	public void select() throws SlickException {
		selectImage = new Image(HIGHLIGHT);
	}
	
	/**
	 * if the unit is selected
	 */
	@Override
	public boolean isSelect() {
		return selectImage != null;
	} 
}
