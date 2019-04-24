package com.luan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperUtils {

	  private static final Logger logger = LoggerFactory.getLogger(ProperUtils.class);
	    private static Properties props;
	    public  static  boolean isLoad =true; //是否需要从新加载配置文件
	    static{
	        props = new Properties();
	        InputStream in = null;
			if (isLoad){
				try {
					/*<!--第一种，通过类加载器进行获取properties文件流-->*/
					in = ProperUtils.class.getClassLoader().getResourceAsStream("appConfig.properties");
					/*<!--第二种，通过类进行获取properties文件流-->*/
					//in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
					props.load(in);
					isLoad = false;
					logger.info("properties文件内容：" + props);
				} catch (FileNotFoundException e) {
					logger.error("appConfig.properties文件未找到");
				} catch (IOException e) {
					logger.error("出现IOException");
				} finally {
					try {
						if(null != in) {
							in.close();
						}
					} catch (IOException e) {
						logger.error("jdbc.properties文件流关闭出现异常");
					}
				}
			}
	    }

	    public static String getProperty(String key){
	        return props.getProperty(key);
	    }

		public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

		public static void setProperty(String key,String value){
	    	props.setProperty(key,value);
		}
}
