import org.newdawn.slick.Input;

/**
 * This class should be used to restrict the game's view to a subset of the entire world.
 */
public class Camera {
	private double x = 300;
	private double y = 300;
	private Sprite target;
	private final static double SPEED= 0.4;

	/**
	 * move left,right,up and down when press "A,D,W,s", or follow the selected sprite
	 * @param world game world
	 */
	public void update(World world) {
		Input input = world.getInput();
		int delta = world.getDelta();
		// up, down, left and right
		if (input.isKeyDown(Input.KEY_W)) {
			target = null;
			y -= delta * SPEED;
			y = detectY(y,world);
		} else if (input.isKeyDown(Input.KEY_S)) {
			target = null;
			y += delta * SPEED;
			y = detectY(y,world);
		} else if (input.isKeyDown(Input.KEY_A)) {
			target = null;
			x -= delta * SPEED;
			x = detectX(x,world);
		} else if (input.isKeyDown(Input.KEY_D)) {
			target = null;
			x += delta * SPEED;
			x = detectX(x,world);
		} else if (target != null && ((Selectable)target).isSelect() ){
		
			double targetX = target.getX() - App.WINDOW_WIDTH / 2;
			double targetY = target.getY() - App.WINDOW_HEIGHT / 2;
			
			x = detectX(targetX,world);
			y = detectY(targetY,world);
		}
	}
	
	/**
	 * follow which selected sprite
	 * @param target selected sprite in the world
	 */
	public void followSprite(Sprite target) {
		this.target = target;
	}
	
	/**
	 * global x coordinate to screen x coordinate
	 * @param x global x coordinate
	 * @return screen x coordinate
	 */
	public double globalXToScreenX(double x) {
		return x - this.x;
	}
	
	/**
	 * global y coordinate to screen y coordinate
	 * @param y global y coordinate
	 * @return screen y coordinate
	 */
	public double globalYToScreenY(double y) {
		return y - this.y;
	}

	/**
	 * screen x coordinate to global x coordinate
	 * @param x screen x coordinate
	 * @return global x coordinate
	 */
	public double screenXToGlobalX(double x) {
		return x + this.x;
	}
	
	/**
	 * screen y coordinate to global y coordinate
	 * @param y screen y coordinate
	 * @return global y coordinate
	 */
	public double screenYToGlobalY(double y) {
		return y + this.y;
	}
	
	
	// detect if x need to be change in the world, return the x value
	private double detectX(double x, World world) {
		x = Math.min(x, world.getMapWidth() - App.WINDOW_WIDTH);
		x = Math.max(x, 0);
		return x;
	}
	
	
	// detect if y need to be change in the world, return the y value 
	private double detectY(double y, World world) {
		y = Math.min(y, world.getMapHeight() - App.WINDOW_HEIGHT);
		y = Math.max(y, 0);
		return y;
	}
	
}
