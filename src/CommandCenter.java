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
	public Unit create(String input, double x, double y) throws SlickException {
		switch(input) {
		case "input1": return new Scout(x, y, getCamera());
		case "input2": return new Builder(x, y, getCamera());
		case "input3": return new Engineer(x, y, getCamera());
		}
		return null;
	}
	
	public boolean isCreate(Input input) {
		if(isSelect() && input.isKeyPressed(Input.KEY_1)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
			g.drawString("1- create Scout\n2- create builder\n3- create engineer" , 32, 100);
		}
	}
}
	
	