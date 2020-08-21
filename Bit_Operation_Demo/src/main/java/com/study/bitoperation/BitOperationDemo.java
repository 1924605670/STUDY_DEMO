package com.study.bitoperation;

import cn.hutool.core.util.NumberUtil;
import com.study.bitoperation.utils.AESUtil;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-08-06 09:48
 * @Description
 **/
public class BitOperationDemo {

    /**
     * int 是4个字节，一个字节4位
     * 0000 0000 0000 0001
     */
    private static final int A = 1 << 0;

    /**
     * int 是4个字节，一个字节4位
     * 0000 0000 0000 0010
     */
    private static final int B = 1 << 1;

    /**
     * int 是4个字节，一个字节4位
     * 0000 0000 0000 0100
     */
    private static final int C = 1 << 2;

    /**
     * int 是4个字节，一个字节4位
     * 0000 0000 0000 1000
     */
    private static final int D = 1 << 3;

    /**
     * int 是4个字节，一个字节4位
     * 0000 0000 0001 0000
     */
    private static final int E = 1 << 4;

    /**
     * int 是4个字节，一个字节4位
     * 0000 0000 0010 0000
     */
    private static final int F = 1 << 5;

    //用来保存当前存在的权限，即用这一个字段，保存8种权限状态
    private int state = 0;

    /**
     * 设置
     *
     * @param bit
     */
    public void setState(int bit) {
        state = bit;
    }


    /**
     * 增加
     * ｜ 运算
     * 0001
     * 0010
     * --------
     * 0011
     *
     * @param bit
     */
    public void addBit(int bit) {
        state = state | bit;
    }

    /**
     * 减少
     * ～0001 = 1110
     * <p>
     * & 运算
     * 0001
     * 1110
     * -----
     * 0000
     *
     * @param bit
     */
    public void delBit(int bit) {
        state = state & ~bit;
    }

    /**
     * 是否拥有该bit
     * <p>
     * 0011
     * 0001
     * ------
     * 0001
     *
     * @param bit
     * @return
     */
    public boolean hasBit(int bit) {
        return ((state & bit) == bit);
    }

    /**
     * 是否没有该bit
     *
     * @param bit
     * @return
     */
    public boolean notHasBit(int bit) {
        return (state & bit) == 0;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        System.out.println(E);
        System.out.println(F);

        BitOperationDemo bod = new BitOperationDemo();
        bod.addBit(A);
        bod.addBit(B);
        bod.addBit(C);
        bod.addBit(D);
        bod.addBit(E);
        bod.addBit(F);

        String s = AESUtil.encryptByAESWithInt(bod.state);
        System.out.println(s);
        String res = AESUtil.decryptByAES(s);
        System.out.println(NumberUtil.parseInt(res.substring(res.indexOf("#") + 1)));

    }


}
