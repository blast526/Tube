package com.lsh.tube.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {

	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private Paint paint;
	private int cellWidth, cellHeight;

	private void init() {
		paint = new Paint();
		paint.setColor(Color.parseColor("#ffffff"));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(20);
		paint.setAntiAlias(true);// 抗锯齿
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (cellWidth == 0) {
			cellWidth = getMeasuredWidth();
		}
		if (cellHeight == 0) {
			cellHeight = getMeasuredHeight() / indexArr.length;
		}
		for (int i = 0; i < indexArr.length; i++) {
			float x = cellWidth / 2 - paint.measureText(indexArr[i]) / 2;

			Rect bounds = new Rect();
			paint.getTextBounds(indexArr[i], 0, indexArr[i].length(), bounds);
			float y = cellHeight / 2 + bounds.height() / 2 + i * cellHeight;

			paint.setColor(i == touchIndex ? Color.parseColor("#666666") : Color.parseColor("#ffffff"));

			canvas.drawText(indexArr[i], x, y, paint);

		}
	}

	private int touchIndex = -1;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (isSameIndex(y / cellHeight)) {
				break;
			}
			touchIndex = y / cellHeight;
			if (touchIndex >= 0 && touchIndex < indexArr.length) {
				String word = indexArr[touchIndex];
				if (onTouchIndexListener != null) {
					onTouchIndexListener.onTouchIndex(word);
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			touchIndex = -1;
			break;
		}
		invalidate();
		return true;
	}

	private boolean isSameIndex(int currentTouchIndex) {
		return touchIndex == currentTouchIndex;
	}

	public OnTouchIndexListener getOnTouchIndexListener() {
		return onTouchIndexListener;
	}

	public void setOnTouchIndexListener(OnTouchIndexListener onTouchIndexListener) {
		this.onTouchIndexListener = onTouchIndexListener;
	}

	private OnTouchIndexListener onTouchIndexListener;

	public interface OnTouchIndexListener {
		void onTouchIndex(String word);
	}
}
