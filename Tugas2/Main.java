package ImageProcessing.Tugas2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Main {
    public static void toNegative(){
        BufferedImage img = null;
        File file = null;
        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);
            }
        }
        try {
            ImageIO.write(img,"jpg",new File("D:\\Unsri\\ImageP\\bakwan-negative.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void toGreyscale(){
        BufferedImage img = null;
        File file = null;
        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;


                int av = (int) ((r*0.299)+(g*0.587)+(b*0.144));
                p = (a << 24) | (av << 16) | (av << 8) | av;
                img.setRGB(x, y, p);
            }
        }
        try {
            ImageIO.write(img,"jpg",new File("D:\\Unsri\\ImageP\\bakwan-greyscale.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }

    }
    public static int bound(int value){
        if (value < 0) {
            value = 0;
        }
        else if (value > 255) {
            value = 255;
        }
        return value;
    }

    public static void brightening(){
        BufferedImage img = null;
        File file = null;
        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();
        int brighteness = -20;
        Color cr = new Color(2,3,4);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;


                r = bound(r+brighteness);
                g = bound(g+brighteness);
                b = bound(b+brighteness);

                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);
            }
        }
        try {
            ImageIO.write(img,"jpg",new File("D:\\Unsri\\ImageP\\bakwan-bright.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void addition(){
        BufferedImage [] img = new BufferedImage[2];
        File [] file = new File[2];
        try {
            file [0] = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img [0]= ImageIO.read(file[0]);
            file [1] = new File("D:\\Unsri\\ImageP\\daun.jpg");
            img [1]= ImageIO.read(file[1]);
        }catch (IOException e){
            System.out.println(e);
        }


        int width = img[0].getWidth();
        int height= img[0].getHeight();

        BufferedImage temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);;


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int [] p = new int[2];
                int [] a = new int[2];
                int [] r = new int[2];
                int [] g = new int[2];
                int [] b = new int[2];
                set(p,a,r,g,b,img,x,y);

                int rgba = temp.getRGB(x,y);
                int opacity = (rgba >> 24) & 0xff;
                int red = (rgba >> 16) & 0xff;
                int green = (rgba >> 8) & 0xff;
                int blue = rgba & 0xff;


                red = r[0];
                green = g[0];
                blue = b[0];

                red =  r[0] + r[1];
                green = g[0]+g[1];
                blue = b[0]+b[1];


                rgba = (opacity << 24) | (red << 16) | (green << 8) | blue;
                temp.setRGB(x, y, rgba);
            }
        }
        try {
            ImageIO.write(img[0],"jpg",new File("D:\\Unsri\\ImageP\\result\\bakwan(addition)2.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }



    public static void substraction(){
        BufferedImage [] img = new BufferedImage[2];
       File [] file = new File[2];
        try {
            file [0] = new File("D:\\Unsri\\ImageP\\bakwan+daun.jpg");
            img [0]= ImageIO.read(file[0]);
                file [1] = new File("D:\\Unsri\\ImageP\\daun.jpg");
            img [1]= ImageIO.read(file[1]);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img[0].getWidth();
        int height= img[0].getHeight();

        BufferedImage temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int [] p = new int[2];
                int [] a = new int[2];
                int [] r = new int[2];
                int [] g = new int[2];
                int [] b = new int[2];
                set(p,a,r,g,b,img,x,y);

//                for (int i = 0; i < 2; i++) {
//                    p[i] = img[i].getRGB(x, y);
//                    a[i] = (p[i] >> 24) & 0xff;
//                    r[i] = (p[i] >> 16) & 0xff;
//                    g[i] = (p[i] >> 8) & 0xff;
//                    b[i] = p[i] & 0xff;
//                }
//                int p1 = img[0].getRGB(x, y);
//                int a1 = (p1 >> 24) & 0xff;
//                int r1 = (p1 >> 16) & 0xff;
//                int g1 = (p1 >> 8) & 0xff;
//                int b1 = p1 & 0xff;

//                int p2 = img[1].getRGB(x, y);
//                int a2 = (p2 >> 24) & 0xff;
//                int r2 = (p2 >> 16) & 0xff;
//                int g2 = (p2 >> 8) & 0xff;
//                int b2 = p2 & 0xff;


                r[0] = r[0]-r[1];
                g[0] = g[0]-g[1];
                b[0] = b[0]-b[1];
//                r1 = r1-r2;
//                g1 = g1-g2;
//                b1 = b1-b2;

                p[0] = (a[0] << 24) | (r[0] << 16) | (g[0] << 8) | b[0];
                temp.setRGB(x, y, p[0]);
//                p1 = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
//                img[0].setRGB(x, y, p1);

            }
        }
        try {
            ImageIO.write(img[0],"jpg",new File("D:\\Unsri\\ImageP\\result\\bakwan(subtraction)2.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void multiplication(){
        BufferedImage [] img = new BufferedImage[2];
        File [] file = new File[2];
        try {
            file [0] = new File("D:\\Unsri\\ImageP\\bakwan+daun.jpg");
            img [0]= ImageIO.read(file[0]);
            file [1] = new File("D:\\Unsri\\ImageP\\daun.jpg");
            img [1]= ImageIO.read(file[1]);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img[0].getWidth();
        int height= img[0].getHeight();

        BufferedImage temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int [] p = new int[2];
                int [] a = new int[2];
                int [] r = new int[2];
                int [] g = new int[2];
                int [] b = new int[2];
                set(p,a,r,g,b,img,x,y);


                r[0] = r[0]*r[1];
                g[0] = g[0]*g[1];
                b[0] = b[0]*b[1];

                p[0] = (a[0] << 24) | (r[0] << 16) | (g[0] << 8) | b[0];
                temp.setRGB(x, y, p[0]);
            }
        }
        try {
            ImageIO.write(img[0],"jpg",new File("D:\\Unsri\\ImageP\\result\\bakwan(multiplication).jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public static void set(int []p,int []a,int[]r,int[]g,int[]b, BufferedImage [] img, int x, int y){
        for (int i = 0; i < 2; i++) {
            p[i] = img[i].getRGB(x, y);
            a[i] = (p[i] >> 24) & 0xff;
            r[i] = (p[i] >> 16) & 0xff;
            g[i] = (p[i] >> 8) & 0xff;
            b[i] = p[i] & 0xff;
        }
    }

    public static void toBinaryImage(){
        BufferedImage img = null;
        File file = null;
        try {
            file = new File("D:\\Unsri\\ImageP\\doraemon.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int av = (int) ((r*0.299)+(g*0.587)+(b*0.144));

                if (av>=128) {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                }else
                    img.setRGB(x, y, 0);

            }
        }
        try {
            ImageIO.write(img,"jpg",new File("D:\\Unsri\\ImageP\\doraemon-2.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }


    public static void bitwiseAND(){

        BufferedImage [] img = new BufferedImage[2];
        File [] file = new File[2];
        try {
            file [0] = new File("D:\\Unsri\\ImageP\\doraemon-2.jpg");
            img [0]= ImageIO.read(file[0]);
            file [1] = new File("D:\\Unsri\\ImageP\\batman.jpg");
            img [1]= ImageIO.read(file[1]);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img[0].getWidth();
        int height= img[0].getHeight();


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int [] p = new int[2];
                int [] a = new int[2];
                int [] r = new int[2];
                int [] g = new int[2];
                int [] b = new int[2];
                set(p,a,r,g,b,img,x,y);


                int red = r[0] & r[1];
                int green = g[0] & g[1];
                int blue =b[0] & b[1];


                p[0] = (a[0] << 24) | (red << 16) | (green << 8) | blue;
                img[0].setRGB(x, y, p[0]);
            }
        }
        try {
            ImageIO.write(img[0],"jpg",new File("D:\\Unsri\\ImageP\\operasiAND.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public static void bitwiseOR(){

        BufferedImage [] img = new BufferedImage[2];
        File [] file = new File[2];
        try {
            file [0] = new File("D:\\Unsri\\ImageP\\doraemon-2.jpg");
            img [0]= ImageIO.read(file[0]);
            file [1] = new File("D:\\Unsri\\ImageP\\batman.jpg");
            img [1]= ImageIO.read(file[1]);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img[0].getWidth();
        int height= img[0].getHeight();


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int [] p = new int[2];
                int [] a = new int[2];
                int [] r = new int[2];
                int [] g = new int[2];
                int [] b = new int[2];
                set(p,a,r,g,b,img,x,y);


                int red = r[0] | r[1];
                int green = g[0] | g[1];
                int blue =b[0] | b[1];


                p[0] = (a[0] << 24) | (red << 16) | (green << 8) | blue;
                img[0].setRGB(x, y, p[0]);
            }
        }
        try {
            ImageIO.write(img[0],"jpg",new File("D:\\Unsri\\ImageP\\operasiOR.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void bitwiseXOR(){

        BufferedImage [] img = new BufferedImage[2];
        File [] file = new File[2];
        try {
            file [0] = new File("D:\\Unsri\\ImageP\\doraemon-2.jpg");
            img [0]= ImageIO.read(file[0]);
            file [1] = new File("D:\\Unsri\\ImageP\\batman.jpg");
            img [1]= ImageIO.read(file[1]);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img[0].getWidth();
        int height= img[0].getHeight();


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int [] p = new int[2];
                int [] a = new int[2];
                int [] r = new int[2];
                int [] g = new int[2];
                int [] b = new int[2];
                set(p,a,r,g,b,img,x,y);


                int red = r[0] ^ r[1];
                int green = g[0] ^ g[1];
                int blue =b[0] ^ b[1];


                p[0] = (a[0] << 24) | (red << 16) | (green << 8) | blue;
                img[0].setRGB(x, y, p[0]);
            }
        }
        try {
            ImageIO.write(img[0],"jpg",new File("D:\\Unsri\\ImageP\\operasiXOR.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }
    }


    public static void translation(){
        BufferedImage img = null;
        File file = null;

        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();
        int move = 160;

        BufferedImage result = new BufferedImage(width+move,height+move,BufferedImage.TYPE_INT_RGB);
        int i=0;
        int j=0;


        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                int p,a,r,g,b;
                if (x>=move && y>=move){
                    p = img.getRGB(x-move, y-move);
                    a = (p >> 24) & 0xff;
                    r = (p >> 16) & 0xff;
                    g = (p >> 8) & 0xff;
                    b = p & 0xff;
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    result.setRGB(x, y, p);
                }

            }
        }
        try {
            ImageIO.write(result,"jpg",new File("D:\\Unsri\\ImageP\\result\\bakwan-translation.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }

    }

    public static void rotation(){
        BufferedImage img = null;
        File file = null;
        BufferedImage result = new BufferedImage(670,670,BufferedImage.TYPE_INT_RGB);
        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();
        System.out.println(width+" "+height);
        int i;

        for (int x = 0; x < width; x++) {
            i = height-1;
            for (int y = 0; y < height; y++) {
                    int p,a,r,g,b;
                    p = img.getRGB(x, y);
                    a = (p >> 24) & 0xff;
                    r = (p >> 16) & 0xff;
                    g = (p >> 8) & 0xff;
                    b = p & 0xff;
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    result.setRGB(i, x, p);
                    i--;


            }
        }
        try {
            ImageIO.write(result,"jpg",new File("D:\\Unsri\\ImageP\\bakwan-rotation.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }

    }

    public static void flipHorizontal(){
        BufferedImage img = null;
        File file = null;
        BufferedImage result = new BufferedImage(670,670,BufferedImage.TYPE_INT_RGB);
        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();
        System.out.println(width+" "+height);
        int i;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p,a,r,g,b;
                p = img.getRGB(width-x-1, y);
                a = (p >> 24) & 0xff;
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = p & 0xff;
                p = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x, y, p);

            }
        }
        try {
            ImageIO.write(result,"jpg",new File("D:\\Unsri\\ImageP\\bakwan-flipHorizontal.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }

    }
    public static void zoom(){
        BufferedImage img = null;
        File file = null;
        BufferedImage result = new BufferedImage(670*2,670*2,BufferedImage.TYPE_INT_RGB);
        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        }catch (IOException e){
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();
        System.out.println(width+" "+height);
        int i,j;

        i = 0;
        j =0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p,a,r,g,b;
                p = img.getRGB(x, y);
                a = (p >> 24) & 0xff;
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = p & 0xff;
                p = (a << 24) | (r << 16) | (g << 8) | b;

                result.setRGB(i, j, p);
                result.setRGB(i,j+1, p);
                result.setRGB(i+1, j, p);
                result.setRGB(i+1, j+1, p);
                j=j+2;
            }
            i=i+2;
            j=0;
        }
        try {
            ImageIO.write(result,"jpg",new File("D:\\Unsri\\ImageP\\bakwan-zoom.jpg"));
        }catch (IOException e){
            System.out.println(e);
        }

    }
    public static void zoom2() {
        BufferedImage img = null;
        File file = null;

        try {
            file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println(e);
        }


        int width = img.getWidth()/2-30;
        int height = img.getHeight()/2+-30;
        System.out.println(width + " " + height);
        BufferedImage result = new BufferedImage((width*2), (height*2), BufferedImage.TYPE_INT_RGB);
        System.out.println(result.getWidth() + " " + result.getHeight());
        int i, j;

        i = 0;
        j = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p, a, r, g, b;
                p = img.getRGB(x,y);

                a = (p >> 24) & 0xff;
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = p & 0xff;
                p = (a << 24) | (r << 16) | (g << 8) | b;
//                System.out.println(x+" "+y);
                result.setRGB(i, j, p);
                result.setRGB(i, j + 1, p);
                result.setRGB(i + 1, j, p);
                result.setRGB(i + 1, j + 1, p);
                j = j + 2;
            }
            i = i + 2;
            j = 0;
        }
        try {
            ImageIO.write(result, "jpg", new File("D:\\Unsri\\ImageP\\bakwan-zoom2.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }



    public static void main(String[] args){
//        toNegative();
//        toGreyscale();
//        brightening();
        addition();
        substraction();
        multiplication();
//        toBinaryImage();
//        translation();
//        rotation();
//        flipHorizontal();
//        zoom();
//        zoom2();
//        bitwiseAND();
//        bitwiseOR();
//        bitwiseXOR();


    }
}