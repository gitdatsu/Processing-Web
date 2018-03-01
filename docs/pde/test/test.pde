int n;
float[] x, y;
float[] theta, radius;
float[] speed;

boolean flag;

void setup() {
  size(window.innerWidth, window.innerHeight);
  // size(600,600);

  n = 20;
  x = new float[n];
  y = new float[n];
  theta = new float[n];
  radius = new float[n];
  speed = new float[n];

  for (int i=0; i<n; i++) {
    x[i] = random(width);
    y[i] = random(height);
    theta[i] = random(2.0*PI);
    radius[i] = random(10, 200);
    speed[i] = random(0.1,5.0);
  }

  flag = true;
}

void draw() {
  background(flag?0:255);
  float t = millis()/1000.0;

  for (int i=0; i<n; i++) {
    theta[i] += 0.01*speed[i];
    x[i] += speed[i];
    if(width < x[i]) {
      x[i] -= width;
      y[i] = random(height);
    }

    pushMatrix();
    translate(x[i], y[i]);
    rotate(theta[i]);
    float r = radius[i]-radius[i]/400.0*dist(x[i], y[i], mouseX, mouseY);
    r = constrain(r, 10, radius[i]);
    drawTriangle(r);
    popMatrix();
  }
}

void mousePressed(){
  flag = !flag;
}

void drawTriangle(float radius) {
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
    theta += 2.0*PI/3.0;
  }
  endShape(CLOSE);

  stroke(flag?255:0);
  for(int i=0;i<3;i++){
    line(tmpX[i], tmpY[i], mouseX-width/2, mouseY-height/2);
  }

}
