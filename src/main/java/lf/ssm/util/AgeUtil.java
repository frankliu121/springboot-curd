package lf.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 你年龄，性别判断工具类
 *
 * @author 1100134
 */
public class AgeUtil {

    /**
     * 根据身份证获取年龄
     * @param idCard
     * @return
     */
    public static int IdNOToAge(String idCard){
        int userAge = 0;
        if(idCard.length()==18){
            try {
                String year = idCard.substring(6).substring(0, 4);// 得到年份
                String yue = idCard.substring(10).substring(0, 2);// 得到月份

                Date date = new Date();// 得到当前的系统时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String fyear = format.format(date).substring(0, 4);// 当前年份
                String fyue = format.format(date).substring(5, 7);// 月份

                if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
                    userAge = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
                } else {// 当前用户还没过生
                    userAge = Integer.parseInt(fyear) - Integer.parseInt(year);
                }

            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }else {
            try {
                String uyear = "19" + idCard.substring(6, 8);// 年份
                String uyue = idCard.substring(8, 10);// 月份

                Date date = new Date();// 得到当前的系统时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String fyear = format.format(date).substring(0, 4);// 当前年份
                String fyue = format.format(date).substring(5, 7);// 月份
                if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
                    userAge = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
                } else {// 当前用户还没过生
                    userAge = Integer.parseInt(fyear) - Integer.parseInt(uyear);
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

        }
        return userAge;
    }
}
