import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Engineer extends Unit {
	private static final String ENGINEER_PATH = "assets/units/engineer.png";
	private static final double SPEED = 0.1;
	private static int carryResources = 2;
	private int playerCarryResource = 0;
	private static final float MINE_TIME = 5000;
	private Resource resource;
	private CommandCenter commandCenter;
	private double targetX = getX();
	private double targetY = getY();
	public float count = 0;
	
	public Engineer(double x, double y, Camera camera) throws SlickException {
		super(x, y, ENGINEER_PATH, SPEED, camera);
	}
	public void setCarryResource(int add) {carryResources += add;}
	public int getCarryResource() {return carryResources;}
	
	@Override
	public void update(World world) throws SlickException {
		Input input = world.getInput();
		int delta = world.getDelta();
		if(input.isKeyPressed(Input.KEY_2)) {
		System.out.println(targetX);}
		// If the mouse button is being clicked, set the target to the cursor location
		if (isSelect() && input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			
			targetX = getCamera().screenXToGlobalX(input.getMouseX());
			targetY = getCamera().screenYToGlobalY(input.getMouseY());
		}else if (commandCenter != null && resource.getAmount() > 0)	{
			// arrive resource to mine
			if ((World.distance(getX(), getY(), resource.getX(), resource.getY())) <= 32) {
				count += delta;
				if (count > MINE_TIME && carryResources <= resource.getAmount()) {
					targetX = commandCenter.getX();
					targetY = commandCenter.getY();
					playerCarryResource = carryResources;
					resource.setAmount(carryResources);
					count = 0;
				}else if (count > MINE_TIME && carryResources > resource.getAmount()) {
					targetX = commandCenter.getX();
					targetY = commandCenter.getY();
					playerCarryResource = resource.getAmount();
					resource.setAmount(resource.getAmount());
					count = 0;
				}
			// arrive commandCenter to send the resource
			}else if ((World.distance(getX(), getY(), commandCenter.getX(), commandCenter.getY())) <= 32) {
				targetX = resource.getX();
				targetY = resource.getY();
				if (resource instanceof Metal) {
					world.setMetal(playerCarryResource);
				}else {
					world.setUnobtainium(playerCarryResource);
				}
				playerCarryResource = 0;
				if (resource.getAmount() == 0) {
					targetX = getX();
					targetY = getY();
					
				}
			}
		}
		moves(world, targetX, targetY);
		// find the resource to mine 
		findMineResource(world.getSprites());
		
		if (isFindResource()) {
			findNearestCommandCenter(world.getSprites());
		}
		
	}
	/*
	 * get to know if the resources could be mining anymore.
	 */
	private boolean isMineAvailable(Resource resource) {
			return resource.getAmount() != 0;
	}
	
	/*
	 * mine resources each time 
	 */
	private void mine(Resource resource) {
		resource.setAmount(carryResources);
	}
	
	private void findNearestCommandCenter(ArrayList<Sprite> sprites) {
		double min = Double.POSITIVE_INFINITY;
		HashMap<Sprite, Double> commandCenter = new HashMap<Sprite, Double>();
		for(Sprite sprite: sprites) {
			if (sprite instanceof CommandCenter) {
				double distance = World.distance(getX(), getY(), sprite.getX(), sprite.getY());
				commandCenter.put(sprite, distance);
			}
		}
		for(HashMap.Entry<Sprite, Double> entry: commandCenter.entrySet()) {
			if(entry.getValue() < min) {
				min = entry.getValue();
				this.commandCenter = (CommandCenter)entry.getKey();
			}
		}
	}
	private void findMineResource(ArrayList<Sprite> sprites) {
		for (Sprite sprite: sprites) {
			if(sprite instanceof Resource && (World.distance(
					getX(), getY(), sprite.getX(), sprite.getY()) <= World.DISTANCE)
					&& ((Resource) sprite).getAmount() > 0) {
				this.resource = (Resource)sprite;
			}
		}
	}
	private boolean isFindResource() {
		return resource != null;
	}
}
	
