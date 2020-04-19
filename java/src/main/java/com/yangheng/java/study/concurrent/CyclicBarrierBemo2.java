package com.yangheng.java.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier案例2
 * 需求：
 *   秦国灭6国(韩、赵、魏、楚、燕、齐)，统一华夏
 */
public class CyclicBarrierBemo2 {
    public static void main(String[] args) {

        int countryLength = CountryEnum.values().length;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(countryLength,()->{
            System.out.println("秦国统一华夏啦");
        });

        for (int c = 0; c < countryLength ; c++) {
            new Thread(new Fight(cyclicBarrier,c),"t"+c).start();
        }
    }
}

/**
 * 秦国战斗灭国家
 */
class Fight implements Runnable{

    private CyclicBarrier cyclicBarrier ;
    private Integer count ;

    public Fight(CyclicBarrier cyclicBarrier,Integer count){
        this.cyclicBarrier = cyclicBarrier ;
        this.count = count ;
    }

    @Override
    public void run() {
        try {
            System.out.println("秦国灭掉["+countryInfo(count)+"]啦");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    //国家信息
    private String countryInfo(Integer count) {
        String format = "国家:%s,编码:%s,建国时间:%s";
        CountryEnum countName = CountryEnum.getCountName(count);
        if (countName == null) {
            return null;
        }
        return String.format(format,countName.getName(),countName.getCode(),countName.getCreateAge());
    }
}

/**
 * 枚举--可以作为静态服务器使用
 */
enum CountryEnum{

    HAN("han","韩国",1490),
    ZAHO("zhao","赵国",1890),
    WEI("wei","魏国",1258),
    CHU("chu","楚国",1867),
    YAN("yan","燕国",1746),
    QI("qi","齐国",1867);

    private String code ;
    private String name ;
    private Integer createAge ;

    CountryEnum(String code,String name,Integer createAge){
        this.code = code ;
        this.name = name ;
        this.createAge = createAge ;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getCreateAge() {
        return createAge;
    }

    public static CountryEnum getCountName(Integer countryCount){
        if(countryCount == null ){
            throw new NullPointerException("参数countryCount不合法为空，值为："+countryCount);
        }
        CountryEnum[] countryEnums = CountryEnum.values();
        CountryEnum countryEnum = countryEnums[countryCount];
        if (countryCount == null) {
            return null ;
        }
        return countryEnum;
    }
}
