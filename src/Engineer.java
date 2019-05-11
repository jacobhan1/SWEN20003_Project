import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Engineer extends Unit implements Movable{
	private static final String ENGINEER_PATH = "assets/units/engineer.png";
	private static final double SPEED = 0.1;
	private int carryResources = 2;
	private static final int MINE_TIME = 5;
	private Resource resource;
	private CommandCenter commandCenter;
	
	public Engineer(double x, double y) throws SlickException {
		super(x, y, ENGINEER_PATH, SPEED);
	}
	public void setCarryResource(int add) {carryResources += add;}
	public int getCarryResource() {return carryResources;}
	
	/*
	 * get to know if the resources could be mining anymore.
	 */
	private boolean isMineAvailable(Resource resource) {
		if (resource.getAmount() == 0) {
			return false;
		}else
			return true;
	}
	
	/*
	 * mine resources each time 
	 */
	private void mine(Resource resource) {
		resource.setAmount(carryResources);
	}
	
	private void findNearestCommandCenter(ArrayList<Sprite> sprites) {}
	
	
	
//	@Override
//	public void update(World world) {	}
}
