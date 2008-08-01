package com.briansteen;

import java.awt.Color;

import org.sunflow.SunflowAPI;
import org.sunflow.core.Display;
import org.sunflow.core.display.FileDisplay;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

public class SunflowAPIAPI {
private String currShader;
private String currModifier;
private String currCamera;

private SunflowAPI sunflow;

private Display windowDisplay;
private FileDisplay fileDisplay;

public final String SHADER_AMBIENT_OCCLUSION = "ambient_occlusion";
public final String SHADER_TEXTURED_AMBIENT_OCCLUSION = "textured_ambient_occlusion";
public final String SHADER_CONSTANT = "constant";
public final String SHADER_DIFFUSE = "diffuse";
public final String SHADER_TEXTURED_DIFFUSE = "textured_diffuse";
public final String SHADER_GLASS = "glass";
public final String SHADER_MIRROR = "mirror";
public final String SHADER_PHONG = "phong";
public final String SHADER_TEXTURED_PHONG = "textured_phong";
public final String SHADER_SHINY_DIFFUSE = "shiny_diffuse";
public final String SHADER_TEXTURED_SHINY_DIFFUSE = "textured_shiny_diffuse";
public final String SHADER_UBER = "uber";
public final String SHADER_WARD = "ward";
public final String SHADER_TEXTURED_WARD = "textured_ward";
public final String SHADER_WIREFRAME = "wireframe";

public final String COLORSPACE_SRGB_NONLINEAR = "sRGB nonlinear";
public final String COLORSPACE_SRGB_LINEAR = "sRGB linear";
public final String COLORSPACE_XYZ = "XYZ";
private String colorSpace = COLORSPACE_SRGB_NONLINEAR;

public final String CAMERA_PINHOLE = "pinhole";
public final String CAMERA_THINLENS = "thinlens";
public final String CAMERA_FISHEYE = "fisheye";
public final String CAMERA_SPHERICAL = "spherical";

public final String GI_AMBIENT_OCCLUSION = "ambocc";
public final String GI_FAKE = "fake";
public final String GI_INSTANT_GI = "igi";
public final String GI_IRRADIANCE_CACHE = "irr-cache";
public final String GI_PATH = "path";

private Point3 eye = new Point3(0, 10, 15); 
private Point3 target = new Point3(0, 0, 0); 
private Vector3 up = new Vector3(0, 1, 0); 

private int primitiveID = 0;
private boolean isModifiers = false;
public SunflowAPIAPI() {
sunflow = new SunflowAPI();
}

/*
* --------------------------------------------------------------------------------------
* SHADER
*/

/**
* Sets Ambient Occlusion Shader
* @param name Individual Name
* @param bright Highlight Color
* @param dark Dark Color
* @param samples Detail, the higher the slower and smoother
* @param maxDist ?
*/
public void setAmbientOcclusionShader(String name, Color bright, Color dark, int samples, int maxDist) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("bright", colorSpace, bright.getRed(), bright.getGreen(), bright.getBlue());
sunflow.parameter("dark", colorSpace, dark.getRed(), dark.getGreen(), dark.getBlue());
sunflow.parameter("samples", samples);
sunflow.parameter("maxdist", maxDist);

// set shader
sunflow.shader(currShader, SHADER_AMBIENT_OCCLUSION);
}

/**
* Sets Ambient Occlusion Shader
* @param name Individual Name
* @param bright Highlight Color
* @param dark Dark Color
* @param samples Detail, the higher the slower and smoother
* @param maxDist ?
* @param texture Path to texture file
*/
public void setAmbientOcclusionShader(String name, Color bright, Color dark, int samples, int maxDist, String texture) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("bright", colorSpace, bright.getRed(), bright.getGreen(), bright.getBlue());
sunflow.parameter("dark", colorSpace, dark.getRed(), dark.getGreen(), dark.getBlue());
sunflow.parameter("samples", samples);
sunflow.parameter("maxdist", maxDist);
sunflow.parameter("texture", texture);

// set shader
sunflow.shader(currShader, SHADER_TEXTURED_AMBIENT_OCCLUSION);
}

/**
* Sets constant shader
* @param name Individual Name
* @param color Color
*/
public void setConstantShader(String name, Color color) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("color", colorSpace, color.getRed(), color.getGreen(), color.getBlue());

// set shader
sunflow.shader(currShader, SHADER_CONSTANT);
}

/**
* Sets Diffuse Shader
* @param name Individial Name
* @param color Color
*/
public void setDiffuseShader(String name, Color color) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, color.getRed(), color.getGreen(), color.getBlue());

