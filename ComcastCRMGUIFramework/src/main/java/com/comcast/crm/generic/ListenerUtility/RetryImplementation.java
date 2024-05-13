package com.comcast.crm.generic.ListenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImplementation implements IRetryAnalyzer{

	int count=0;
	int limitCount=3;
	public boolean retry(ITestResult result) {
		
		if(count<limitCount)
		{
			count++;
			return true;
		}
		return false;
	}

}
