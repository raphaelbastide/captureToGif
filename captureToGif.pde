// INSTRUCTIONS
// Change capture with and height if needed
// Focus on the main window and hit space to record
// Capture again to overwrite last capture
// Stop the program and run again for a new capture
// Repository: https://github.com/raphaelbastide/captureToGif

import com.onformative.screencapturer.*;
import gifAnimation.*;
import processing.opengl.*;

ScreenCapturer capturer;
GifMaker gifExport;
int captureWidth = 430;
int captureHeight = 120;
boolean recording = false;
String readytext = "Hit SPACE to record";
String rectext = "Rec.";
Date d = new Date();
long timestamp = d.getTime()/1000; 
String name = "gifs/capture-" + timestamp + ".gif";

public void setup() {
  size(captureWidth, captureHeight, OPENGL);
  capturer = new ScreenCapturer(width, height, 30); // 30 = framerate of the capture

  println("gifAnimation " + Gif.version());
  gifExport = new GifMaker(this, name);
  gifExport.setRepeat(0); // make it an "endless" animation
}

void draw() {
  background(0);
  image(capturer.getImage(), 0, 0);
  gifExport.setDelay(1);
  if (recording) {
    gifExport.addFrame();
    fill(#FFFFFF);
    text(rectext, 5, 13);
    fill(#BC0517);
    text(rectext, 5, 12);
  }else{ 
    fill(#FFFFFF);
    text(readytext, 5, 13);
    fill(#198025);
    text(readytext, 5, 12);
  }
}
void keyPressed() {
  if (key == ' ') {
    if (recording) {
      recording = !recording;
      gifExport.finish();
      println("Gif exported");
    }
    else {
      recording = !recording;
      draw();
      println("Recording…");
    }
  }
}