// set shader
sunflow.shader(currShader, SHADER_DIFFUSE);
}

/**
* Sets Diffuse Shader
* @param name Individial Name
* @param color Color
* @param texture Path to texture file
*/
public void setDiffuseShader(String name, Color color, String texture) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, color.getRed(), color.getGreen(), color.getBlue());
sunflow.parameter("texture", texture);

// set shader
sunflow.shader(currShader, SHADER_TEXTURED_DIFFUSE);
}

/**
* Sets Glass Shader
* @param name Individual Name
* @param color Color
* @param eta ?
* @param absorptionDistance ?
* @param absorptionColor Color
*/
public void setGlassShader(String name, Color color, float eta, float absorptionDistance, Color absorptionColor) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("color", colorSpace, color.getRed(), color.getGreen(), color.getBlue());
sunflow.parameter("eta", eta);
sunflow.parameter("absorption.distance", absorptionDistance);
sunflow.parameter("absorption.color", colorSpace, absorptionColor.getRed(), absorptionColor.getGreen(), absorptionColor.getBlue());

// set shader
sunflow.shader(currShader, SHADER_GLASS);
}

/**
* Sets Mirror Shader
* @param name Individial Name
* @param color Color
*/
public void setMirrorShader(String name, Color color) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("color", colorSpace, color.getRed(), color.getGreen(), color.getBlue());

// set shader
sunflow.shader(currShader, SHADER_MIRROR);
}

/**
* Sets Phong Shader
* @param name Individual Name
* @param diffuse Diffuse Color
* @param specular Specular Color
* @param power ?
* @param samples Detail, the higher the slower and smoother
*/
public void setPhongShader(String name, Color diffuse, Color specular, float power, int samples) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, diffuse.getRed(), diffuse.getGreen(), diffuse.getBlue());
sunflow.parameter("specular", colorSpace, specular.getRed(), specular.getGreen(), specular.getBlue());
sunflow.parameter("power", power);
sunflow.parameter("samples", samples);

// set shader
sunflow.shader(currShader, SHADER_PHONG);
}

/**
* Sets Phong Shader
* @param name Individual Name
* @param diffuse Diffuse Color
* @param specular Specular Color
* @param power ?
* @param samples Detail, the higher the slower and smoother
* @param texture Path to texture file
*/
public void setPhongShader(String name, Color diffuse, Color specular, float power, int samples, String texture) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, diffuse.getRed(), diffuse.getGreen(), diffuse.getBlue());
sunflow.parameter("specular", colorSpace, specular.getRed(), specular.getGreen(), specular.getBlue());
sunflow.parameter("power", power);
sunflow.parameter("samples", samples);
sunflow.parameter("texture", texture);

// set shader
sunflow.shader(currShader, SHADER_TEXTURED_PHONG);
}
/**
* Sets Shiny Diffuse Shader
* @param name Individual Name
* @param color Color
* @param shiny shinyness, the bigger the more
*/
public void setShinyDiffuseShader(String name, Color color, float shiny) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, color.getRed(), color.getGreen(), color.getBlue());
sunflow.parameter("shiny", shiny);

// set shader
sunflow.shader(currShader, SHADER_SHINY_DIFFUSE);
}
/**
* Sets Shiny Diffuse Shader
* @param name Individual Name
* @param color Color
* @param shiny shinyness, the bigger the more
* @param texture Path to texture file
*/
public void setShinyDiffuseShader(String name, Color color, float shiny, String texture) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, color.getRed(), color.getGreen(), color.getBlue());
sunflow.parameter("shiny", shiny);
sunflow.parameter("texture", texture);

// set shader
sunflow.shader(currShader, SHADER_TEXTURED_SHINY_DIFFUSE);
}

/**
* Sets Uber Shader
* @param name Individual Name
* @param diffuse Diffuse Color
* @param specular Specular Color
* @param diffuseTexture Diffuse Texture
* @param specularTexture Specular Texture
* @param diffuseBlend Diffuse Blendamount
* @param specularBlend Specular Blendamount
* @param glossyness glossyness
* @param samples samples
*/
public void setUberShader(String name, Color diffuse, Color specular, String diffuseTexture, String specularTexture, float diffuseBlend, float specularBlend, float glossyness, int samples) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, diffuse.getRed(), diffuse.getGreen(), diffuse.getBlue());
sunflow.parameter("specular", colorSpace, specular.getRed(), specular.getGreen(), specular.getBlue());
sunflow.parameter("diffuse.texture", diffuseTexture);
sunflow.parameter("specular.texture", specularTexture);
sunflow.parameter("diffuse.blend", diffuseBlend);
sunflow.parameter("specular.blend", specularBlend);
sunflow.parameter("glossyness", glossyness);
sunflow.parameter("samples", samples);

