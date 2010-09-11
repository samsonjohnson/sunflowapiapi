package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BasicMesh {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	public BasicMesh() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set camera
		sunflow.setCameraPosition(-1.5f, .5f, -.63f);
		sunflow.setCameraTarget(25, -.1f, 13);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		
		// set basic light
		sunflow.setSunSkyLight("mySunskyLight");
		sunflow.setPointLight("myPointLight", new Point3(0,5,5), new Color(255,255,255));
		sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2,3,0), new Vector3(0,0,0), 3, new Color(1f,0f,0f));
		sunflow.setSphereLight("mySphereLight", new Point3(0,20,-5), new Color(0,0,255), 32, 10);
		
		sunflow.drawPlane("ground", new Point3(0,-.5f,0), new Vector3(0,1,0));
		
		// set shader
		sunflow.setShinyDiffuseShader("myShinyShader", new Color(5,5,5), .5f);
		
		// draw a mesh
		for(int i=0;i<50;i++) {
			float vertices[] = {3.0f+i, -.5f, 0.0f + i*.1f,
					2.5f+i + (float)Math.cos(i*.35f)*2f, .5f + (float)Math.cos(i*.1f)*.2f, 0.5f + i*.1f,
					2.0f+i, -.5f, 1.0f + i*.1f};
			int triangles[] = {0, 1, 2,};
			sunflow.drawMesh("mesh"+i, vertices, triangles);
		}
		
		// set glass shader
		// sunflow.setGlassShader("myGlassShader", new Color(1f,1f,1f), 2.5f, 3f, new Color(1f,1f,1f));
		// sunflow.setDiffuseShader("myDiffuseShader", new Color(255,0,0));
		
		// draw a bezier mesh
		float points[] = { 3.2f, 0f, 2.25f,
				3.2f, -0.15f, 2.25f,
				2.8f, -0.15f, 2.25f,
				2.8f, 0f, 2.25f, 3.45f,
				0f, 2.3625f, 3.45f,
				-0.15f, 2.3625f, 2.9f,
				-0.25f, 2.325f, 2.9f,
				0f, 2.325f, 3.525f,
				0f, 2.34375f, 3.525f,
				-0.25f, 2.34375f, 2.8f,
				-0.25f, 2.325f, 2.8f,
				0f, 2.325f, 3.3f,
				0f, 2.25f, 3.3f,
				-0.25f, 2.25f, 2.7f,
				-0.25f, 2.25f, 2.7f,
				0f, 2.25f, 2.4f,
				0f, 1.875f, 2.4f,
				-0.25f, 1.875f, 2.3f,
				-0.25f, 1.95f, 2.3f,
				0f, 1.95f, 3.1f,
				0f, 0.675f, 3.1f,
				-0.66f, 0.675f, 2.6f,
				-0.66f, 1.275f, 2.6f,
				0f, 1.275f, 1.7f,
				0f, 0.45f, 1.7f,
				-0.66f, 0.45f, 1.7f,
				-0.66f, 1.275f, 1.7f,
				0f, 1.275f };
		sunflow.drawBezierMesh("beziermesh", 8, true, false, 4, 7, false, false, points);
		
		// sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		sunflow.setPathTracingGIEngine(64);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BasicMesh main = new BasicMesh();
	}
}