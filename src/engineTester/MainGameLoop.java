package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.tools.javah.Util;
import entities.*;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		/* Objects Created */
		RawModel floor1 = OBJLoader.loadObjModel("floor", loader);
		RawModel wall1 = OBJLoader.loadObjModel("test_wall2", loader);
		RawModel chair = OBJLoader.loadObjModel("chair", loader);
		RawModel imac = OBJLoader.loadObjModel("imac", loader);
		RawModel table = OBJLoader.loadObjModel("table", loader);

		/* Textured Models Created */
		TexturedModel staticModel = new TexturedModel(floor1,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel1 = new TexturedModel(wall1,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel2 = new TexturedModel(imac, new ModelTexture(loader.loadTexture("white1")));
		TexturedModel staticModel3 = new TexturedModel(table, new ModelTexture(loader.loadTexture("white1")));
		TexturedModel staticModel5 = new TexturedModel(chair, new ModelTexture(loader.loadTexture("white")));

		/* Floor and Walls Created */
		World floor = new World(staticModel, new Vector3f(-3, 0, -105), 0, 0, 0, 1);
		World walls = new World(staticModel1, new Vector3f(-8, 0, -105), 0, 0, 0, 1);
		World walls1 = new World(staticModel1, new Vector3f(49, 0, -105), 0, 0, 0, 1);
		World floor_1 = new World(staticModel, new Vector3f(-3, 0, -500), 0, 0, 0, 1);
		World walls2 = new World(staticModel1, new Vector3f(-8, 0, -500), 0, 0, 0, 1);
		World walls3 = new World(staticModel1, new Vector3f(49, 0, -500), 0, 0, 0, 1);


//		List<Entity> entities = new ArrayList<Entity>();
//		Random random = new Random();
//		for(int i=0;i<500;i++){
//			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
//		}

		/* Light source Created */
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));

