import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Unobtainium extends Resource {
	private static final String UNOBTAINIUN_PATH = "assets/resources/unobtainium_mine.png";
	
	public Unobtainium(double x, double y) throws SlickException{
		super(x, y, UNOBTAINIUN_PATH);
	}

}
