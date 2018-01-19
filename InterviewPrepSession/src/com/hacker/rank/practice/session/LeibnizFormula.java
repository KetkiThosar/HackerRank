package com.hacker.rank.practice.session;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class LeibnizFormula {
	public static final double CONFIDENCE_INTERVAL = 1.96;
	public static void main(String [] args){
		  Scanner sc =new Scanner(System.in);
			int noOfTC=sc.nextInt(); 
			Integer [] input=new Integer[noOfTC];
			int i=0;
			while(i<noOfTC){
				input[i++]=sc.nextInt();
			}
			for(Integer num:input){
				new LeibnizFormula().printLeibnizFormula(num);	
			}
			sc.close();
	    }
	    public void printLeibnizFormula(int n){
			double result=0;
			for(int i =0 ; i<n;i++){
				result+=Math.pow(-1,i)/((2*i)+1);
			}
			Double toBeTruncated=new Double(result);
			Double truncated =BigDecimal.valueOf(toBeTruncated).setScale(15,RoundingMode.HALF_UP).doubleValue();
			System.out.println(truncated);
	
	}
}