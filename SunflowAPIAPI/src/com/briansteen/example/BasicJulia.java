package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BasicJulia {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	public BasicJulia() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set camera
		sunflow.setCameraPosition(0, 0, -15);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		// set basic light
		sunflow.setPointLight("myPointLight", new Point3(0,5,5), new Color(255,255,255));
		sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2,3,0), new Vector3(0,0,0), 3, new Color(1f,0f,0f));
		sunflow.setSphereLight("mySphereLight", new Point3(0,20,-5), new Color(0,0,255), 32, 10);
		
		sunflow.drawPlane("wall", new Point3(0,0,1000), new Vector3(0,0,1));
		
		// set shader
		sunflow.setAmbientOcclusionShader("myAmbientOcclusionShader", new Color(255,255,255), new Color(0,0,0), 16, 1);
		// draw an object
		float[] q = new float[4];
		q[0] = -0.125f;
		q[1] = 0.256f;
		q[2] = 0.847f;
		q[3] = 0.0895f;
		sunflow.drawJulia("julia01", 0, 0, 0, 2, 0, 0, 0, q, 8, .001f);
		
		sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BasicJulia main = new BasicJulia();
	}
}