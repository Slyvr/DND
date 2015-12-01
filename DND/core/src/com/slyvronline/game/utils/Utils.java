package com.slyvronline.game.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.slyvronline.game.Game;

public class Utils {
	
	public static double calcLineDistance(Rectangle pos1, Rectangle pos2){
		float diffX = pos1.getX()-pos2.getX();
		float diffY = pos1.getY()-pos2.getY();
		double distance = Math.sqrt((diffX*diffX)+(diffY*diffY));
		return distance;
	}
	
	public static Rectangle getGameMousePos(){
		Vector3 mousePos = Game.getGlobal().getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		return new Rectangle(mousePos.x, mousePos.y,1,1);
	}
	
	public static Rectangle getMenuMousePos(){
		Vector3 mousePos = Game.getGlobal().getHudCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		return new Rectangle(mousePos.x, mousePos.y,1,1);
	}
	
	public static String docToString(Document doc) {
	    try {
	        StringWriter sw = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        transformer.transform(new DOMSource(doc), new StreamResult(sw));
	        return sw.toString();
	    } catch (Exception ex) {
	        throw new RuntimeException("Error converting to String", ex);
	    }
	}
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
}