// set shader
sunflow.shader(currShader, SHADER_UBER);
}

/**
* Sets Anisotropic Ward Shader
* @param name Individual Name
* @param diffuse Diffuse Color
* @param specular Specular Color
* @param roughnessX Roughness in x axis
* @param roughnessY Roughness in y axis
* @param samples Detail, the more the slower and smoother
*/
public void setWardShader(String name, Color diffuse, Color specular, float roughnessX, float roughnessY, int samples) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, diffuse.getRed(), diffuse.getGreen(), diffuse.getBlue());
sunflow.parameter("specular", colorSpace, specular.getRed(), specular.getGreen(), specular.getBlue());
sunflow.parameter("roughnessX", roughnessX);
sunflow.parameter("roughnessY", roughnessY);
sunflow.parameter("samples", samples);

// set shader
sunflow.shader(currShader, SHADER_WARD);
}

/**
* Sets Anisotropic Ward Shader
* @param name Individual Name
* @param diffuse Diffuse Color
* @param specular Specular Color
* @param roughnessX Roughness in x axis
* @param roughnessY Roughness in y axis
* @param samples Detail, the more the slower and smoother
* @param texture Path to texture file
*/
public void setWardShader(String name, Color diffuse, Color specular, float roughnessX, float roughnessY, int samples, String texture) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("diffuse", colorSpace, diffuse.getRed(), diffuse.getGreen(), diffuse.getBlue());
sunflow.parameter("specular", colorSpace, specular.getRed(), specular.getGreen(), specular.getBlue());
sunflow.parameter("roughnessX", roughnessX);
sunflow.parameter("roughnessY", roughnessY);
sunflow.parameter("samples", samples);
sunflow.parameter("texture", texture);

// set shader
sunflow.shader(currShader, SHADER_TEXTURED_WARD);
}

/**
* Sets Wireframe Shader
* @param name Individual Name
* @param lineColor line color
* @param fillColor fill color
* @param width stroke width ?
*/
public void setWireframeShader(String name, Color lineColor, Color fillColor, float width) {
// save name for use with primitives
currShader = name;

// set parameter
sunflow.parameter("line", colorSpace, lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue());
sunflow.parameter("fill", colorSpace, fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue());
sunflow.parameter("width", width);

// set shader
sunflow.shader(currShader, SHADER_WIREFRAME);
}

/*
* END OF SHADER
* --------------------------------------------------------------------------------------
*/

/*
* --------------------------------------------------------------------------------------
* PRIMITIVES
*/

