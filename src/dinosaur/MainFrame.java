package dinosaur;

import javax.swing.*;

import dinosaur.Sound;

import java.awt.*;
/**
 * 主窗体
 */
public class MainFrame extends JFrame {
    GamePanl p;

    public MainFrame() {
        //窗口关闭
    	Sound.backgroud();// 播放背景音乐
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new GamePanl();
        //获取窗体的主容器
        Container c = getContentPane();
        c.add(p);//把面板添加到主容器中

        addKeyListener(p);//添加键盘监听

    }

    public static void main(String[] args) { 

        MainFrame frame = new MainFrame();
        //窗体大小
        frame.setBounds(340, 220, 734, 320);
        //窗体可见
        frame.setVisible(true);
        //屏幕居中
        frame.setLocationRelativeTo(null);
        frame.setTitle("奔跑吧！小恐龙！");


    }


    //复活，把死掉的东西删掉，重新来过
    public void restart(){
        Container c=getContentPane();
        //删除所有死的组件
        c.removeAll();;

        GamePanl gp=new GamePanl();//创建新的游戏版面
        c.add(gp);

        addKeyListener(gp);//添加键盘事件

        c.validate();//重新验证容器组件

    }


public static   int topScore=0;//最高分




}
