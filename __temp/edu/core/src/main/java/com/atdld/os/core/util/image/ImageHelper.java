package com.atdld.os.core.util.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @ClassName com.atdld.os.core.util.image.ImageHelper
 * @description
 * @author :
 * @Create Date : 2014-2-7 下午9:05:49
 */
public class ImageHelper {
    public static int defalutdropWidth = 150;
    public static int defalutdropHeight = 150;

    private static BufferedImage makeThumbnail(Image img, int width, int height) {
        BufferedImage tag = new BufferedImage(width, height, 1);
        Graphics g = tag.getGraphics();
        g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);
        g.dispose();
        return tag;
    }

    private static void saveSubImage(BufferedImage image, Rectangle subImageBounds,
            File subImageFile, int left, int top, int dropWidth, int dropHeight)
            throws IOException {
        if (dropWidth == 0 || dropHeight == 0) {
            dropWidth = defalutdropWidth;
            dropHeight = defalutdropHeight;
        }
        String fileName = subImageFile.getName();
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        BufferedImage subImage = new BufferedImage(dropWidth, dropHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = subImage.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, dropWidth, dropHeight);
        g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
                subImageBounds.width, subImageBounds.height), left, top, null);
        g.dispose();
        File file = new File(subImageFile.getPath());
        if (!file.exists()) {
            file.mkdirs();
        }

        ImageIO.write(subImage, formatName, subImageFile);
    }

    public static void cut(File srcImageFile, File descDir, Rectangle rect, int[] intParms)
            throws IOException {
        Image image = ImageIO.read(srcImageFile);
        BufferedImage bImage = makeThumbnail(image, intParms[0], intParms[1]);

        /*
         * new int[] { imageWidth, imageHeight, cutLeft, cutTop, dropWidth,
         * dropHeight }
         */

        /*
         * String fileName = descDir.getName();
         * System.out.println("fileName:"+fileName); String formatName =
         * fileName.substring(fileName.lastIndexOf(".") + 1);
         * System.out.println("formatName:"+fileName); Iterator<ImageReader> it
         * = ImageIO.getImageReadersByFormatName(formatName); ImageReader reader
         * = it.next(); // 获取图片流 ImageInputStream iis
         * =ImageIO.createImageInputStream(new FileInputStream(srcImageFile));
         * reader.setInput(iis, true); ImageReadParam param =
         * reader.getDefaultReadParam();
         * 
         * rect=new Rectangle(intParms[2], intParms[3], intParms[4],
         * intParms[5]); param.setSourceRegion(rect);
         * 
         * BufferedImage bi = reader.read(0, param); ImageIO.write(bi,
         * formatName, descDir);
         */

        saveSubImage(bImage, rect, descDir, intParms[2] < 0 ? -intParms[2] : 0,
                intParms[3] < 0 ? -intParms[3] : 0, intParms[4], intParms[5]);
    }

}