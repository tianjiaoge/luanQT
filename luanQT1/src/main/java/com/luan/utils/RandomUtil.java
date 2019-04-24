package com.luan.utils;

import java.util.Random;

public class RandomUtil {
	 private static final String numberChar = "0123456789abcdefghijklmnopqrstuvwxyz";
	 private static final String number = "0123456789";
	 public static void main(String[] args) {
	  String random = getRandom(6);
	  System.out.println(random);
	 }
	 
	 /**
	  * 根据系统时间获得指定位数的随机数
	  * @return 获得的随机数
	  */
	 public static String getRandom(int length) 
	 {
		  Long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子
		  StringBuffer sb = new StringBuffer();// 装载生成的随机数
		  Random random = new Random(seed);// 调用种子生成随机数
		  for (int i = 0; i < length; i++) 
		  {
		      sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		  }
		  return sb.toString();
	 }
	 /**
	  * 根据系统时间获得指定位数的随机数(全部为数字)
	  * @return 获得的随机数
	  */
	 public static String getRandomNumberOnly(int length) 
	 {
		 Long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子
		 StringBuffer sb = new StringBuffer();// 装载生成的随机数
		 Random random = new Random(seed);// 调用种子生成随机数
		 for (int i = 0; i < length; i++) 
		 {
			 sb.append(number.charAt(random.nextInt(number.length())));
		 }
		 return sb.toString();
	 }
}
