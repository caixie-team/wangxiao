package io.wangxiao.image.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class ChangeImage {

    /**
     * Definition the default zipimage quality is 0.75.//定义默认zipimage质量是0.75 。
     */
    private float quality = 0.75f;

    /**
     * Use the Area Averaging image scaling algorithm.//使用面积平均的图像缩放算法。
     */
    private int hints = 16;

    /**
     * 构造函数
     *
     * @throws FileNotFoundException
     */
    public ChangeImage() {
    }

    /**
     * @param imagename String
     * @return BufferedImage
     * @throws FileNotFoundException
     */
    public BufferedImage readImage(String imagename) throws FileNotFoundException,
            Exception {
        File file = null;
        BufferedImage bufferedImage = null;
        try {
            file = new File(imagename);
            bufferedImage = ImageIO.read(file); // create bufferedImage object
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * @param url
     * @return BufferedImage
     * @throws FileNotFoundException
     */
    public BufferedImage readImage(URL url) throws FileNotFoundException, Exception {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(url); // create bufferedImage object
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * Calculation(计算) height / width
     *
     * @param image BufferedImage
     * @return
     */
    private double getImageHeight(BufferedImage image) {
        double height = (double) image.getHeight();
        double width = (double) image.getWidth();
        double hw = height / width;
        return hw;
    }

    /**
     * 改变文件大小的具体业务
     *
     * @param readpath      String
     * @param savepath      String
     * @param readImageName String
     * @param saveImageName String
     * @param imageWidth    int
     * @throws Exception
     */
    public void changeSize(String readpath, String savepath, String readImageName,
                           String saveImageName, String extName, int imageWidth) throws Exception {
        // String methodName = "changeSize";
        /** 文件对象输出流 */
        FileOutputStream outputStream = null;
        /** 建立BufferedImage对象。指定图片的长度宽度和色彩。 */
        BufferedImage outputImage = null;
        /** 制图法,制图学,图表算法,图形 */
        Graphics2D biContext = null;
        /** 类编码图像 */
        JPEGImageEncoder encoder = null;

        /** 建立 该对象的一个输入流图片对象、这时代表 创建一个要压缩图片的位置对象(要处理的图片) */
        BufferedImage inputImage = this.readImage(readpath + readImageName);

        imageWidth = getImageWidth(inputImage, imageWidth);
        try {
            /** 创建了一个 文件对象输出流对象、即将压缩图片保存的位置、 */
            // outputStream = new FileOutputStream(savepath + saveImageName +
            // readImageName.substring(readImageName.lastIndexOf(".")));
            outputStream = new FileOutputStream(savepath + saveImageName + "." + extName);
            /**
             * 建构bufferedimage的一个预定义的图像类型。 预定义了一个图片格式、主要是 宽、高、类型(颜色组件与系统相关)
             * */
            outputImage = new BufferedImage(imageWidth,
                    (int) (getImageHeight(inputImage) * (double) imageWidth),
                    BufferedImage.TYPE_INT_BGR);
            /** 由以上outputImage 预定义图、创建一个真正图(画出一个图、绘制) */
            biContext = outputImage.createGraphics();
            /**
             * 创建一个图 创建一个 预定义的图像
             * */
            biContext
                    .drawImage(this.zipImage(inputImage, imageWidth,
                            (int) (getImageHeight(inputImage) * (double) imageWidth)), 0,
                            0, null);
            // Additional drawing code, such as
            // watermarks or logos can be placed here.
            // com.sun.image.codec.jpeg package
            // is included in Sun and IBM sdk 1.3.
            encoder = JPEGCodec.createJPEGEncoder(outputStream);

            // The default QUALITY is 0.75.
            /**
             * getDefaultJPEGEncodeParam 这将创建一个实例一jpegimageencoder可以用来
             * 编码图像数据时如同JPEG文件数据流。
             */
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outputImage);
            jep.setQuality(this.quality, true);
            encoder.encode(outputImage, jep);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 改变文件大小的具体业务
     *
     * @param readSavepath
     * @param imageWidth
     * @throws Exception
     */
    public void changeSize(String readSavepath, int imageWidth) throws Exception {
        // String methodName = "changeSize";
        /** 文件对象输出流 */
        FileOutputStream outputStream = null;
        /** 建立BufferedImage对象。指定图片的长度宽度和色彩。 */
        BufferedImage outputImage = null;
        /** 制图法,制图学,图表算法,图形 */
        Graphics2D biContext = null;
        /** 类编码图像 */
        JPEGImageEncoder encoder = null;

        /** 建立 该对象的一个输入流图片对象、这时代表 创建一个要压缩图片的位置对象(要处理的图片) */
        BufferedImage inputImage = this.readImage(readSavepath);

        imageWidth = getImageWidth(inputImage, imageWidth);
        try {
            /** 创建了一个 文件对象输出流对象、即将压缩图片保存的位置、 */
            // outputStream = new FileOutputStream(savepath + saveImageName +
            // readImageName.substring(readImageName.lastIndexOf(".")));
            outputStream = new FileOutputStream(readSavepath);
            /**
             * 建构bufferedimage的一个预定义的图像类型。 预定义了一个图片格式、主要是 宽、高、类型(颜色组件与系统相关)
             * */
            outputImage = new BufferedImage(imageWidth,
                    (int) (getImageHeight(inputImage) * (double) imageWidth),
                    BufferedImage.TYPE_INT_BGR);
            /** 由以上outputImage 预定义图、创建一个真正图(画出一个图、绘制) */
            biContext = outputImage.createGraphics();
            /**
             * 创建一个图 创建一个 预定义的图像
             * */
            biContext
                    .drawImage(this.zipImage(inputImage, imageWidth,
                            (int) (getImageHeight(inputImage) * (double) imageWidth)), 0,
                            0, null);
            // Additional drawing code, such as
            // watermarks or logos can be placed here.
            // com.sun.image.codec.jpeg package
            // is included in Sun and IBM sdk 1.3.
            encoder = JPEGCodec.createJPEGEncoder(outputStream);

            // The default QUALITY is 0.75.
            /**
             * getDefaultJPEGEncodeParam 这将创建一个实例一jpegimageencoder可以用来
             * 编码图像数据时如同JPEG文件数据流。
             */
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outputImage);
            jep.setQuality(this.quality, true);
            encoder.encode(outputImage, jep);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 改变文件大小的具体业务
     *
     * @param readpath      String
     * @param savepath      String
     * @param readImageName String
     * @param saveImageName String
     * @param imageWidth    int
     * @throws Exception
     */
    public void changeSize(String readpath, String savepath, String readImageName,
                           String saveImageName, String extName, int imageWidth, int imageHeight)
            throws Exception {
        // String methodName = "changeSize";
        /** 文件对象输出流 */
        FileOutputStream outputStream = null;
        /** 建立BufferedImage对象。指定图片的长度宽度和色彩。 */
        BufferedImage outputImage = null;
        /** 制图法,制图学,图表算法,图形 */
        Graphics2D biContext = null;
        /** 类编码图像 */
        JPEGImageEncoder encoder = null;

        /** 建立 该对象的一个输入流图片对象、这时代表 创建一个要压缩图片的位置对象(要处理的图片) */
        BufferedImage inputImage = this.readImage(readpath + readImageName);

        imageWidth = getImageWidth(inputImage, imageWidth);
        try {
            /** 创建了一个 文件对象输出流对象、即将压缩图片保存的位置、 */
            // outputStream = new FileOutputStream(savepath + saveImageName +
            // readImageName.substring(readImageName.lastIndexOf(".")));
            outputStream = new FileOutputStream(savepath + saveImageName + "." + extName);
            /**
             * 建构bufferedimage的一个预定义的图像类型。 预定义了一个图片格式、主要是 宽、高、类型(颜色组件与系统相关)
             * */
            outputImage = new BufferedImage(imageWidth, imageHeight,
                    BufferedImage.TYPE_INT_BGR);
            /** 由以上outputImage 预定义图、创建一个真正图(画出一个图、绘制) */
            biContext = outputImage.createGraphics();
            /**
             * 创建一个图 创建一个 预定义的图像
             * */
            biContext.drawImage(this.zipImage(inputImage, imageWidth, imageHeight), 0, 0,
                    null);
            // Additional drawing code, such as
            // watermarks or logos can be placed here.
            // com.sun.image.codec.jpeg package
            // is included in Sun and IBM sdk 1.3.
            encoder = JPEGCodec.createJPEGEncoder(outputStream);

            // The default QUALITY is 0.75.
            /**
             * getDefaultJPEGEncodeParam 这将创建一个实例一jpegimageencoder可以用来
             * 编码图像数据时如同JPEG文件数据流。
             */
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outputImage);
            // jep.setQuality(this.quality, true);
            encoder.encode(outputImage, jep);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 改变文件大小的具体业务
     *
     * @param readpathURL
     * @param savepath      String 保存物理地址
     * @param saveImageName String 保存文件名称(不包括"."与扩展名)
     * @param extName       String 扩展名称
     * @param imageWidth    int 压缩结果宽度
     * @throws Exception
     */
    public void changeSize(URL readpathURL, String savepath, String saveImageName,
                           String extName, int imageWidth) throws Exception {
        // String methodName = "changeSize";
        /** 文件对象输出流 */
        FileOutputStream outputStream = null;
        /** 建立BufferedImage对象。指定图片的长度宽度和色彩。 */
        BufferedImage outputImage = null;
        /** 制图法,制图学,图表算法,图形 */
        Graphics2D biContext = null;
        /** 类编码图像 */
        JPEGImageEncoder encoder = null;

        /** 建立 该对象的一个输入流图片对象、这时代表 创建一个要压缩图片的位置对象(要处理的图片) */
        BufferedImage inputImage = this.readImage(readpathURL);

        imageWidth = getImageWidth(inputImage, imageWidth);
        try {
            /** 创建了一个 文件对象输出流对象、即将压缩图片保存的位置、 */
            // outputStream = new FileOutputStream(savepath + saveImageName +
            // readImageName.substring(readImageName.lastIndexOf(".")));
            outputStream = new FileOutputStream(savepath + saveImageName + "." + extName);
            /**
             * 建构bufferedimage的一个预定义的图像类型。 预定义了一个图片格式、主要是 宽、高、类型(颜色组件与系统相关)
             * */
            outputImage = new BufferedImage(imageWidth,
                    (int) (getImageHeight(inputImage) * (double) imageWidth),
                    BufferedImage.TYPE_INT_BGR);
            /** 由以上outputImage 预定义图、创建一个真正图(画出一个图、绘制) */
            biContext = outputImage.createGraphics();
            /**
             * 创建一个图 创建一个 预定义的图像
             * */
            biContext
                    .drawImage(this.zipImage(inputImage, imageWidth,
                            (int) (getImageHeight(inputImage) * (double) imageWidth)), 0,
                            0, null);
            // Additional drawing code, such as
            // watermarks or logos can be placed here.
            // com.sun.image.codec.jpeg package
            // is included in Sun and IBM sdk 1.3.
            encoder = JPEGCodec.createJPEGEncoder(outputStream);

            // The default QUALITY is 0.75.
            /**
             * getDefaultJPEGEncodeParam 这将创建一个实例一jpegimageencoder可以用来
             * 编码图像数据时如同JPEG文件数据流。
             */
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outputImage);
            jep.setQuality(this.quality, true);
            encoder.encode(outputImage, jep);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 改变文件大小的具体业务
     *
     * @param readpathURL 读取路径
     * @param savepath    String 保存物理地址
     * @param imageWidth  int 压缩结果宽度
     * @throws Exception 异常
     */
    public void changeSize(URL readpathURL, String savepath, int imageWidth)
            throws Exception {
        // String methodName = "changeSize";
        /** 文件对象输出流 */
        FileOutputStream outputStream = null;
        /** 建立BufferedImage对象。指定图片的长度宽度和色彩。 */
        BufferedImage outputImage = null;
        /** 制图法,制图学,图表算法,图形 */
        Graphics2D biContext = null;
        /** 类编码图像 */
        JPEGImageEncoder encoder = null;

        /** 建立 该对象的一个输入流图片对象、这时代表 创建一个要压缩图片的位置对象(要处理的图片) */
        BufferedImage inputImage = this.readImage(readpathURL);

        imageWidth = getImageWidth(inputImage, imageWidth);
        try {
            /** 创建了一个 文件对象输出流对象、即将压缩图片保存的位置、 */
            // outputStream = new FileOutputStream(savepath + saveImageName +
            // readImageName.substring(readImageName.lastIndexOf(".")));
            outputStream = new FileOutputStream(savepath);
            /**
             * 建构bufferedimage的一个预定义的图像类型。 预定义了一个图片格式、主要是 宽、高、类型(颜色组件与系统相关)
             * */
            outputImage = new BufferedImage(imageWidth,
                    (int) (getImageHeight(inputImage) * (double) imageWidth),
                    BufferedImage.TYPE_INT_BGR);
            /** 由以上outputImage 预定义图、创建一个真正图(画出一个图、绘制) */
            biContext = outputImage.createGraphics();
            /**
             * 创建一个图 创建一个 预定义的图像
             * */
            biContext
                    .drawImage(this.zipImage(inputImage, imageWidth,
                            (int) (getImageHeight(inputImage) * (double) imageWidth)), 0,
                            0, null);
            // Additional drawing code, such as
            // watermarks or logos can be placed here.
            // com.sun.image.codec.jpeg package
            // is included in Sun and IBM sdk 1.3.
            encoder = JPEGCodec.createJPEGEncoder(outputStream);

            // The default QUALITY is 0.75.
            /**
             * getDefaultJPEGEncodeParam 这将创建一个实例一jpegimageencoder可以用来
             * 编码图像数据时如同JPEG文件数据流。
             */
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outputImage);
            jep.setQuality(this.quality, true);
            encoder.encode(outputImage, jep);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * 改变文件大小的具体业务
     *
     * @param readpathURL   读取路径
     * @param savepath      保存路径
     * @param saveImageName 存储名
     * @param extName       扩展名
     * @param imageWidth    图片宽
     * @param imageHeight   图片高
     * @throws Exception 异常
     */
    public void changeSize(URL readpathURL, String savepath, String saveImageName,
                           String extName, int imageWidth, int imageHeight) throws Exception {
        // String methodName = "changeSize";
        /** 文件对象输出流 */
        FileOutputStream outputStream = null;
        /** 建立BufferedImage对象。指定图片的长度宽度和色彩。 */
        BufferedImage outputImage = null;
        /** 制图法,制图学,图表算法,图形 */
        Graphics2D biContext = null;
        /** 类编码图像 */
        JPEGImageEncoder encoder = null;

        /** 建立 该对象的一个输入流图片对象、这时代表 创建一个要压缩图片的位置对象(要处理的图片) */
        BufferedImage inputImage = this.readImage(readpathURL);

        imageWidth = getImageWidth(inputImage, imageWidth);
        try {
            /** 创建了一个 文件对象输出流对象、即将压缩图片保存的位置、 */
            // outputStream = new FileOutputStream(savepath + saveImageName +
            // readImageName.substring(readImageName.lastIndexOf(".")));
            outputStream = new FileOutputStream(savepath + saveImageName + "." + extName);
            /**
             * 建构bufferedimage的一个预定义的图像类型。 预定义了一个图片格式、主要是 宽、高、类型(颜色组件与系统相关)
             * */
            outputImage = new BufferedImage(imageWidth, imageHeight,
                    BufferedImage.TYPE_INT_BGR);
            // outputImage = new BufferedImage(imageWidth,(int)
            // (getImageHeight(inputImage) * (double)
            // imageWidth),BufferedImage.TYPE_INT_BGR);
            /** 由以上outputImage 预定义图、创建一个真正图(画出一个图、绘制) */
            biContext = outputImage.createGraphics();
            /**
             * 创建一个图 创建一个 预定义的图像
             * */
            biContext.drawImage(this.zipImage(inputImage, imageWidth, imageHeight), 0, 0,
                    null);
            // biContext.drawImage(this.zipImage(inputImage, imageWidth,(int)
            // (getImageHeight(inputImage) * (double) imageWidth)),0, 0, null);
            // Additional drawing code, such as
            // watermarks or logos can be placed here.
            // com.sun.image.codec.jpeg package
            // is included in Sun and IBM sdk 1.3.
            encoder = JPEGCodec.createJPEGEncoder(outputStream);

            // The default QUALITY is 0.75.
            /**
             * getDefaultJPEGEncodeParam 这将创建一个实例一jpegimageencoder可以用来
             * 编码图像数据时如同JPEG文件数据流。
             */
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outputImage);
            jep.setQuality(this.quality, true);
            encoder.encode(outputImage, jep);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * @param bufferedImage BufferedImage
     * @param width         int
     * @param height        int
     * @return Image
     * @throws FileNotFoundException
     */
    public Image zipImage(BufferedImage bufferedImage, int width, int height)
            throws FileNotFoundException {
        Image outputImage = null;
        // SCALE_AREA_AVERAGING //SCALE_FAST
        try {
            // hints flags to indicate the type of algorithm to use * for image
            // resampling.
            /**
             * 创建一个规模版本的这个形象。一种新的图像对象是归侨，这会使图像在指定的宽度和高度的预设值。新形象，
             * 对象可能被载入异步即使原始来源的形象，已经载入完全。
             * 如果任何一方的宽度或高度是一种消极的数目，然后是一个值取代，以维持长宽比原始图像尺寸
             * 。如果双方的宽度和高度是否定的，那么原始图像尺寸用。
             */
            outputImage = bufferedImage.getScaledInstance(width, height, hints);
        } catch (Exception ignored) {
        }
        return outputImage;
    }

    /**
     * 获取具体的图片宽度、即是：要压缩的文件宽度
     *
     * @param image         图片对象
     * @param standardWidth 宽度标准值
     * @return
     */
    private static int getImageWidth(BufferedImage image, int standardWidth) {
        /** width 的默认值为：standardWidth、这样不会有差错的、因为如果image 对象不为空则 width 会重新 替换新值 */
        int width = standardWidth;
        try {
            if (image != null) {
                width = image.getWidth();
            }
            /** 如果实际文件的宽度大于standardWidth则重新复制 */
            if (width > standardWidth) {
                width = standardWidth;
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return width;
    }

    /**
     * 压缩图片文件
     *
     * @param sourcePath      保存源文件的位置目录
     * @param savePath        要将源文件 压缩后保存的位置目录
     * @param sourceImageName 源文件名
     * @param saveImageName   要保存的文件名
     * @param imageWidth      宽度
     * @throws FileNotFoundException
     */
    public static void change(String sourcePath, String savePath, String sourceImageName,
                              String saveImageName, String extName, int imageWidth)
            throws FileNotFoundException {
        ChangeImage c = new ChangeImage();
        try {
            c.changeSize(sourcePath, savePath, sourceImageName, saveImageName, extName,
                    imageWidth);
        } catch (Exception ignored) {
        }
    }

    /**
     * 压缩图片文件
     *
     * @param sourcePath      保存源文件的位置目录
     * @param savePath        要将源文件 压缩后保存的位置目录
     * @param sourceImageName 源文件名
     * @param saveImageName   要保存的文件名
     * @param extName         扩展名
     * @param imageWidth      宽度
     * @param imageHeight     高度
     * @throws FileNotFoundException 文件异常
     */
    public static void change(String sourcePath, String savePath, String sourceImageName,
                              String saveImageName, String extName, int imageWidth, int imageHeight)
            throws FileNotFoundException {
        ChangeImage c = new ChangeImage();
        try {
            c.changeSize(sourcePath, savePath, sourceImageName, saveImageName, extName,
                    imageWidth);
        } catch (Exception ignored) {
        }
    }

}
