package com.misslyr.test.extenorimpl;

/**
 * @Author missli
 * @Description
 * @Date 2019/5/29 17:19
 * 如果子类是非抽象类，则必须实现接口中的所有方法；
 *
 * 如果子类是抽象类，则可以不实现接口中的所有方法，因为抽象类中允许有抽象方法的存在！
 *
 * 参考：https://blog.csdn.net/qq_38329988/article/details/80855124
 **/
public class Child001 extends Child01 {

    @Override
    public void addTest() {
        // 必须实现父抽象类的抽象方法
    }

    @Override
    public void excute() {
        // 必须实现 Parent1
    }

    /*@Override
    public void submit() {
        //可选 重写抽象类里的普通方法
        //如果Child01中不重写submit(), 则这里必须实现接口中所有未实现方法--即submit();
        //super.submit();
    }*/
}
