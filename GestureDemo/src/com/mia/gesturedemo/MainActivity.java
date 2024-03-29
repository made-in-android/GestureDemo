package com.mia.gesturedemo;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;

public class MainActivity extends Activity implements OnGesturePerformedListener {

	GestureLibrary mLibrary;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_main);
	 
	   mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
	   if (!mLibrary.load()) {
	     finish();
	   }
	 
	   GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
	   gestures.addOnGesturePerformedListener(this);
	}
	
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		   ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
		 
		   if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
		     String result = predictions.get(0).name;
		 
		     if ("close".equalsIgnoreCase(result)) {
		       finish();
		     } 
		   }
		}
}