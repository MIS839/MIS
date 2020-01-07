package dinosaur;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ������
 * 
 * 
 *
 */
public class Dinosaur {
    public int x, y;//����
    BufferedImage image;//ͼƬ
    BufferedImage image1, image2, image3,image_over;
    int stepTime = 0;//��ʱ��
    int fresh = GamePanl.FRESH;

    boolean jumpState = false;//��Ծ��״̬
    int jumpHeight = 100;//��Ծ�߶�
    final int LOWEST_Y = 200;//�������
    int jumpValue = 5;//��Ծ��������

    public Dinosaur() {

        try {
            image1 = ImageIO.read(new File("image/long1.png"));
            image2 = ImageIO.read(new File("image/long2.png"));
            image3 = ImageIO.read(new File("image/long3.png"));
            image_over = ImageIO.read(new File("image/over.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        x = 50;//��ʼ����
        y = LOWEST_Y;//��ʼ
    }

    public void move() {//�ƶ�
        step();//̤��
        if (jumpState) {
            if (y >= LOWEST_Y) {
                jumpValue = -4;
            }
            if (y <= LOWEST_Y - jumpHeight) {
                jumpValue = 4;
            }
            y += jumpValue;
            image = image3;
            if (y >= LOWEST_Y) {
                jumpState = false;
            }

        }
    }


    //̤��
    void step() {
        int tmp = stepTime / 100 % 2;
        if (tmp == 1) {
            image = image1;
        } else {
            image = image2;
        }

        stepTime += fresh;

    }


    public void jump() {//��Ծ
        if (!jumpState) {// ���������Ծ״̬
            Sound.jump();// ������Ծ��Ч
        }
        jumpState = true;
    } 

    //�Ӵ���������
    public Rectangle bounds1(){

        return new Rectangle(x+20,y,20,10);
    } //�Ӵ���������
    public Rectangle bounds2(){
        return new Rectangle(x+5,y+35,20,10);
    }

}
