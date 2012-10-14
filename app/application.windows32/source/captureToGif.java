import processing.core.*; 
import processing.xml.*; 

import com.onformative.screencapturer.*; 
import gifAnimation.*; 
import processing.opengl.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class captureToGif extends PApplet {

// INSTRUCTIONS
// Change capture with and height if needed
// Focus on the main window and hit space to record
// Capture again to overwrite last capture
// Stop the program and run again for a new capture
// Repository: https://github.com/raphaelbastide/captureToGif





ScreenCapturer capturer;
GifMaker gifExport;
int captureWidth = 500;
int captureHeight = 320;
boolean recording = false;
String readytext = "Hit SPACE to record";
String rectext = "Rec.";
Date d = new Date();
long timestamp = d.getTime()/1000; 
String name = "gifs/capture-" + timestamp + ".gif";

public void setup() {
  frameRate(30);
  size(captureWidth, captureHeight, OPENGL);
  capturer = new ScreenCapturer(width, height, 30); // 30 = framerate of the capture

  println("gifAnimation " + Gif.version());
  gifExport = new GifMaker(this, name);
  gifExport.setRepeat(0); // make it an "endless" animation

  noStroke();
}

public void draw() {
  background(0);
  image(capturer.getImage(), 0, 0);
  gifExport.setDelay(1);
  if (recording) {
    gifExport.addFrame();
    fill(0, 0, 0, 200);
    rect(0, 0, 35, 20);
    fill(255, 0, 20, 255);
    text(rectext, 5, 13);
  }else{
    fill(0, 0, 0, 200);
    rect(0, 0, 122, 20);
    fill(255, 255, 255, 255);
    text(readytext, 5, 13);
  }
}
public void keyPressed() {
  if (key == ' ') {
    if (recording) {
      recording = !recording;
      gifExport.finish();
      println("Gif exported");
    }
    else {
      recording = !recording;
      draw();
      println("Recording\u2026");
    }
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "captureToGif" });
  }
}
