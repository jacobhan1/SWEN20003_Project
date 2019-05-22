import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Unobtainium extends Resource {
	private static final String UNOBTAINIUN_PATH = "assets/resources/unobtainium_mine.png";
	private static final int Unobtainium_AMOUNT = 500;
	
	
	public Unobtainium(double x, double y, Camera camera) throws SlickException{
		super(x, y, UNOBTAINIUN_PATH, Unobtainium_AMOUNT, camera);
	}

}
