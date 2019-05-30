package collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author missli
 * @Description
 * @Date 2019/5/30 14:35
 **/
public class CollectionTest {
    public static void main(String[] args) {
        /**
         * jdk1.8prev：默认数组Entry[] table大小 length = 16 阈值0.75
         * 当数组容量大于 16*0.75 时扩容为当前数组length*2大小，再把原数组的数据重新Hash计算插入到新的位置 见：resize();
         * 构造函数就初始化数组大小
         *
         * jdk1.8next：默认数组大小 1<<4 二进制操作,避免一些转换，提高效率，1左移4位-->10000 = 16  阈值不变
         * Node<K,V>[] table put时初始化数组大小
         * 1、存在数组中
         * 2、存在相同节点--覆盖
         * 3、哈希碰撞后其数组中的节点是否为TreeNode类型--》是则直接添加到树上
         * 4、不是树节点--》先使用链表存，当链表长度大于8或数组长度达64（4倍初始值）--> resize();
         *    否则把链表节点替换成TreeNode:treeifyBin();--> 构建红黑树结构treeify();
         *
         * HashMap线程不安全的两个方面：
         * 1、Hash碰撞的时候，多个线程同时put，这个方法不是同步的，结果势必会丢失数据
         * 2、扩容的时候，resize(); 当多个线程检测到超出阈值的时候会同时调用resize();
         *    各自生成新的数组并rehash后赋给该map底层的数组table，结果只有最后一个线程生成的新数组被赋给table变量,其他线程的均会丢失;
         */
        Map map = new HashMap();
        map.put("sw","1111");
        map.put(null,"e3232");   //key可=null
        System.out.println(map);

        Map map1 = new Hashtable();
       // map1.put(null,"dwqdad");  //key 不能为null
        System.out.println(map1);

        Set setColl =  new HashSet<String>();
        setColl.add("哈哈");
        setColl.add("嘿嘿");
        setColl.add("嘿嘿");
        System.out.println(setColl);

        /**
         * 线程安全的HashMap
         * 使用了大量的volatile 关键字修饰成员变量
         */
        Map conMap = new ConcurrentHashMap<String, String>();

        List list = new ArrayList();
        list.add("adewd");
        System.out.println(list);

    }
}
