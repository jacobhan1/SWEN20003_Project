import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * commandCenter class 
 */
public class CommandCenter extends Building implements Creatable {
	private static final String CENTER_PATH = "assets/buildings/command_centre.png";
	
	/**
	 * constructor of commandcenter
	 * @param x commandCenter x coordinate
	 * @param y commandCenter y coordinate
	 * @param camera camera
	 * @throws SlickException
	 */
	public CommandCenter(double x, double y, Camera camera) throws SlickException{
		super(x, y, CENTER_PATH, camera);
	}
	
	/**
	 * create Unit depend on user input
	 * @param input user input value
	 * @param x unit x coordinate
	 * @param y unit y coordinate
	 */
	@Override
	public Unit create(String input, double x, double y) throws SlickException {
		switch(input) {
		case "input1": return new Scout(x, y, getCamera());
		case "input2": return new Builder(x, y, getCamera());
		case "input3": return new Engineer(x, y, getCamera());
		}
		return null;
	}
	
	/**
	 * render the commandCenter
	 * @param g render the function of commandCenter
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
			g.drawString("1- create Scout\n2- create builder\n3- create engineer" , 32, 100);
		}
	}
}
	
	