/**
* sets a mesh primitive
* @param name individual name of primitive
* @param vertices Float array with coordinates (like [x0,y0,z0,x1,y1,z1,x2,y2,z2])
* @param triangles int array connecting the vertices (like [0,1,2])
*/
public void drawMesh(String name, float[] vertices, int[] triangles) {
sunflow.parameter("points", "point", "vertex", vertices); 
sunflow.parameter("triangles", triangles);

sunflow.geometry( name, "triangle_mesh" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}
/**
* draws a sphere
* @param name Individual name
*/
public void drawSphere(String name) {
sunflow.geometry( name, "sphere" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}
/**
* draws a sphere
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
*/
public void drawSphere(String name, float x, float y, float z, float size) {

Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = translate.multiply(m);

sunflow.geometry( name, "sphere" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}
/**
* Draws a box
* @param name Individual name
*/
public void drawBox(String name) {
sunflow.geometry( name, "box" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}
/**
* Draws a box
* @param name
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
*/
public void drawBox(String name, float x, float y, float z, float size) {
Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = translate.multiply(m);

sunflow.geometry( name, "box" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}

/**
* Draws a box
* @param name
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
* @param xRotation x rotation
* @param yRotation y rotation
* @param zRotation z rotation
*/
public void drawBox(String name, float x, float y, float z, float size, float xRotation, float yRotation, float zRotation) {
Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );
Matrix4 rotate = Matrix4.IDENTITY 
.multiply( Matrix4.rotateZ(zRotation) ) 
.multiply( Matrix4.rotateX(xRotation) ) 
.multiply( Matrix4.rotateY(yRotation) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = rotate.multiply(m); 
m = translate.multiply(m);

sunflow.geometry( name, "box" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}

/**
* Draws a cylinder
* @param name Individual name
*/
public void drawCylinder(String name) {
sunflow.geometry( name, "cylinder" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}
/**
* Draws a cylinder
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
*/
public void drawCylinder(String name, float x, float y, float z, float size) {
Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = translate.multiply(m);

sunflow.geometry( name, "cylinder" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}

/**
* Draws a cylinder
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
* @param xRotation x rotation
* @param yRotation y rotation
* @param zRotation z rotation
*/
public void drawCylinder(String name, float x, float y, float z, float size, float xRotation, float yRotation, float zRotation) {
Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );
Matrix4 rotate = Matrix4.IDENTITY 
.multiply( Matrix4.rotateZ(zRotation) ) 
.multiply( Matrix4.rotateX(xRotation) ) 
.multiply( Matrix4.rotateY(yRotation) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = rotate.multiply(m); 
m = translate.multiply(m);

sunflow.geometry( name, "cylinder" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}

/**
* Draws a Banchoff Surface
* @param name Individual name
*/
public void drawBanchoffSurface(String name) {
sunflow.geometry( name, "banchoff" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}
/**
* Draws a Banchoff Surface
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
*/
public void drawBanchoffSurface(String name, float x, float y, float z, float size) {
Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = translate.multiply(m);

sunflow.geometry( name, "banchoff" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}

/**
* Draws a Banchoff Surface
* @param name Individual name
* @param x x position
* @param y y position
* @param z z position 
* @param size size
* @param xRotation x rotation
* @param yRotation y rotation
* @param zRotation z rotation
*/
public void drawBanchoffSurface(String name, float x, float y, float z, float size, float xRotation, float yRotation, float zRotation) {
Matrix4 translate = Matrix4.IDENTITY.multiply( Matrix4.translation(x, y, z ));
Matrix4 scale = Matrix4.IDENTITY.multiply( Matrix4.scale(size, size, size) );
Matrix4 rotate = Matrix4.IDENTITY 
.multiply( Matrix4.rotateZ(zRotation) ) 
.multiply( Matrix4.rotateX(xRotation) ) 
.multiply( Matrix4.rotateY(yRotation) );

Matrix4 m = Matrix4.IDENTITY;
m = scale.multiply(m);
m = rotate.multiply(m); 
m = translate.multiply(m);

sunflow.geometry( name, "banchoff" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.parameter( "transform", m ); 
sunflow.instance( name + ".instance", name );
}

/**
* set background
* @param name Individual name
*/
public void setBackground(String name) {
sunflow.geometry( name, "background" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}

/**
* draw hair object
* @param name Individual Name
* @param segments ?
* @param points start of hair ?
* @param widths hairwidth ?
*/
public void drawHair(String name, int segments, float[] points, float[] widths) {
sunflow.parameter("segments", segments);
sunflow.parameter("widths", "float", "none", widths);
sunflow.parameter("points", "point", "vertex", points);

sunflow.geometry( name, "hair" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );

}

/**
* draw a particle surface object
* @param name Individual name
* @param particles float array with particle positions
* @param radius object radius ?
* @param num ?
*/
public void drawParticleSurface(String name, float[] particles, float radius, int num) {
sunflow.parameter("points", "point", "vertex", particles);
sunflow.parameter("radius", num);
sunflow.parameter("num", num);

sunflow.geometry( name, "particles" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}
/**
* Draw a plane
* @param name Individual name
* @param center center of plane
* @param normal normal of plane
*/
public void drawPlane(String name, Point3 center, Vector3 normal) {
sunflow.parameter("center", center);
sunflow.parameter("normal", normal);

sunflow.geometry( name, "plane" );
sunflow.parameter( "shaders", currShader);
if(isModifiers) sunflow.parameter("modifiers", currModifier);
sunflow.instance( name + ".instance", name );
}

/*
* END OF PRIMITIVES
* --------------------------------------------------------------------------------------
*/

/*
* --------------------------------------------------------------------------------------
* CAMERAS
*/

/**
* sets Camera Position
* @param x x value
* @param y y value
* @param z z value
*/
public void setCameraPosition(float x, float y, float z) {
eye = new Point3(x, y, z);
// TODO: update current camera
}
/**
* sets camera target (look at)
* @param x x value
* @param y y value
* @param z z value
*/
public void setCameraTarget(float x, float y, float z) {
target = new Point3(x, y, z);
// TODO: update current camera
}
/**
* ? camera direction/rotation?
* @param value0
* @param value1
* @param value2
*/
public void setCameraUp(float value0, float value1, float value2) {
up = new Vector3(value0, value1, value2);
// TODO: update current camera
}
/**
* set a pinhole camera
* @param name Individual Name
* @param fov Field of View
* @param aspect Aspect Ratio
* @param shiftX ?
* @param shiftY ?
*/
public void setPinholeCamera(String name, float fov, float aspect, float shiftX, float shiftY) {
// set cameraname for rendering
currCamera = name;

sunflow.parameter("transform", Matrix4.lookAt(eye, target, up)); 
sunflow.parameter("fov", fov);
sunflow.parameter("aspect", aspect);
sunflow.parameter("shift.x", shiftX);
sunflow.parameter("shift.y", shiftY);

sunflow.camera(name, CAMERA_PINHOLE);
}

/**
* Set thinlens camera
* @param name Individual name
* @param fov Field of View
* @param aspect Aspect ratio
* @param shiftX ?
* @param shiftY ?
* @param focusDistance focal blur setting
* @param lensRadius Lens radius
* @param sides ?
* @param lensRotation Lens rotation
*/
public void setThinlensCamera(String name, float fov, float aspect, float shiftX, float shiftY, float focusDistance, float lensRadius, float sides, float lensRotation) {
// set cameraname for rendering
currCamera = name;

sunflow.parameter("transform", Matrix4.lookAt(eye, target, up)); 
sunflow.parameter("fov", fov);
sunflow.parameter("aspect", aspect);
sunflow.parameter("shift.x", shiftX);
sunflow.parameter("shift.y", shiftY);
sunflow.parameter("focus.distance", focusDistance);
sunflow.parameter("lens.radius", lensRadius);
sunflow.parameter("lens.sides", sides);
sunflow.parameter("lens.rotation", lensRotation);

sunflow.camera(name, CAMERA_THINLENS);
}

/**
* set fisheye camera
* @param name Individual name
*/
public void setFisheyeCamera(String name) {
// set cameraname for rendering
currCamera = name;

sunflow.parameter("transform", Matrix4.lookAt(eye, target, up)); 

sunflow.camera(name, CAMERA_FISHEYE);
}

/**
* set spherical camera
* @param name Individual name
*/
public void setSphericalCamera(String name) {
// set cameraname for rendering
currCamera = name;

sunflow.parameter("transform", Matrix4.lookAt(eye, target, up)); 

sunflow.camera(name, CAMERA_SPHERICAL);
}

/*
* END OF CAMERAS
* --------------------------------------------------------------------------------------
*/

/*
* --------------------------------------------------------------------------------------
* GLOBAL ILLUMINATION ENGINE
*/

/**
* sets ambient occlusion gi engine
* @param bright
* @param dark
* @param samples
* @param maxDist
*/
public void setAmbientOcclusionEngine(Color bright, Color dark, int samples, float maxDist) {
sunflow.parameter("gi.engine", GI_AMBIENT_OCCLUSION); 
sunflow.parameter("gi.ambocc.bright", colorSpace, bright.getRed(), bright.getGreen(), bright.getBlue());
sunflow.parameter("gi.ambocc.dark", colorSpace, dark.getRed(), dark.getGreen(), dark.getBlue());
sunflow.parameter("gi.ambocc.samples", samples);
sunflow.parameter("gi.ambocc.maxdist", maxDist);
}

/*
* END OF GLOBAL ILLUMINATION ENGINE
* --------------------------------------------------------------------------------------
*/

/**
* Sets color Space, default is COLORSPACE_SRGB_NONLINEAR
* @param theColorSpace either COLORSPACE_SRGB_NONLINEAR, COLORSPACE_SRGB_LINEAR, or COLORSPACE_XYZ
*/
public void setColorSpace(String theColorSpace) {
if(theColorSpace == COLORSPACE_SRGB_NONLINEAR) colorSpace = theColorSpace;
else if(theColorSpace == COLORSPACE_SRGB_LINEAR) colorSpace = theColorSpace;
else if(theColorSpace == COLORSPACE_XYZ) colorSpace = theColorSpace; 
else System.out.println("Colorspace not found, keeping current one");
}

public void render(){

}
public void render(boolean isPreview) {

}
public void render(String fileName) {

}
public void render(boolean isPreview, String fileName) {

}

public SunflowAPI sunflowObject() {
return sunflow;
}
}