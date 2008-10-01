package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BasicHair {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	private int pointAmount = 350;
	private float[] hairCoordinates;
	private float[] hairWidths;
	public BasicHair() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set background color
		sunflow.setBackground(1f, 1f, 1f);
		// set camera
		sunflow.setCameraPosition(0, 10, 5);
		sunflow.setCameraTarget(15, 0, 0);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		// set basic light
		sunflow.setPointLight("myPointLight", new Point3(0,5,5), new Color(255,255,255));
		sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2,3,0), new Vector3(0,0,0), 3, new Color(1f,1f,1f));
		// sunflow.setSphereLight("mySphereLight", new Point3(0,30,-5), new Color(0,0,255), 32, 10);
		// draw a ground plane
		sunflow.drawPlane("ground", new Point3(0,0,0), new Vector3(0,1,0));
		
		// coordinates array
		hairCoordinates = new float[pointAmount*3];
		hairWidths = new float[] { .5f };
		// particle start position
		float particleX = 0;
		float particleY = 0;
		float particleZ = 0;
		
		// create particle coodinates
		int arrayIndex = 0;
		for(int i=0;i<pointAmount;i++) {
			particleX += .1f + (float)Math.cos(i * .15f) * 1.3f;
			particleY += (float)Math.sin(particleZ)*.25f + (float)Math.cos(i*.5f + particleY)*.25f;
			particleZ += (float)Math.sin(i)*.25f + particleY*.01f;
			
			hairCoordinates[arrayIndex++] = particleX;
			hairCoordinates[arrayIndex++] = particleY;
			hairCoordinates[arrayIndex++] = particleZ;
			
			// set a light
			sunflow.setPointLight(i + "myPointLight", new Point3(particleX,particleY,particleZ+10), new Color(255,255,255));
		}
		
		// set ambient occlusion shader
		// sunflow.setAmbientOcclusionShader("myAmbientOcclusionShader", new Color(255,255,255), new Color(0,0,0), 16, 1);
		// set glass shader
		sunflow.setGlassShader("myGlassShader", new Color(1f,1f,1f), 2.5f, 3f, new Color(1f,1f,1f));
		// set shiny-diffuse shader
		// sunflow.setShinyDiffuseShader("myShinyShader", new Color(255,255,255), .8f);
		
		// draw object
		sunflow.drawHair("hair", pointAmount-1, hairCoordinates, hairWidths);
		
		sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BasicHair main = new BasicHair();
	}
}