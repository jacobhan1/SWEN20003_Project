import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * truck is kind of unit in the world. It could create the commandCenter.
 */
public class Truck extends Unit implements Creatable, Removable {
	
	private static final String TRUCK_PATH = "assets/units/truck.png";
	private static final double SPEED = 0.25;
	private boolean input1 = false;
	private int count = 0;
	
	/**
	 * truck constructor
	 * @param x truck x coordinate
	 * @param y truck y coordinate
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Truck(double x, double y, Camera camera) throws SlickException{
		super(x, y, TRUCK_PATH, SPEED, camera);
	}
	
	/**
	 * if it creates sprite, it sould not move
	 * @param world game world
	 */
	@Override
	public void update(World world) throws SlickException {
		Input input = world.getInput();
		int delta = world.getDelta();
		if (input.isKeyDown(Input.KEY_1)) {
			this.input1 = true;
		}
		if (input1 && isSelect()) {
			count += delta;
			if (count >= World.COMMANDCENTRE_BUILD_TIME) {
				input1 = false;
			}
		} else {
			super.update(world);
		}
	}
	
	/**
	 * create the commandCenter
	 * @param input user input name
	 * @param x truck x coordinate
	 * @param y truck y coordinate
	 */
	@Override
	public CommandCenter create(String input, double x, double y) {
			try {
				return new CommandCenter(getX(), getY(), getCamera());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * after truck creates the commanCenter, it should be removed from the world.
	 */
	@Override
	public void remove() {setImage(null);}
	
	/**
	 * render the truck and the function in the world
	 * @param g render the truck's function
	 */
	@Override
	public void render(Graphics g) {
		if (getImage() != null) {
			super.render(g);
			getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
			if (isSelect()) {
				g.drawString("1- create command centre", 32, 100);
			}
		}
		
	}
}
