import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Engineer extends Unit implements Movable{
	private static final String ENGINEER_PATH = "assets/units/engineer.png";
	private static final double SPEED = 0.1;
	private int carryResources = 2;
	private int playerCarryResource = 0;
	private static final float MINE_TIME = 5000;
	private Resource resource;
	private CommandCenter commandCenter;
	private double targetX = getX();
	private double targetY = getY();
	
	public Engineer(double x, double y, Camera camera) throws SlickException {
		super(x, y, ENGINEER_PATH, SPEED, camera);
	}
	public void setCarryResource(int add) {carryResources += add;}
	public int getCarryResource() {return carryResources;}
	
	@Override
	public void update(World world) throws SlickException {
		Input input = world.getInput();
		
		// If the mouse button is being clicked, set the target to the cursor location
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON) && isSelect()) {
			
			targetX = getCamera().screenXToGlobalX(input.getMouseX());
			targetY = getCamera().screenYToGlobalY(input.getMouseY());
		}
		
		moves(world, targetX, targetY);
		// find the resource to mine 
		findMineResource(world.sprites);
		
		if (isFindResource()) {
			findNearestCommandCenter(world.sprites);
		}
		if (commandCenter != null)	{
			// arrive resource to mine
			if((World.distance(getX(), getY(), resource.getX(), resource.getY())) <= 32) {
				targetX = commandCenter.getX();
				targetY = commandCenter.getY();
				if (resource.getAmount() <= 0) {
					targetX = getX();
					targetY = getY();
				}else {
				playerCarryResource = carryResources;
				resource.setAmount(carryResources);
				}
			// arrive commandCenter to send the resource
			}else if ((World.distance(getX(), getY(), commandCenter.getX(), commandCenter.getY())) <= 32) {
				targetX = resource.getX();
				targetY = resource.getY();
				if(resource instanceof Metal) {
					world.setMetal(playerCarryResource);
				}else {
					world.setUnobtainium(playerCarryResource);
				}
				playerCarryResource = 0;
				if (resource.getAmount() <= 0) {
					targetX = getX();
					targetY = getY();
					
				}
			}
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
	
	@Override
	public void render(Graphics g) {
		if (isFindResource()) {
		g.drawString("target x " + targetX + "\ntargetY "
		+ targetY + "\nresourceamount " + resource.getAmount() , 100, 100);
		}
		super.render(g);
	}
}
	
