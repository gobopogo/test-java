package com.misslyr.test.thread;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author missli
 * @Description ThreadLocal 实现原理
 *  ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本
 *  
 * @Date 2021/4/16 14:16
 **/
public class ThreadLocalMy<T> {
    private final ConcurrentHashMap<Thread, T> valueMap  = new ConcurrentHashMap();

    public void set(T newValue){
        valueMap.put(Thread.currentThread(), newValue);
    }

    public T get(){
        Thread currentThread = Thread.currentThread();
        T o = valueMap.get(currentThread);
        if(o == null && !valueMap.containsKey(currentThread)){
            o = initValue();
            valueMap.put(currentThread,o);
        }

        return o;
    }

    public void remove(){
        valueMap.remove(Thread.currentThread());
    }

    public T initValue(){
        return null;
    }
}

class Test {

    //随机睡眠1000到2000毫秒
    public static void randSleep(){
        Random random = new Random();
        //生产环境中要避免Random被多线程共享使用，虽然是线程安全的，但会因为竞争同一seed导致性能下降。
        //在 JDK7 之后，可以直接使用 API ThreadLocalRandom， 而在 JDK7 之前， 需要编码保证每个线程持有一个实例
        int rand = random.nextInt(1000) + 1000;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ThreadLocalMy<Integer> threadLocalMy = new ThreadLocalMy();
        Runnable task = () -> {
          for (int i=0; i<5; i++){
              randSleep();
              threadLocalMy.set(i);
              int num = threadLocalMy.get();
              System.out.println("task1："+num);
          }
        };

        Runnable task1 = () ->{
            for (int j=0; j<10; j++){
                randSleep();
                threadLocalMy.set(j);
                int num = threadLocalMy.get();
                System.out.println("task2："+num);
            }
        };

        new Thread(task).start();
        new Thread(task1).start();
        /**
         * ThreadLocal和线程同步机制相比有什么优势呢？ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
         *
         * 在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，
         * 使用同步机制要求程序慎密地分析什么时候对变量进行读写，什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。
         *
         * 而ThreadLocal则从另一个角度来解决多线程的并发访问。ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
         * 因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。
         * ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
         * 由于ThreadLocal中可以持有任何类型的对象，低版本JDK所提供的get()返回的是Object对象，需要强制类型转换。
         * 但JDK 5.0通过泛型很好的解决了这个问题，在一定程度地简化ThreadLocal的使用，代码清单 9 2就使用了JDK 5.0新的ThreadLocal<T>版本。范型
         *
         * ThreadLocal 的使用场景:用来解决数据库连接、Session 管理等。
         */
    }
}