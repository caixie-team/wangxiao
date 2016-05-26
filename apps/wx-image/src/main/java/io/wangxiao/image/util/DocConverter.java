package io.wangxiao.image.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class DocConverter {
    private static final int environment = 2;// 环境 1：windows 2:linux
    private static Logger logger = Logger.getLogger(DocConverter.class);
    /**
     * doc转为PDF
     */
   /* private void doc2pdf() throws Exception {
        if (docFile.exists()) {
            if (!pdfFile.exists()) {
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
                try {
                    connection.connect();
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                    converter.convert(docFile, pdfFile);
                    // close the connection
                    connection.disconnect();
                    System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");
                } catch (java.net.ConnectException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，openoffice服务未启动！****");
                    throw e;
                } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，读取转换文件失败****");
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            } else {
                System.out.println("****已经转换为pdf，不需要再进行转化****");
            }
        } else {
            System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
        }
    }*/

    /**
     * pdf转换成 swf
     */
    @SuppressWarnings("unused")
    public static String pdf2swf(String pdfName, String swfName) throws Exception {
        File pdfFile = new File(pdfName);
        File swfFile = new File(swfName);
        Runtime r = Runtime.getRuntime();
        if (!swfFile.exists()) {
            if (pdfFile.exists()) {
                if (environment == 1) {// windows环境处理测试
                    try {
                        Process p = r.exec("C:\\Program Files (x86)\\SWFTools\\pdf2swf.exe " + pdfName + " -o " + swfName + " -T 9");
                        logger.info("swf转换成功，文件输出：" + swfName);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "convererror";
                    }
                } else if (environment == 2) {// linux环境处理
                    try {
                        Process p = r.exec("/opt/swftools/bin/pdf2swf " + pdfName + " -o " + swfName + " -T 9");
                        logger.info("swf转换成功，文件输出：" + swfName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "convererror";
                    }
                }
            } else {
                return "pdfnotexists";//pdf文件不存在
            }
        } else {
            return "swfexists";//swf已经存在不需要转换
        }
        return "success";//转换成功
    }


    public static void main(String[] args) {
        try {
            String pfd = "a.pdf";
            String swf = "a.swf";
            pdf2swf(pfd, swf);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
