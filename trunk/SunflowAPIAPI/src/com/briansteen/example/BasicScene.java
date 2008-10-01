package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BasicScene {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	public BasicScene() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set camera
		sunflow.setCameraPosition(0, 0, 15);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		// set basic light
		sunflow.setSunSkyLight("mySunskyLight");
		sunflow.setPointLight("myPointLight", new Point3(0,5,5), new Color(255,255,255));
		sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2,3,0), new Vector3(0,0,0), 3, new Color(1f,0f,0f));
		sunflow.setSphereLight("mySphereLight", new Point3(0,20,-5), new Color(0,0,255), 32, 10);
		
		// set shader
		sunflow.setAmbientOcclusionShader("myAmbientOcclusionShader", new Color(255,0,0), new Color(0,0,0), 16, 1);
		// draw an object
		sunflow.drawSphere("sphere01", -4, 0, 0, 1);

		// set shader
		sunflow.setDiffuseShader("myDiffuseShader", new Color(255,0,0));
		// draw an object
		sunflow.drawSphere("sphere02", -2, 0, 0, 1);
		
		// set shader
		sunflow.setGlassShader("myGlassShader", new Color(1f,1f,1f), 2.5f, 3f, new Color(1f,1f,1f));
		// draw an object
		sunflow.drawSphere("sphere03", 0, 0, 0, 1);
		
		// set shader
		sunflow.setShinyDiffuseShader("myShinyShader", new Color(255,0,0), 2f);
		// draw an object
		sunflow.drawSphere("sphere04", 2, 0, 0, 1);
		
		// set shader
		sunflow.setPhongShader("myPhangShader", new Color(1f,1f,1f), new Color(.5f,.5f,.9f), 10, 16);
		// draw an object
		sunflow.drawSphere("sphere05", 4, 0, 0, 1);
		
		sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BasicScene main = new BasicScene();
	}
}