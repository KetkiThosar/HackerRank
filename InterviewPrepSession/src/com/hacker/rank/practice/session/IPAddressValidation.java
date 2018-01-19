package com.hacker.rank.practice.session;

import java.util.Scanner;
import java.util.regex.Pattern;

public class IPAddressValidation {

	public static final String IPV4 = "IPv4";
	public static final String IPV6 = "IPv6";
	public static final String NEITHER = "Neither";
	public static final String IPV4_SEPARATOR = ".";
	public static final String IPV6_SEPARATOR = ":";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int noOfTC = sc.nextInt();
		String[] input = new String[noOfTC];
		int i = 0;
		while (i < noOfTC) {
			String line = sc.nextLine().trim();
			if(line.isEmpty()){
				continue;
			}
			input[i++] = line.trim();
		}
		sc.close();
		IPAddressValidation ip = new IPAddressValidation();
		for (String str : input) {
			System.out.println(ip.getIPAddressType(str.trim()));
		}
		
	}

	public String getIPAddressType(String input) {
		String type = NEITHER;
		int v4Index = input.indexOf(IPV4_SEPARATOR);
		int v6Index = input.indexOf(IPV6_SEPARATOR);
		if (v4Index >= 0) {
			String[] v4Arr = input.split(Pattern.quote(IPV4_SEPARATOR),0);
			if (v4Arr.length > 4 ||v4Arr.length==0) {
				return type;
			}
			if (isIPv4(v4Arr)) {
				type = IPV4;
			}
		} else if (v6Index >= 0) {
			String[] v6Arr = input.split(IPV6_SEPARATOR,0);
			if (v6Arr.length > 8 ||v6Arr.length==0) {
				return type;
			}
			if (isIPv6(v6Arr)) {
				type = IPV6;
			}
		}
		return type;
	}

	public boolean isIPv4(String[] arr) {
		for (String str : arr) {
			Integer i = getDigit(str);
			if (i == null || i.intValue() > 255 || i.intValue() < 0) {
				return false;
			}
		} // end of for loop
		return true;
	}

	public boolean isIPv6(String[] arr) {
		for (String str : arr) {
			try {
				Integer.parseInt(str, 16);
			} catch (NumberFormatException ex) {
				return false;
			}
		} // end of for loop
		return true;
	}

	public Integer getDigit(String str) {
		Integer i = null;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return i;
		}
		return i;
	}

}
