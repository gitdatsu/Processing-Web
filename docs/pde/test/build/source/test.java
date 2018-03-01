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

boolean flag;

public void setup() {
  // size(window.innerWidth, window.innerHeight);
  

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
    speed[i] = random(0.1f,5.0f);
  }

  flag = true;
}

public void draw() {
  background(flag?0:255);
  float t = millis()/1000.0f;

  for (int i=0; i<n; i++) {
    theta[i] += 0.01f*speed[i];
    x[i] += speed[i];
    if(width < x[i]) {
      x[i] -= width;
      y[i] = random(height);
    }

    pushMatrix();
    translate(x[i], y[i]);
    rotate(theta[i]);
    float r = radius[i]-radius[i]/400.0f*dist(x[i], y[i], mouseX, mouseY);
    r = constrain(r, 10, radius[i]);
    drawTriangle(r);
    popMatrix();
  }
}

public void mousePressed(){
  flag = !flag;
}

public void drawTriangle(float radius) {
  float theta = 0;
  float[] tmpX = new float[3];
  float[] tmpY = new float[3];

  noStroke();
  fill(flag?255:0,100);
  beginShape();
  for (int i=0; i<3; i++) {
    tmpX[i] = radius*cos(theta);
    tmpY[i] = radius*sin(theta);
    vertex(tmpX[i], tmpY[i]);
    theta += 2.0f*PI/3.0f;
  }
  endShape(CLOSE);

  stroke(flag?255:0);
  for(int i=0;i<3;i++){
    line(tmpX[i], tmpY[i], mouseX-width/2, mouseY-height/2);
  }

}
  public void settings() {  size(600,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "test" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
