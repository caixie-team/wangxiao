/**
 * 
 */
package com.atdld.open.image.util;

/*
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
*/

/**
 * pdf工具类
 */
public class PDFUtils {
/*
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance("project");
    
    private static final String rootpath = propertyUtil.getProperty("file.root");
    
    *//**
     * office转pdf
     * 
     * @param sourceFile
     *            转换的源文件
     * @return String 转换后的路径
     *//*
    public static String office2PDF(String sourceFile) {
        try {
            sourceFile=rootpath+sourceFile;
            sourceFile=sourceFile.replace(File.separator+File.separator, File.separator);
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return "fileNotFound";// 找不到源文件
            }
            // 获取扩展名
            String extName = "";
            if (sourceFile.lastIndexOf(".") >= 0) {
                extName = sourceFile.substring(sourceFile.lastIndexOf("."));
            }
            // 如果传入的是pdf则直接返回
            if (extName.equalsIgnoreCase(".pdf")) {
                return sourceFile.replace(rootpath, "/");
            }
            // 文件路径
            String filePath = "";
            if (sourceFile.lastIndexOf(".") >= 0) {
                filePath = sourceFile.substring(0, sourceFile.lastIndexOf("."));
            }
            // 要转的pdf路径
            String destFile = filePath + ".pdf";

            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
           *//* // connect to an OpenOffice.org instance running on port 8100
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();
            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
            // close the connection
            connection.disconnect();*//*
            
            String[] cmdA = { "/bin/sh", "-c", "doc2pdf.sh "+inputFile, outputFile+" " +outputFile};  
            Process process = Runtime.getRuntime().exec(cmdA);  
            process.waitFor();  
            
            
            return destFile.replace(rootpath, "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    *//**
     * pdf转图片集
     * 
     * @param sourceFile
     *            转换的源文件
     * @throws Exception
     * 
     * @return String 转换后的路径Json格式
     *//*
    public static List<String> PDF2Imgs(String sourceFile) throws Exception {
        List<String> fileNameList = new ArrayList<String>();
        try {
            sourceFile=rootpath+sourceFile;
            sourceFile=sourceFile.replace(File.separator+File.separator, File.separator);
            // pdf路径
            String pdfPath = office2PDF(sourceFile.replace(rootpath, "/"));
            if(pdfPath==null) return null;
            // 文件路径
            String destFile = "";
            if (sourceFile.lastIndexOf(".") >= 0) {
                destFile = sourceFile.substring(0, sourceFile.lastIndexOf("."));
            }
            // 要转的图片集路径
            File f1 = new File(destFile);
            if (!f1.exists()) {
                f1.mkdirs();
            }
            PDDocument doc = PDDocument.load((rootpath+pdfPath).replace(File.separator+File.separator, File.separator));
            int pageCount = doc.getPageCount();
            List pages = doc.getDocumentCatalog().getAllPages();
            for (int i = 0; i < pages.size(); i++) {
                PDPage page = (PDPage) pages.get(i);
                BufferedImage image = page.convertToImage();
                String filename=destFile+File.separator + i + ".jpg";
                ImageIO.write(image, "jpg", new File(filename));
                fileNameList.add(filename.replace(rootpath, "/"));
            }
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Gson gson = new Gson();gson.toJson(fileNameList)
        return fileNameList ; 
    }
    */

  /*  <!-- office转图片 -->
    <!--<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox-app</artifactId>
    <version>1.8.6</version>
    </dependency>-->*/

   
}
