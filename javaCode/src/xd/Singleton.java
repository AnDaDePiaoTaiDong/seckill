package xd;

/**
 * 线程安全的懒汉双重检查枷锁单例
 * Created by Administrator on 2017/4/8.
 */
public class Singleton {
    //volatile和static两个限制
    private volatile static Singleton singleton;
    //私有构造方法
    private Singleton(){}
    public static Singleton getSingleton()
    {
        //每次只要判断外面的第一个判断条件就行了
        if (singleton==null)
        {//之后的代码第二次进入之后就不用进来了
            synchronized (Singleton.class)
            {
                if (singleton==null)
                {
                    return singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}
