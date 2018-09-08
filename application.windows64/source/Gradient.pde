color colors[] = new color[]{color(255,0,0), color(0,255,0), color(0,0,255)};

void setup() {
  size(800, 600);
}

void draw() {
  loadPixels();
  for(int i = 0; i < height; i++) {
    color c = getColor(i);
    for(int j = 0; j < width; j++) {
      int loc = j + i * width;
      pixels[loc] = c;
    }
  }
  updatePixels();
  noLoop();
}

color getColor(int y) {
  float colorIndUnaccurate = ((colors.length - 1) * y) / (float)height;
  int colorInd = floor(colorIndUnaccurate);
  color c = colors[colorInd];
  if(colorIndUnaccurate > colorInd) {
    color next = colors[colorInd + 1];
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
