package EngineTester;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.RawModel;
import RenderEngine.Renderer;

public class Main {
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
			    -0.5f, 0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f,
			    -0.5f, 0.5f, 0f
			  };
		
		RawModel model = loader.loadToVAO(vertices);

		while (!Display.isCloseRequested()) {
			// game logic
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
		}

		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
