package com.slyvronline.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.slyvronline.game.Game;

public class TiledObjectUtil {

	public static void parseTiledObjectLayer(World world, MapObjects objects){
		for(MapObject object : objects){
			Shape shape;
			if (object instanceof PolylineMapObject){
				shape = createPolyline((PolylineMapObject) object);
			}
			else{
				continue;
			}
			
			Body body;
			BodyDef bdef = new BodyDef();
			bdef.type = BodyDef.BodyType.StaticBody;
			body = world.createBody(bdef);
			body.createFixture(shape, 1.0f);
			shape.dispose();
		}
	}
	
	private static ChainShape createPolyline(PolylineMapObject polyline){
		float[] vertices = polyline.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];
		
		for(int i=0; i < worldVertices.length; i++){
			worldVertices[i] = new Vector2(vertices[i * 2] / Game.PPM, vertices[i * 2 + 1] / Game.PPM);
		}
		
		ChainShape cs = new ChainShape();
		cs.createChain(worldVertices);
		
		return cs;
	}
}
