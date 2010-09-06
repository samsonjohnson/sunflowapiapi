package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BasicHair {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	private int pointAmount = 20;
	private float[] hairCoordinates;
	private float[] hairWidths;
	public BasicHair() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set background color
		sunflow.setBackground(1f, 1f, 1f);
		// set camera
		sunflow.setCameraPosition(0, 7, 5);
		sunflow.setCameraTarget(2, .5f, 0);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		// set basic light
		sunflow.setPointLight("myPointLight", new Point3(0,5,5), new Color(255,255,255));
		sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2,3,0), new Vector3(0,0,0), 3, new Color(1f,1f,1f));
		// sunflow.setSphereLight("mySphereLight", new Point3(0,30,-5), new Color(0,0,255), 32, 10);
		// draw a ground plane
		sunflow.drawPlane("ground", new Point3(0,0,0), new Vector3(0,1,0));
		
		// coordinates array
		hairCoordinates = new float[pointAmount*3];
		hairWidths = new float[] { .025f };
		
		sunflow.drawBox("boxname", 0, 0, 0, 1f);
		
		// create particle coodinates
		for(int j=0;j<350;j++) {
			// particle start position
			float particleX = (float)Math.cos(j*.5f)*j*.0015f;
			float particleY = 0;
			float particleZ = (float)Math.sin(j*.5f)*j*.0015f;
			int arrayIndex = 0;
			hairCoordinates = new float[pointAmount*3];
			for(int i=0;i<pointAmount;i++) {
				particleX += .1f + (float)Math.cos(i * .15f + j*.05f) * .13f;
				particleY -= (float)Math.sin(particleZ*.01f + j*.05f)*.125f + (float)Math.cos(i*.5f + particleY)*.125f;
				particleZ += (float)Math.sin(i)*.25f + particleY*.01f;
				
				hairCoordinates[arrayIndex++] = particleX;
				hairCoordinates[arrayIndex++] = particleY;
				hairCoordinates[arrayIndex++] = particleZ;
			}
			
			// set ambient occlusion shader
			sunflow.setAmbientOcclusionShader("myAmbientOcclusionShader"+j, new Color(55,55,55), new Color(0,0,0), 16, 1);
			// set glass shader
			// sunflow.setGlassShader("myGlassShader", new Color(1f,1f,1f), 2.5f, 3f, new Color(1f,1f,1f));
			// set shiny-diffuse shader
			// sunflow.setShinyDiffuseShader("myShinyShader", new Color(55,55,55), .8f);
			
			// draw object
			sunflow.drawHair("hair"+j, pointAmount-2, hairCoordinates, hairWidths);
		}
		
		sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BasicHair main = new BasicHair();
	}
}