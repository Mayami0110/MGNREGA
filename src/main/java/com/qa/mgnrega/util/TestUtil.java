package com.qa.mgnrega.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.mgnrega.base.TestBase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	public String strStartTime = null;
	private String am_pm;
	private boolean running = false;
	public long startTime;
	long stopTime;
	private int hour;
	private int min;
	private int sec;

	public String strExestartTime = null;
	public long exestartTime;

	long exestopTime;
	
	public static String strAbsolutepath = new File("").getAbsolutePath() +"\\";

	

	

	public String start()
	{
		Calendar calendar = new GregorianCalendar();
		strStartTime = null;
		int hour, min, sec;
		Long actualStartTime = System.currentTimeMillis();
		hour = calendar.get(Calendar.HOUR);
		min = calendar.get(Calendar.MINUTE);
		sec = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0) {
			am_pm = "AM";
		} else {
			am_pm = "PM";
		}
		running = true;
		startTime = actualStartTime;
		strStartTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;
		System.out.println(strStartTime);
		return strStartTime;
	}

	public String Executionstart() 
	{
		Calendar calendar = new GregorianCalendar();
		strExestartTime = null;
		int hour, min, sec;
		Long exeactualStartTime = System.currentTimeMillis();
		hour = calendar.get(Calendar.HOUR);
		min = calendar.get(Calendar.MINUTE);
		sec = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0) {
			am_pm = "AM";
		} else {
			am_pm = "PM";
		}
		running = true;
		exestartTime = exeactualStartTime;
		strExestartTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;
		System.out.println(strExestartTime);
		return strExestartTime;
	}

	public String stop()
	{
		String strStoTime = null;

		Calendar stop = new GregorianCalendar();
		Long actualstopTime = System.currentTimeMillis();
		hour = stop.get(Calendar.HOUR);
		min = stop.get(Calendar.MINUTE);
		sec = stop.get(Calendar.SECOND);
		if (stop.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";
		// .currentTimeMillis();
		stopTime = actualstopTime;
		strStoTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;

		running = false;
		return strStoTime;
	}
	public String exestop() 
	{
		String exestrStoTime = null;

		Calendar stop = new GregorianCalendar();
		Long exeactualstopTime = System.currentTimeMillis();
		hour = stop.get(Calendar.HOUR);
		min = stop.get(Calendar.MINUTE);
		sec = stop.get(Calendar.SECOND);
		if (stop.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";
		// .currentTimeMillis();
		exestopTime = exeactualstopTime;
		exestrStoTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;

		running = false;
		return exestrStoTime;
	}
	public float getElapsedTime() {
		float elapsedTime = 0;
		if (running) {
			elapsedTime = (System.currentTimeMillis() - startTime);
			// .currentTimeMillis() - startTime);
		} else {
			elapsedTime = (stopTime - startTime);
		}
		return elapsedTime;
	}
	public float getExecutionElapsedTime() {
		float elapsedTime = 0;
		if (running) {

			elapsedTime = (System.currentTimeMillis() - exestartTime);
			// .currentTimeMillis() - startTime);
		} else {
			elapsedTime = (exestopTime - exestartTime);
		}
		return elapsedTime;
	}

	public String ExecutionTime( float timeElapsed)
	{
		int seconds = Math.round((timeElapsed / (60000)) * 60);
		int hours = 0;
		int minutes = 0;
		int newSeconds = 0;
		int newSecond1 = 0;
		String strExecutiontime = "";
		if (seconds >= 3600) {
			hours = seconds / 3600;
			minutes = (seconds % 3600) / 60;
			newSeconds = (seconds % 3600) % 60;
			if (minutes == 0) {
				newSecond1 = (seconds % 3600) % 60;
				if (newSecond1 != 0) {
					strExecutiontime = hours + " Hour(s)" + newSecond1 + " Second(s)";
				} else {
					strExecutiontime = hours + " Hour(s)";
				}
			}

			if (minutes > 0 && minutes < 60) {
				if (newSeconds > 0 && minutes > 0) {
					strExecutiontime = hours + " Hour(s) " + minutes + " Minute(s)" + newSeconds + " Second(s)";
				}
				if (newSeconds == 0 && minutes > 0) {
					strExecutiontime = hours + " Hour(s) " + minutes + " Minute(s)";
				}
			}

			if (minutes > 60) {
				seconds = minutes % 60;
				minutes = minutes / 60;
				strExecutiontime = hours + " Hour(s) " + minutes + " Minutes " + seconds + " Seconds";
			}

		} else {
			minutes = seconds / 60;
			seconds = seconds % 60;
			if (minutes > 0 && seconds == 0) {
				strExecutiontime = minutes + " Minute(s)";
			}
			if (minutes > 0 && seconds > 0) {
				strExecutiontime = minutes + " Minute(s) " + seconds + " Second(s)";
			}
			if (minutes == 0) {
				strExecutiontime = seconds + " Second(s)";
			}
		}
		return strExecutiontime;

	}
	
	
	
}
