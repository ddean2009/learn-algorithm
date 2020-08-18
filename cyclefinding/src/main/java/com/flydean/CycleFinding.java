package com.flydean;

/**
 * @author wayne
 * @version CycleFinding,  2020/8/18
 */
public class CycleFinding {

    public static int entryStep;
    public static int entryPoint;
    public static int cycleLongth;

    //环路生成函数
    //以这样的形式来生成数据：{x0, x1 = f(x0), x2 = f(x1), ..., xi = f(xi-1), ...}
    public static int f(int x){
        return (3*x*x+7*x+5)%97;
    }

    //弗洛伊德兔子乌龟-环检测算法
    public static void floydCycleFinding(int x){
        //第一步：让乌龟和兔子相遇
        // 我们定义两个值，一个是乌龟=f(x),一个是兔子，因为兔子的速度两倍于乌龟，所以我们可以用f(f(x))来表示兔子的值。
        int tortoise = f(x), hare = f(f(x));
        //然后乌龟和兔子一直向前走，直到他们的值相等，表明两者相遇了
        //注意，相遇点并不是环的入口点
        while (tortoise != hare) {
            tortoise = f(tortoise);
            hare = f(f(hare));
        }
        //第二步：找到环的入口点
        //让兔子重新从起点开始起跑，步长和乌龟一致，而乌龟继续原来的位置向后走，当两者相遇的点，就是环的入口点
        entryStep = 0; hare = x;
        while (tortoise != hare) {
            tortoise = f(tortoise);
            hare = f(hare); entryStep++;
        }
        entryPoint= tortoise;

        //第三步：找到环的步长
        //让兔子继续向前走，乌龟不动，当两者再次相遇的时候，就找到了步长
        cycleLongth = 1; hare = f(tortoise);
        while (tortoise != hare) {
            hare = f(hare); cycleLongth++;
        }
    }

    public static void main(String[] args) {
        floydCycleFinding(62);
        System.out.printf("entryPoint: %d,cycleLongth %d\n", entryPoint, cycleLongth);
    }
}
