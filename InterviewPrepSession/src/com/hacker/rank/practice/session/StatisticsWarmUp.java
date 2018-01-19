package com.hacker.rank.practice.session;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class StatisticsWarmUp {
	public static final double CONFIDENCE_INTERVAL = 1.96;

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);
		int noOfTC=sc.nextInt(); 
		int [] input=new int[noOfTC];
		int i=0;
		while(i<noOfTC){
			input[i++]=sc.nextInt();
		}
		sc.close();
		new StatisticsWarmUp().calculateAndDisplySamplingDetails(input);
	}

	// average of all the integers
	// median no. of integer is odd , then middle element
	// average of middle two elements
	// element which occurs frequently , if multiple elements satisfy criteria
	// display numerically smallest one

	public void calculateAndDisplySamplingDetails(int[] arr) {
		double avg = getTrucatedDouble(getAverage(arr));
		System.out.println(avg);
		double median =getTrucatedDouble(getMedian(arr));
		System.out.println(median);
		System.out.println(getMode(arr));
		double sd = getTrucatedDouble(getTrucatedDouble(getStandardDeviation(arr, avg)));
		System.out.println(sd);
		double cl = getConfidenceLevel(sd, arr.length);
		double min = getTrucatedDouble(avg - cl);
		double max = getTrucatedDouble(avg + cl);
		System.out.print(min + " " + max);
	}

	public double getTrucatedDouble(double val) {
		Double tobetrucated = new Double(val);
		Double d = BigDecimal.valueOf(tobetrucated).setScale(1, RoundingMode.HALF_UP).doubleValue();
		return d;
	}

	public double getMedian(int[] arr) {
		Arrays.sort(arr);
		int index = arr.length % 2;
		int midEl=arr.length / 2;
		if (index != 0) {
			return arr[midEl+1];
		}
		int sum=arr[midEl-1] + arr[midEl];
		return (double) sum/ (double)(2);
	}

	public double getAverage(int[] arr) {
		int average = 0;
		for (int i = 0; i < arr.length; i++) {
			average += arr[i];
		}
		return (double) average / (double) arr.length;
	}

	public double getStandardDeviation(int[] arr, double mean) {
		double res = 0;
		int len = arr.length;
		for (int i = 0; i < arr.length; i++) {
			res += Math.pow((arr[i] - mean),2);
		}
		return Math.pow((res / (double) len), 0.5);
	}

	public double getConfidenceLevel(double sd, int len) {

		double cl = CONFIDENCE_INTERVAL * (sd / Math.sqrt(len));
		return cl;
	}

	public int getMode(int[] arr) {
		TreeMap<Integer, Integer> trM = new TreeMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			Integer key = trM.get(arr[i]);
			if (key == null) {
				key = 1;
			} else {
				key += 1;
			}
			trM.put(arr[i], key);
		} // end of loop

		int maxCount = Integer.MIN_VALUE;
		TreeSet<Integer> ocuEle = new TreeSet<>();
		for (Map.Entry<Integer, Integer> entry : trM.entrySet()) {
			if (entry.getValue() >= maxCount) {
				maxCount = entry.getValue();
				ocuEle.add(entry.getKey());
			}
		}

		return ocuEle.first();

	}// end of function
}
