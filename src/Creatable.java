
import org.newdawn.slick.Input;
/**
 * @author ASUS
 *
 */
public interface Creatable {
	public static final int UNIT_BUILD_TIME = 5;
	public static final int FACTORY_BUILD_TIME = 10;
	public static final int COMMANDCENTRE_BUILD_TIME = 15;
	public static final int FACTORY_COST_METAL = 100;
	public static final int SCOUT_COST_METAL = 5;
	public static final int BUILDER_COST_METAL = 10;
	public static final int ENGINEER_COST_METAL = 20;
	public static final int TRUCK_COST_METAL = 150;
	
	public Sprite create(int input, double x, double y);
}
