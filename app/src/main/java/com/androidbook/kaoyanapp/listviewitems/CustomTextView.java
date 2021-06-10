package com.androidbook.kaoyanapp.listviewitems;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


public class CustomTextView extends AppCompatTextView {
	
	public static final String TAG = "CustomTextView";
	
	public CustomTextView(Context context) {
		super(context);
	}
	
	public CustomTextView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}
	
	public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int actionMasked = event.getActionMasked();
		
		Log.e(TAG, "onTouchEvent: actionMasked = " + actionMasked);
		
		return super.onTouchEvent(event);
	}
}
