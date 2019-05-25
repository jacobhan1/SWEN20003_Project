import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class should be used to contain all the different objects in your game world, 
 * and schedule their interactions.
 */
public class World {
	public static final int DISTANCE = 32;
	private static final String MAP_PATH = "assets/main.tmx";
	private static final String SOLID_PROPERTY = "solid";
	private static final int INDEX_OBJECT = 0;
	private static final int INDEX_X = 1;
	private static final int INDEX_Y = 2;
	private static final int DISPLAY = 32;
	// create object needed
	public static final int UNIT_BUILD_TIME = 5000;
	public static final int FACTORY_BUILD_TIME = 10000;
	public static final int COMMANDCENTRE_BUILD_TIME = 15000;
	public static final int FACTORY_COST_METAL = 100;
	public static final int SCOUT_COST_METAL = 5;
	public static final int BUILDER_COST_METAL = 10;
	public static final int ENGINEER_COST_METAL = 20;
	public static final int TRUCK_COST_METAL = 150;
	// count time 
	private int count = 0;
	// player's metal and unobtainium amounts
	private int metal = 0;
	private int unobtainium = 0;
	// keyDownInput
	private boolean input1 = false;
	private boolean input2 = false;
	private boolean input3 = false;
	private Sprite createObject;
	// all sprites
	private ArrayList<Sprite> sprites;
	private TiledMap map;
	private Camera camera = new Camera();
	private Input lastInput;
	private int lastDelta;
	/**
	 * world init the map and read csv files to init the objects to the world
	 * @throws SlickException
	 */
	public World() throws SlickException {
		map = new TiledMap(MAP_PATH);
		readCsv();
	}
	
