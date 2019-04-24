package com.luan.utils;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    /**
     * add by mengye 2016-1-19
     * 一天中一共的毫秒数
     */
    public static long millionSecondsOfDay = 86400000;
    /**
     * 时间字符串格式
     */
    public final static String HALF_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND = "yyMMddHHmmssSS";	//年月日时分秒毫秒格式
    public final static String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND = "yyyyMMddHHmmssSS";	//年月日时分秒毫秒格式
    public final static String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND 			 = "yyyyMMddHHmmss";	//年月日时分秒格式
    public final static String YEAR_MONTH_DAY_HOUR_MINUTE 					 = "yyyyMMddHHmm";	//年月日时分格式
    public final static String YEAR_MONTH_DAY_HOUR 							 = "yyyyMMddHH";	//年月日时格式
    public final static String YEAR_MONTH_DAY 								 = "yyyyMMdd";	//年月日格式

    public final static String STANDARD_DATE 			 					 = "yyyy-MM-dd HH:mm:ss";	//年月日时分秒格式
    public final static String YEAR_MONTH_DAY_ 								 = "yyyy-MM-dd";	//年-月-日格式
    public final static String YEAR_MONTH_ 									 = "yyyy-MM";	//年-月格式
    /**
     * 将Calendar转换为指定格式字符串
     *
     * @param calendar
     * @param formatType
     * @return
     */
    public static String getStringByCalendar(Calendar calendar,String formatType){
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(calendar.getTime());

    };
    /**
     * 将Date类型转换为指定格式
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateTimeFormat(Date date,String formatStr){
        DateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(date);

    }
    /**
     * <p>获得时间不同格式的时间字符串</p>
     */
    public static String getDateTimeChars(String formatString)
    {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(cal.getTime());
    }
    /**
     * <p>获得时间对应的星期数</p>
     * @param dateString
     */
    public static void getWeek(String dateString)
    {
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        @SuppressWarnings("unused")
        Date date = new Date();
        try
        {
            date = sdfInput.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek<0)dayOfWeek=0;
        System.out.println(dayNames[dayOfWeek]);
    }
    /**
     * <p>以基准日期的天数获得时间对应的星期数</p>
     */
    public static void getWeek2(int year,int month,int day)
    {
        int count=0,i=1,j=1970;
        while(j<year)
        {
            if(j%4==0&&j%100!=0||j%400==0) count+=366;
            else count+=365;
            j++;
        }
        while(i<month)
        {
            if(i==1||i==3||i==5||i==7||i==8||i==10||i==12) count+=31;
            if(i==4||i==6||i==9||i==11) count+=30;
            if(i==2)
                if(year%4==0&&year%100!=0||year%400==0) count+=29;
                else count+=28;
            i++;
        }
        count+=day;
        switch(count%7)
        {
            case 1:System.out.println("星期四");break;
            case 2:System.out.println("星期五");break;
            case 3:System.out.println("星期六");break;
            case 4:System.out.println("星期天");break;
            case 5:System.out.println("星期一");break;
            case 6:System.out.println("星期二");break;
            case 0:System.out.println("星期三");break;
        }

    }
    /**
     * <p>根据日期返回对应的星期</p>
     * @param date
     * @return 星期
     */
    public static String getFormatDateWeek(Date date)
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEEE");
        return bartDateFormat.format(date);
    }

    /**
     * <p>获得指定格式的时间一周时间,按顺序从周一到周日</p>
     * @param dateFormatStr
     * @return 一周时间(中国人习惯)
     */
    @SuppressWarnings("static-access")
    public static String[] getWeekOfDayChina(String dateFormatStr)
    {
        String[] arr=new String[7];
        Date date=null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
        sdf.applyPattern(dateFormatStr);
        for(int i=1,k=0;i<8;i++,k++)
        {
            calendar.set(Calendar.DAY_OF_WEEK,i);
            if(k>0)
            {
                date=calendar.getTime();
                String timeStr = sdf.format(date);
                arr[i-2]=timeStr;
                calendar.setTime(date);
            }

        }
        calendar.add(Calendar.DATE,1);
        date=calendar.getTime();
        String timeStr = sdf.format(date);
        arr[6]=timeStr;
        return arr;
    }

    /**
     * <p>获得指定格式的时间一周时间,按顺序从周日到周六</p>
     * @param dateFormatStr
     * @return 一周时间(西方习惯)
     */
    @SuppressWarnings("static-access")
    public static String[] getWeekOfDayEnlish(String dateFormatStr)
    {
        String[] arr=new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
        sdf.applyPattern(dateFormatStr);
        int []week={Calendar.SUNDAY,Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY};
        for(int i=0;i<week.length;i++)
        {
            Calendar c = Calendar.getInstance();
            Date date=new Date();
            c.setTime(date);
            c.set(Calendar.DAY_OF_WEEK,week[i]);
            String timeStr=new SimpleDateFormat(dateFormatStr).format(c.getTime());
            arr[i]=timeStr;
        }
        return arr;
    }
    /**
     * <p>获得时间对应的星期数</p>
     */
    public static String  getWeeks(String inputTimeStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
        @SuppressWarnings("unused")
        Date date = null;
        try
        {
            date = sdf.parse(inputTimeStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEEE");
        return bartDateFormat.format(date);
    }
    /**
     * <p>返回指定格式的第n天后的时间串</p>
     * @param date
     * @param n
     * @param dateFormatStr
     * @return n天后的时间串
     */
    public static String afterNDay(Date date,int n,String dateFormatStr)
    {
        Calendar c=Calendar.getInstance();
        DateFormat df=new SimpleDateFormat(dateFormatStr);
        c.setTime(date);
        c.add(Calendar.DATE,n);
        Date d=c.getTime();
        String s=df.format(d);
        return s;
    }
    /**
     * <p>返回指定格式的第n天后的时间串集合</p>
     * @param date
     * @param n
     * @param dateFormatStr
     * @return n天后的时间串
     */
    public static String[] getAfterNDay(Date date,int n,String dateFormatStr)
    {
        Calendar c=Calendar.getInstance();
        String []st=new String[n];
        DateFormat df=new SimpleDateFormat(dateFormatStr);
        for(int i=0;i<n;i++)
        {
            c.setTime(date);
            c.add(Calendar.DATE,i);
            Date d=c.getTime();
            String s=df.format(d);
            st[i]=s;
        }

        return st;
    }
    /**
     * <p>返回指定格式的第n天前的时间串集合</p>
     * @param date
     * @param n
     * @param dateFormatStr
     * @return n天后的时间串
     */
    public static String[] getBeforNDay(Date date,int n,String dateFormatStr)
    {
        Calendar c=Calendar.getInstance();
        String []st=new String[n];
        DateFormat df=new SimpleDateFormat(dateFormatStr);
        for(int i=n;i<0;i++)
        {
            c.setTime(date);
            c.add(Calendar.DATE,i);
            Date d=c.getTime();
            String s=df.format(d);
            st[i]=s;
        }

        return st;
    }

    /**
     * <p>返回指定格式的第n天后的时间串集合</p>
     * @param n
     * @param dateFormatStr
     * @return n天后的时间串
     */
    public static String[] getAfterNDay(int n,String dateFormatStr)
    {
        Calendar c=Calendar.getInstance();
        String []st=new String[n];
        Date date=new Date();

        DateFormat df=new SimpleDateFormat(dateFormatStr);
        String ss=df.format(date);
        c.setTime(date);
        c.add(Calendar.DATE,n);
        Date d=c.getTime();
        String s=df.format(d);
        System.out.println(ss);
        System.out.println(s);
        st[0]=ss;
        st[0]=s;
        return st;
    }

    /**
     * <p>根据指定格式获得时间串</p>
     */
    public static String  getFormatDate(String inputTimeStr,String formatStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
        @SuppressWarnings("unused")
        Date date = null;
        try
        {
            date = sdf.parse(inputTimeStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(formatStr);
        return bartDateFormat.format(date);
    }
    /**
     * <p>根据指定格式获得当前时间串</p>
     */
    public static String  getFormatCurrentDate(String formatStr)
    {
        Date date = new Date();
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(formatStr);
        return bartDateFormat.format(date);
    }
    /**
     * <p>将短时间格式字符串转换为时间formatStr </p>
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate,String formatStr)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        Date strtodate = null;
        try
        {
            strtodate = formatter.parse(strDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return strtodate;
    }
    public static void main(String[] args)
    {
        System.out.println(getDateTimeChars(STANDARD_DATE));
//        String[]st=getWeekOfDayEnlish("MM月dd日--EEEE");
//        getWeekOfDayChina("MM月dd日");
//        for(int i=0;i<st.length;i++)
//        {
//
//            System.out.println(st[i]);
//        }
       /* String[]st=getAfterNDay(new Date(),6,"EEEE");
        for(int i=0;i<st.length;i++)
        {
            System.out.println(st[i]);
        }*/
        //getAfterNDay(6,"yyyy,MM月dd日");
//        System.out.println(getWeeks("2009-03-12"));
//        GregorianCalendar   ca   =   new   GregorianCalendar();
//        System.out.println(ca.get(GregorianCalendar.AM_PM));
//
//        SimpleDateFormat defaultSDF=new SimpleDateFormat("yyyyMM");

        //System.out.println(strToDate("2009-3-13","yyyy-MM-dd"));

    }

    public static  String dateToStr(Date date,String formatStr) {
        String dateStr = "";
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        if(date != null) {
            dateStr = formatter.format(date);
        }

        return dateStr;
    }
    /**
     * 取得当月天数
     * */
    public static int getCurrentMonthLastDay()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到指定月的天数
     * */
    public static int getMonthLastDay(int year, int month)
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    /**
     * add by mengye 2016-1-19
     * 得到两个日期之间相差的天数,两头不算,取出数据后，可以根据需要再加
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferDay(String date1, String date2) {
        Date dateTime1_tmp = parse(date1, "yyyy-MM-dd");
        Date dateTime2_tmp = parse(date2, "yyyy-MM-dd");
        Long d1 = dateTime1_tmp.getTime();
        Long d2 = dateTime2_tmp.getTime();
        return (int) ((d2 - d1) / millionSecondsOfDay);
    }
    /**
     * add by mengye 2016-1-19
     * 根据指定日期字符串格式格式化日期为Date型
     *
     * @param date
     * @param formater
     * @return
     */
    public static Date parse(String date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * add by mengye 2016-1-19
     * 根据指定日期格式格式化日期为Date型
     *
     * @param date
     * @param formater
     * @return
     */
    public static Date parseDate(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        Date result = null;
        try {
            result = sdf.parse(sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  两个时间相差距离多少天多少小时多少分多少秒
     * @author gaodengke
     * @date 2016-08-09
     * @param beginDate 时间参数 1 格式：1990-01-01 12:00:00
     * @param endDate 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(Date beginDate, Date endDate) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = beginDate.getTime();
        long time2 = endDate.getTime();
        long diff ;
        if(time1<time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 计算自然天
     * @param startDate
     * @param endDate
     * @return
     */
    public static int naturalDay(Date startDate,Date endDate){
        LocalDate start =new LocalDate();
        LocalDate end = new LocalDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(endDate);
        start=new LocalDate(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH) + 1 , calendar1.get(Calendar.DAY_OF_MONTH));
        end=new LocalDate(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH) + 1 , calendar2.get(Calendar.DAY_OF_MONTH));
        //计算自然天的差
        int days = Days.daysBetween(start, end).getDays()+1;
        return days;
    }

    /**
     * 生成合样码时间
     * @return
     */
    public static String getMargeCodeTime() {
        String standardDate = getCurrentDate(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        return  getShifts() + standardDate.substring(4,8);
    }

    /**
     * 获取当前班次
     * @return
     */
    public static String getShifts() {
        String standardDate = getCurrentDate(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        String yearMonthDay = standardDate.substring(0,8);
        //20190107000000
        String zoreTime=yearMonthDay+"000000";
        String eightTime=yearMonthDay+"080000";
        String fourTime=yearMonthDay+"160000";
        String t;
        if(standardDate.compareTo(zoreTime)>=0 && standardDate.compareTo(eightTime)<0){
            t="0";
        }else if(standardDate.compareTo(eightTime)>=0 && standardDate.compareTo(fourTime)<0){
            t="8";
        }else{
            t="4";
        }
        return  t;
    }

    /**
     * 获取当前时间字符串
     * @param dateformat
     * @return
     */
    public  static  String getCurrentDate(String dateformat){
       return new SimpleDateFormat(dateformat).format(new Date());
    }
    public  static  String getCurrentDate(){
            return getCurrentDate(STANDARD_DATE);
    }
}
