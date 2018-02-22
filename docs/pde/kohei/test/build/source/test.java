import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class test extends PApplet {

int n;
float[] x, y;
float[] theta, radius;
float[] speed;

public void setup() {
  //size(window.innerWidth, window.innerHeight);
  

  n = 20;
  x = new float[n];
  y = new float[n];
  theta = new float[n];
  radius = new float[n];
  speed = new float[n];

  for (int i=0; i<n; i++) {
    x[i] = random(width);
    y[i] = random(height);
    theta[i] = random(2.0f*PI);
    radius[i] = random(10, 200);
    speed[i] = random(5.0f);
  }
}

public void draw() {
  background(0);
  float t = millis()/1000.0f;

  noStroke();
  fill(255,100);
  for (int i=0; i<n; i++) {
    theta[i] += 0.01f*speed[i];

    pushMatrix();
    translate(x[i], y[i]);
    rotate(theta[i]);
    drawTriangle(radius[i]);
    popMatrix();
  }
}

public void mousePressed(){

}

public void drawTriangle(float radius) {
  float theta = 0;
  beginShape();
  for (int i=0; i<3; i++) {
    vertex(radius*cos(theta), radius*sin(theta));
    theta += 2.0f*PI/3.0f;
  }
  endShape(CLOSE);
}
  public void settings() {  size(600,400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "test" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
