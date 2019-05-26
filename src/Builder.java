import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Builder extends Unit implements Creatable {
	private static final String BUILDER_PATH = "assets/units/builder.png";
	private static final double SPEED = 0.1;
	// record if the Key_1 is press down. 
	private boolean input1 = false;
	// count time(timer).
	private int count = 0;
	
	/**
	 * builder's constructor
	 * @param x builder's x coordinate
	 * @param y builder's y coordinate
	 * @param camera camera
	 * @throws SlickException
	 */
	public Builder(double x, double y, Camera camera) throws SlickException {
		super(x, y, BUILDER_PATH, SPEED, camera );
	}
	
	/**
	 * builder creates factory
	 * @param input input string name
	 * @param x builder's x coordinate
	 * @param y builder's y coordinate 
	 */
	@Override
	public Factory create(String input, double x, double y) throws SlickException{
		return new Factory(x, y, getCamera());
	}
	
	/**
	 * if builder creates factory, it cannot be moved
	 * @param world game world
	 */
	@Override
	public void update(World world) throws SlickException {
		Input input = world.getInput();
		int delta = world.getDelta();
		if (input.isKeyDown(Input.KEY_1)) {
			this.input1 = true;
		}
		if (input1 && isSelect() && world.getMetal() >= world.FACTORY_COST_METAL) {
			count += delta;
			if (count >= world.FACTORY_BUILD_TIME) {
				input1 = false;
			}
		} else {
			super.update(world);
		}
	}
	
	/**
	 * render builder,selected image and text display
	 * @param g render the builder's function 
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
			g.drawString("1- create factory", 32, 100);
		}
		
	}
}
