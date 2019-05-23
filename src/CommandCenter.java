import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class CommandCenter extends Building implements Creatable {
	private static final String CENTER_PATH = "assets/buildings/command_centre.png";

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
		
		if(isSelect() && input.isKeyPressed(input.KEY_1) ) {
		
			try {
				world.sprites.wait(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			world.sprites.add(new Engineer(getX(), getY(), getCamera()));
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
		g.drawString("1- create factory\n2- create builder\n3- create engineer", 32, 100);
		}
	}
}
	
	