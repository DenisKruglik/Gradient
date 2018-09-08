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

public class Gradient extends PApplet {

int colors[] = new int[]{color(255,0,0), color(0,255,0), color(0,0,255)};

public void setup() {
  
}

public void draw() {
  loadPixels();
  for(int i = 0; i < height; i++) {
    int c = getColor(i);
    for(int j = 0; j < width; j++) {
      int loc = j + i * width;
      pixels[loc] = c;
    }
  }
  updatePixels();
  noLoop();
}

public int getColor(int y) {
  float colorIndUnaccurate = ((colors.length - 1) * y) / (float)height;
  int colorInd = floor(colorIndUnaccurate);
  int c = colors[colorInd];
  if(colorIndUnaccurate > colorInd) {
    int next = colors[colorInd + 1];
    float diff = colorIndUnaccurate - colorInd;
    float red = (red(next) - red(c)) * diff;
    float green = (green(next) - green(c)) * diff;
    float blue = (blue(next) - blue(c)) * diff;
    c = color(
      red(c) + red,
      green(c) + green,
      blue(c) + blue
    );
  }
  return c;
}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "Gradient" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
