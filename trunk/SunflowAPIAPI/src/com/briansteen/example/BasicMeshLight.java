package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BasicMeshLight {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	public BasicMeshLight() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set camera
		sunflow.setCameraPosition(0, 0, -15);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		// set mesh light
		float vertices[] = {-5.0f, 1.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				5.0f, 1.0f, 0.0f};
		int triangles[] = {0, 1, 2,};
		sunflow.drawMeshLight("meshLight0", new Color(255, 255, 255), 64, vertices, triangles);
		
		// background plane
		sunflow.setDiffuseShader("myWallShader", new Color(255,255,255));
		sunflow.drawPlane("wall", new Point3(0,0,3), new Vector3(0,0,1));
		
		// set shader
		sunflow.setAmbientOcclusionShader("myAmbientOcclusionShader", new Color(255,0,0), new Color(0,0,0), 64, 1);
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
		sunflow.setPhongShader("myPhongShader", new Color(1f,1f,1f), new Color(.5f,.5f,.9f), 10, 64);
		// draw an object
		sunflow.drawSphere("sphere05", 4, 0, 0, 1);
		
		// sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		sunflow.setPathTracingGIEngine(64);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BasicMeshLight main = new BasicMeshLight();
	}
}