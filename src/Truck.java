import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Truck extends Unit implements Creatable, Removable {
	
	private static final String TRUCK_PATH = "assets/units/truck.png";
	private static final double SPEED = 0.25;
	private boolean input1 = false;
	private int count = 0;
	
	public Truck(double x, double y, Camera camera) throws SlickException{
		super(x, y, TRUCK_PATH, SPEED, camera);
	}
	
	@Override
	public void update(World world) throws SlickException {
		Input input = world.getInput();
		int delta = world.getDelta();
		if (input.isKeyDown(Input.KEY_1)) {
			this.input1 = true;
		}
		if (input1 && isSelect()) {
			count += delta;
			if (count >= world.COMMANDCENTRE_BUILD_TIME) {
				input1 = false;
			}
		} else {
			super.update(world);
		}
	}
	@Override
	public CommandCenter create(String input, double x, double y) {
			try {
				return new CommandCenter(getX(), getY(), getCamera());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Override
	public void remove() {setImage(null);}
	
	@Override
	public void render(Graphics g) {
		if(getImage() != null) {
			super.render(g);
			getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
			if (isSelect()) {
				g.drawString("1- create command centre", 32, 100);
			}
		}
		
	}
}
