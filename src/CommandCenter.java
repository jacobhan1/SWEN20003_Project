import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class CommandCenter extends Building implements Creatable {
	private static final String CENTER_PATH = "assets/buildings/command_centre.png";
	public float count = 0;
	public CommandCenter(double x, double y, Camera camera) throws SlickException{
		super(x, y, CENTER_PATH, camera);
	}
	
	@Override
	public Unit create(int input, double x, double y) {
			
			try {
				return new Engineer(x, y, getCamera());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		return null;
	}
	@Override
	public void update(World world) throws SlickException{ 
		super.update(world);
		Input input = world.getInput();
		int delta = world.getDelta();
		
		if(isSelect() && input.isKeyPressed(Input.KEY_2)) {
			count += delta;
			if(count > FACTORY_BUILD_TIME) {
				world.setSprites(create(1, getX(), getY()));
				
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
		g.drawString("1- create factory\n2- create builder\n3- create engineer\n count" + count, 32, 100);
		}
	}
}
	
	