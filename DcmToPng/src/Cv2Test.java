import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.io.File;
import java.awt.Color;

public class Cv2Test {
    public static Integer isRL() throws  IOException{
        BufferedImage bi = ImageIO.read(new File("D:/cv2/bbb.png"));//通过imageio将图像载入
        int h = bi.getHeight();//获取图像的高
        int w = bi.getWidth();//获取图像的宽
        int rgb = bi.getRGB(0, 0);//获取指定坐标的ARGB的像素值
        int[][] gray = new int[w][h];
        int Left = 0;
        int Right = 0;
        for (int x = 0; x < w / 2; x++) {
            for (int y = 0; y < h; y++) {
                gray[x][y] = getGray(bi.getRGB(x, y));
//                System.out.println(gray[x][y]);
                Left = Left + gray[x][y];
            }
        }
        for (int x = w/2; x < w ; x++) {
            for (int y = 0; y < h; y++) {
                gray[x][y] = getGray(bi.getRGB(x, y));
//                System.out.println(gray[x][y]);
                Right = Right+gray[x][y];
            }
        }
//        System.out.println("Left:"+Left+"  R:"+Right);
        if(Left > Right){

            System.out.println("左边");
            return 0;
        }else {
            System.out.println("右边");
            return 1;
        }

    }

    public static int getGray(int rgb) {
        String str = Integer.toHexString(rgb);
        int r = Integer.parseInt(str.substring(2, 4), 16);
        int g = Integer.parseInt(str.substring(4, 6), 16);
        int b = Integer.parseInt(str.substring(6, 8), 16);
        //or 直接new个color对象
        Color c = new Color(rgb);
        r = c.getRed();
        g = c.getGreen();
        b = c.getBlue();
        int top = (r + g + b) / 3;
        return (int) (top);
    }

}