	/**
	 * Update all the sprites, make creatable sprites create new sprite
	 * @param input The input to control the movement.
	 * @param delta The delta makes sure the same speed with different FPS.
	 * @throws SlickException 
	 */
	public void update(Input input, int delta) throws SlickException{
		lastInput = input;
		lastDelta = delta;
		camera.update(this);
		inputChange(input);
		// create sprite into the world, press KEY_1:
		if (input1) {  
			if(createObject == null) {
				inputFalse("input1");
			} else { 
				if (createObject instanceof CommandCenter) {
					addSprite(createObject, SCOUT_COST_METAL, UNIT_BUILD_TIME, "input1");
				}else if (createObject instanceof Factory) {
					addSprite(createObject, TRUCK_COST_METAL, UNIT_BUILD_TIME, "input1");
				}else if (createObject instanceof Builder) {
					addSprite(createObject, FACTORY_COST_METAL, FACTORY_BUILD_TIME, "input1");
				}else if (createObject instanceof Truck) {
					addSprite(createObject, 0, COMMANDCENTRE_BUILD_TIME, "input1");
				}
				createObject = null;
			}
		// when press KEY_2:
		}else if (input2) {
			if(createObject == null) {
				inputFalse("input2");
			} else {
				if (createObject instanceof CommandCenter) {
					addSprite(createObject, BUILDER_COST_METAL, UNIT_BUILD_TIME, "input2");
				}
				createObject = null;
			}
		// when press KEY_3: 
		}else if (input3) {
			if(createObject == null) {
				inputFalse("input3");
			} else {
				if (createObject instanceof CommandCenter) {
					addSprite(createObject, ENGINEER_COST_METAL, UNIT_BUILD_TIME, "input3");
				}
				createObject = null;
			}
		}
		// update all the sprites in the world
		for (Sprite sprite: sprites) {
			sprite.update(this);
			if (sprite instanceof Creatable && ((Selectable)sprite).isSelect()) {
				createObject = sprite;
			}
		}
	}
	/**
	 * render the whole world 
	 * @param g render the player's metal and unobtainium amount
	 */
	public void render(Graphics g) {
		map.render((int)camera.globalXToScreenX(0),
				   (int)camera.globalYToScreenY(0));
		g.drawString("Metal:  <" + metal + ">\nUnobtainium:  <" + unobtainium + ">" ,
				DISPLAY, DISPLAY);
		for(Sprite sprite: sprites) {
			sprite.render(g);
			}
	}
	/**
	 * calculate distance between sprite1 and sprite2 
	 * @param x1 sprite1 x coordinate
	 * @param y1 sprite1 y coordinate
	 * @param x2 sprite2 x coordinate
	 * @param y2 sprite2 y coordinate
	 * @return two sprites' distance
	 */
	public static double distance(double x1, double y1, double x2, double y2) {
		return (double)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	/**
	 * get the input value.
	 * @return input value
	 */
	public Input getInput() {return lastInput;}
	/**
	 * get the delta value.
	 * @return delta value
	 */
	public int getDelta() {return lastDelta;}
	/** 
	 * get the player's metal value.
	 * @return player's metal value
	 */
	public int getMetal() {return metal;}
	/** 
	 * get the camera.
	 * @return camera
	 */
	public Camera getCamera() {return camera;}
	/**
	 * get the map's width.
	 * @return map's width
	 */
	public double getMapWidth() {return map.getWidth() * map.getTileWidth();}
	/**
	 * get the map's height.
	 * @return map's height
	 */
	public double getMapHeight() {return map.getHeight() * map.getTileHeight();}
	/**
	 * set the metal value.
	 * @param metal player's metal value
	 */
	public void setMetal(int metal) {this.metal += metal;}
	/**
	 * set the unobtainium value.
	 * @param unobtainium player's unobtainium value
	 */
	public void setUnobtainium(int unobtainium) {this.unobtainium += unobtainium;}
	/**
	 * get all the world's sprites
	 * @return arrayList contains all the world's sprites
	 */
	public ArrayList<Sprite> getSprites() {return sprites;}
	/**
	 * judge if the point is solid.
	 * @param x point's x coordinate
	 * @param y point's y coordinate
	 * @return if it is solid, return true, else return false
	 */
	public boolean isPositionFree(double x, double y) {
		int tileId = map.getTileId(worldXToTileX(x), worldYToTileY(y), 0);
		return !Boolean.parseBoolean(map.getTileProperty(tileId, SOLID_PROPERTY, "false"));
	}
	// assign all the input to false
	private void inputFalse(String input) {
		switch (input) {
			case "input1": input1 = false;
			case "input2": input2 = false;
			case "input3": input3 = false;
		}
	}
	// if the costMetal and createTime available, then addSprite to the world
	private void addSprite(Sprite sprite, int costMetal, int createTime, String input) throws SlickException {
		if(sprite instanceof Removable) {
			if (count < createTime) {
				count += lastDelta;
			}else {
				count = 0;
				sprites.add(((Creatable)sprite).create(input, sprite.getX(), sprite.getY()));
				((Removable)sprite).remove();
			}
		}else {
			if (metal >= costMetal && count < createTime) {
				count += lastDelta;
			}else if (metal < costMetal) {
				inputFalse(input);
			}else if (metal >= costMetal && count >= createTime) {
				count = 0;
				inputFalse(input);
				metal -= costMetal;
				sprites.add(((Creatable)sprite).create(input, sprite.getX(), sprite.getY()));
			}
		}
	}
	// record the key press
	private void inputChange(Input input) {
		if (input.isKeyPressed(Input.KEY_1)) {
			input1 = true;
		}else if (input.isKeyPressed(Input.KEY_2)) {
			input2 = true;
		}else if (input.isKeyPressed(Input.KEY_3)) {
			input3 = true;
		}
	}
	//read csv file to init objects
	private void readCsv() {
		sprites = new ArrayList<>();
		try (BufferedReader csvReader = 
				new BufferedReader (new FileReader("assets/objects.csv"))) {
			String line;
			while ((line = csvReader.readLine()) != null) {
				String[] data = line.split(",");
				
				int x = Integer.parseInt(data[INDEX_X]);
				int y = Integer.parseInt(data[INDEX_Y]);
				
				switch (data[INDEX_OBJECT]) {
					case("command_centre"):
						sprites.add(new CommandCenter(x, y, camera));
						break;
						
					case("metal_mine"):
						sprites.add(new Metal(x, y, camera));
						break;
						
					case("unobtainium_mine"):
						sprites.add(new Unobtainium(x, y, camera));
						break;
						
					case("pylon"):
						sprites.add(new Pylon(x, y, camera));
						break;
					
					case("engineer"):
						sprites.add(new Engineer(x, y, camera));
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private int worldXToTileX(double x) {
		return (int)(x / map.getTileWidth());
	}
	private int worldYToTileY(double y) {
		return (int)(y / map.getTileHeight());
	}
}
