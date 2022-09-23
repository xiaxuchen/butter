package com.xxc.homework.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class RandomNum
 * @Description
 * @createTime 2019-03-25 11:39
 */
public class RandomNum implements Serializable {
    
    private static final int OK = 1;//相同
    
    private static  final int BIGGER = 0;//大了
    
    private static final int SMALLER = -1;//小了

    private static final int NOINPUT = 2;//没有输入，可能是刷新网页
    
    private int count;//猜的次数

    private int realNum;//答案
    
    private int num;//猜测的bean
    
    private int state;

    private Random random;

    public RandomNum()
    {
        state = NOINPUT;
        random = new Random();
        changeRandom();
    }
    
    public int getRealNum() {
        return realNum;
    }

    public void setRealNum(int realNum) {
        this.realNum = realNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        count++;
        if(Objects.equals(num, realNum))
        {
            state = OK;
            changeRandom();
        }
        else if(num < realNum)
            state = SMALLER;
        else
            state = BIGGER;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 更新猜的数字
     */
    private void changeRandom()
    {
        realNum = random.nextInt(100);
    }

    @Override
    public String toString() {
        return "RandomNum{" +
                "count=" + count +
                ", realNum=" + realNum +
                ", num=" + num +
                ", state=" + state +
                '}';
    }
}
