package ImageProcessing.tes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Main {
    public static void main(String[] args) throws IOException{
        File file = new File("D:\\Unsri\\ImageP\\bakwan.jpg");
        BufferedImage image = ImageIO.read(file);
        Kernel kernel = new Kernel(3, 3,
                new float[] {
                        1f/9f, 1f/9f, 1f/9f,
                        1f/9f, 1f/9f, 1f/9f,
                        1f/9f, 1f/9f, 1f/9f});
        BufferedImageOp op = new ConvolveOp(kernel);
        BufferedImage bufferedImage = null;
        bufferedImage = op.filter(image, null);

        File output = new File("D:\\Unsri\\ImageP\\output-2.jpg");
        ImageIO.write(bufferedImage, "jpg", output);
    }
}