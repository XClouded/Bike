package com.yhj.app.bike.utils;

import android.os.Handler;

public class CTimer
{
	public interface CallBack
	{
		void timeTicked(CTimer srcTimer);
	};

	private Handler handler = new Handler();
	private Runnable runnable = null;

	private CTimer.CallBack timeCallback = null;
	private float interval = 1.0f;

	private boolean isStart = false;

	public CTimer(CTimer.CallBack callback)
	{
		this.timeCallback = callback;
		this.interval = 1.0f;

		runnable = new Runnable()
		{
			public void run()
			{
				handler.postDelayed(runnable, (long) (interval * 1000));
				if (timeCallback != null)
				{
					timeCallback.timeTicked(CTimer.this);
				}
			}
		};
	}

	

	public boolean timerIsStart()
	{
		return this.isStart;
	}

	public void startTimer(float seconds)
	{
		this.interval = seconds;
		handler.postDelayed(runnable, (long) (interval * 1000));
		this.isStart = true;
	}

	public void stopTimer()
	{
		if (!this.isStart)
		{
			return;
		}

		handler.removeCallbacks(runnable);
		this.isStart = false;
	}
}