//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain2 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain3 = new Terrain(-2,-1,loader,new ModelTexture(loader.loadTexture("grass")));

		/* Master Renderer Created */
		MasterRenderer renderer = new MasterRenderer();

		RawModel sonicModel = OBJLoader.loadObjModel("sonic", loader);
		TexturedModel sonic = new TexturedModel(sonicModel, new ModelTexture(loader.loadTexture("chr_sonic_body01_dif")));

		/* Player Created */
		Player player = new Player(sonic, new Vector3f(0, 0, -10), 0, 0, 0, 15);

		Obstacles obstacles = new Obstacles(staticModel5, new Vector3f(0, 0, -500), 0, 0, 0, 1.5f);
		Obstacles obstacles1 = new Obstacles(staticModel5, new Vector3f(1000, 1000,1000), 0, 0, 0, 0);
		Obstacles obstacles2 = new Obstacles(staticModel5,new Vector3f(1000, 1000,1000), 0, 0, 0, 0);
		Obstacles obstacles3 = new Obstacles(staticModel5, new Vector3f(1000, 1000,1000), 0, 0, 0, 0);
		Obstacles obstacles4 = new Obstacles(staticModel5, new Vector3f(1000, 1000,1000), 0, 0, 0, 0);

		/* Camera Created */
		Camera camera = new Camera(player);

		while(!Display.isCloseRequested()){
			camera.move();
			/* Moving world and player */
			if (player.getPosition().x < -13.0f) {
				player.setPosition(new Vector3f(-13.0f, 0, 0));
			}
			if (player.getPosition().x > 13.0f) {
				player.setPosition(new Vector3f(13.0f, 0, 0));
			}
			player.move();
			floor.move();
			walls.move();
			walls1.move();
			floor_1.move();
			walls2.move();
			walls3.move();
			obstacles.move();
			obstacles1.move();
			obstacles2.move();
			obstacles3.move();
			obstacles4.move();
			/* Render Entities */
			renderer.processEntity(player);
			renderer.processEntity(floor);
			renderer.processEntity(walls);
			renderer.processEntity(walls1);
			renderer.processEntity(floor_1);
			renderer.processEntity(walls2);
			renderer.processEntity(walls3);
			renderer.processEntity(obstacles);
			renderer.processEntity(obstacles2);
			renderer.processEntity(obstacles1);
			renderer.processEntity(obstacles3);
			renderer.processEntity(obstacles4);
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
				System.out.println("Obstacle positionZ = " + obstacles.getPosition().z);
				System.out.println("Obstacle1 positionZ = " + obstacles1.getPosition().z);
				System.out.println("Obstacle2 positionZ = " + obstacles2.getPosition().z);
				System.out.println("Obstacle3 positionZ = " + obstacles3.getPosition().z);
			}
			if (floor.getPosition().z >= 290) {
				System.out.println("floor and walls created");
				floor = new World(staticModel, new Vector3f(-3, 0, -500), 0, 0, 0, 1);
				walls = new World(staticModel1, new Vector3f(-8, 0, -500), 0, 0, 0, 1);
				walls1 = new World(staticModel1, new Vector3f(49, 0, -500), 0, 0, 0, 1);
				obstacles2 = new Obstacles(staticModel5, new Vector3f(0, 0, -500), 0, 0, 0, 1.5f);
				obstacles1 = new Obstacles(staticModel2, new Vector3f(13, 0, -500), 0, 0, 0, 1.5f);
				obstacles4 = new Obstacles(staticModel3, new Vector3f(13, 0, -500), 0, 0, 0, 1.5f);
			}
			if (floor_1.getPosition().z >= 290) {
				System.out.println("floor_1 and walls created");
				floor_1 = new World(staticModel, new Vector3f(-3, 0, -500), 0, 0, 0, 1);
				walls2 = new World(staticModel1, new Vector3f(-8, 0, -500), 0, 0, 0, 1);
				walls3 = new World(staticModel1, new Vector3f(49, 0, -500), 0, 0, 0, 1);
				obstacles = new Obstacles(staticModel5, new Vector3f(0, 0, -500), 0, 0, 0, 1.5f);
				obstacles3 = new Obstacles(staticModel5, new Vector3f(-13, 0, -500), 0, 0, 0, 1.5f);
			}
			if ((obstacles.getPosition().z >= player.getPosition().z && obstacles.getPosition().z <= -0.7019259f) && (player.getPosition().x >= -9.0f && player.getPosition().x <= 9.0f) && (player.getPosition().y <= 5.8f)) {
				System.out.println("YOU DIED!!!!! Obstacle works!!!");
				break;
			}
			if ((obstacles1.getPosition().z >= player.getPosition().z && obstacles1.getPosition().z <= 10.898511) && (player.getPosition().x <= 13.00f && player.getPosition().x >= 9.0f) &&(player.getPosition().y <= 5.8f)) {
				System.out.println("YOU DIED!!!!! Obstacle1 works!!!");
				break;
			}
			if ((obstacles2.getPosition().z >= player.getPosition().z && obstacles2.getPosition().z <= 9.998177) && (player.getPosition().x <= 9.0f && player.getPosition().x >= -9.0f) && (player.getPosition().y <= 5.8f)){
				System.out.println("YOU DIED!!!!! Obstacle2 works!!!");
				break;
			}
			if ((obstacles3.getPosition().z >= player.getPosition().z && obstacles3.getPosition().z <= 7.398673) && (player.getPosition().x >= -13.00f && player.getPosition().x <= 0.0f) && (player.getPosition().y <= 5.8f)) {
				System.out.println("YOU DIED!!!!! Obstacle3 works!!!");
				System.out.println("Obstacle3 positionX = " + obstacles3.getPosition().x);
				break;
			}
//			renderer.processTerrain(terrain);
//			renderer.processTerrain(terrain2);
//			renderer.processTerrain(terrain3);
//			for(Entity entity:entities){
//				renderer.processEntity(entity);
//			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
