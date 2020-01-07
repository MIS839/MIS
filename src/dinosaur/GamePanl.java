package dinosaur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��Ϸ���
 * 
 *
 *
 */
public class GamePanl extends JPanel implements KeyListener {
    //��ͼƬ
    BufferedImage image;

    //��ͼ���ߣ�����
    Graphics2D g2;
    Dinosaur klong;//����
    boolean finish = false;//��Ϸ����

    static final int FRESH = 10;//ˢ��ʱ�䣬����

    BackgroundImage background;



    List<Obstacle> list = new ArrayList<Obstacle>();//�ϰ��Ｏ��

    int addObjectTimer = 0;
    Obstacle ob=new Obstacle();


    int score=0;//����
    int addScoreTimer=0;

    //���췽��
    public GamePanl() {
        image = new BufferedImage(734, 286, BufferedImage.TYPE_INT_BGR);
        g2 = image.createGraphics();
        klong = new Dinosaur();//ʵ����������
        background = new BackgroundImage();//ʵ��������ͼƬ

        list.add(new Obstacle());


        FreshThread t = new FreshThread(this);//ˢ���߳�
        t.start();

    }

    private void painImage() {//����ͼƬ
        klong.move();//��С�����ƶ�
        background.roll();//���ù���ͼƬ
        g2.drawImage(background.image, 0, 0, this);//���Ʊ���
        g2.drawImage(background.image_yun, background.x_yun,background.y_yun, this);//���ư���

         g2.drawImage(klong.image, klong.x, klong.y, this);//���ƿ���

        if (addObjectTimer >= 1400) {//�������ʱ���ж�
            list.add(new Obstacle());
            addObjectTimer = 0;
        }

        for (int i = 0; i < list.size(); i++) {
            Obstacle o = list.get(i);
            o.move();
            o.bridMove();

            g2.drawImage(o.image, o.x, o.y, this);//�����ϰ�



            //�ж��ϰ����Ƿ��ͷ������ײ
            if (o.bounds().intersects(klong.bounds1()) || o.bounds().intersects(klong.bounds2())){
            	Sound.hit();

                gameOver();//��Ϸ����

            }
        }
        //����++
        if (addObjectTimer>=50){
            score+=1;
            addScoreTimer=0;
        }

        //������ʾ����---"%05d"---ָ���Ƿ�������λ����ʾ
        g2.drawString(String.format("%05d",score),600,35 );
        g2.drawString("Hi",536,35 );
        g2.drawString(String.format("%05d",+MainFrame.topScore),550,35 );


        addObjectTimer += FRESH;
        addScoreTimer +=FRESH;
    }


    //��Ϸ����
    public void gameOver(){
        finish=true;
//        image=background.image_over;
        g2.drawImage(background.image_over,background.x_over,background.y_over,null);
        if (score>MainFrame.topScore){//�ж���߷�
            MainFrame.topScore=score;
        }


    }

    @Override
    public void paint(Graphics g) {
        painImage();
        g.drawImage(image, 0, 0, this);
    }

    /**
     * ��Ϸ�Ƿ����
     * 
     * @return
     */
    public boolean isFinish() {
        return finish;
    }

        //���̼���
    @Override
    public void keyTyped(KeyEvent e) {//��������

    }

    @Override
    public void keyPressed(KeyEvent e) {//��������
        int code = e.getKeyCode();//��ȡ���µı���
        if (code == KeyEvent.VK_SPACE) {
            klong.jump();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {//����̧��

    }
}
