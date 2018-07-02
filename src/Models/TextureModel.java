package Models;

import Textures.ModelTexture;

public class TextureModel {
	
	private RawModel rawModel;
	private ModelTexture texture;
	
	public TextureModel(RawModel model, ModelTexture texture) {
		this.rawModel = model;
		this.texture = texture;
	}
}
