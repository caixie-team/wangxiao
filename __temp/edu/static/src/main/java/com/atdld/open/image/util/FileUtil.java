package com.atdld.open.image.util;

import com.google.gson.JsonObject;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.PropertyUtil;
import com.atdld.os.core.util.image.ImageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @ClassName com.supergenius.sns.util.image.FileUtil
 * @description kindeditoe文件上传类
 * @author :
 * @Create Date : 2013-12-13 下午2:49:02
 */
public class FileUtil {
    public static int CUS_PHOTO_WIDTH = 150;
    public static int CUS_PHOTO_HEIGHT = 150;
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance("project");

    // 统一传到pathfix下
    public static final String pathfix = propertyUtil.getProperty("file.pathfix");//

    public static final String rootpath = propertyUtil.getProperty("file.root");

    public static final String tempPath = "temp";// 临时目录

    /**
     * 
     * 
     * @param imgFile
     *            文件
     * @param paths
     *            路径 0物理 1 URL
     * @returned JsonObject
     */
    public static JsonObject saveImage(MultipartFile imgFile, String[] paths) {

        JsonObject obj = new JsonObject();
        try {
            // 存储物理路径
            String savePath = paths[0];
            // 返回的url
            String urlPath = paths[1];
            String newFileName = "";// 新的文件名
            String upFileName = imgFile.getOriginalFilename();
            // 保存第一张图片
            if (StringUtils.isNotEmpty(upFileName)) {
                if (!checkFileName(upFileName)) {
                    obj.addProperty("error", -1);
                    obj.addProperty("message", "上传文件扩展名不允许。\n只允许 gif,jpg,jpeg,png,bmp格式。");
                    return obj;
                }
                // 文件大小不能超过5M
                if (imgFile.getSize() > 5242880) {
                    obj.addProperty("error", -1);
                    obj.addProperty("message", "上传文件大小不能超过5M");
                    return obj;
                }
                // 新文件名
                newFileName = getRandomFileNameString(upFileName);
                // 锁结束
                File isD = new File(savePath);
                // 校验如果目录不存在，则创建目录
                if (!isD.isDirectory()) {
                    isD.mkdirs();
                }
                if (!isD.exists()) {
                    synchronized (FileUtil.class) {
                        isD.mkdirs();
                    }
                }
                String saveFilename = savePath + File.separator + newFileName;
                // 保存文件
                imgFile.transferTo(new File(saveFilename));
                obj.addProperty("error", 0);
                urlPath = urlPath + "/" + newFileName;
                obj.addProperty("url", urlPath);
            } else {
                obj.addProperty("error", -1);
                obj.addProperty("message", "文件名为空");
            }

            return obj;

        } catch (Exception e) {
            logger.error("+++upload kindeditor images error", e);
            obj.addProperty("error", -1);
            obj.addProperty("message", "上传异常，请稍后再试");
            return obj;
        }

    }

    /**
     * 获得随机的数字为文件名，有效防止文件名重读
     * 
     * @param fileName
     *            传来的文件名
     * @return String 返回新的文件名
     */
    public static String getRandomFileNameString(String fileName) {

        StringBuffer buffer = new StringBuffer();
        // 加锁为防止文件名重复
        final Lock lock = new ReentrantLock();
        lock.lock();
        try {
            buffer.append(System.currentTimeMillis());// 当前时间
            // 增加6位随机的数字
            buffer.append(getRandomString(6));
            // 添加后缀名
            buffer.append(fileName.substring(fileName.lastIndexOf("."), fileName.length()));
        } finally {
            lock.unlock();
        }
        return buffer.toString();
    };

    /**
     * 获取指定长度的随机数字
     * 
     * @param len
     * @return
     */
    public static String getRandomString(int len) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            buffer.append(random.nextInt(10));
            random = new Random();
        }
        return buffer.toString();
    }

    /**
     * 
     * 检查是否是符合的后缀名
     * 
     * @param fileName
     * @return
     */
    public static boolean checkFileName(String fileName) {
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        /*
         * 现在只有图片 extMap.put("flash", "swf,flv"); extMap.put("media",
         * "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
         * extMap.put("file",
         * "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
         */
        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
            return false;
        }
        return true;
    }

    /**
     * 获得存储的物理路径
     * 
     * @param request
     * @return
     */
    public static String[] getSavePathByRequest(HttpServletRequest request) {

        String base = request.getParameter("base");// 区分项目的变量 如：恒企、仁和等区分
        String param = request.getParameter("param");// 区分模块的变量 如：考试、商品、课程等区分
        String dateStr = DateUtils.toString(new Date(), "yyyyMMdd");
        String ukey = request.getParameter("ukey");// 用户唯一key
        StringBuffer savePath = new StringBuffer();
        StringBuffer urlPath = new StringBuffer();
        savePath.append(rootpath).append(pathfix);
        urlPath.append( "/").append(pathfix);
        if (StringUtils.isEmpty(base)) {
            base = "yizhilu";// 临时，未传次参数的项目统一到yizhilu目录下
        }
        savePath.append("/"  ).append(base);
        urlPath.append("/"  ).append(base);
        if (StringUtils.isEmpty(param)) {
            param = "common";// 临时，未传的项目统一到common目录下
        }
        savePath.append("/"  ).append(param);
        urlPath.append("/"  ).append(param);
        
        if (StringUtils.isNotEmpty(ukey)) {
            savePath.append("/"  ).append(ukey);
            urlPath.append("/"  ).append(ukey);
        }
        
        savePath.append("/"  ).append(dateStr);
        urlPath.append("/"  ).append(dateStr);
        
        String[] result = new String[] { savePath.toString(), urlPath.toString() };
        return result;
    }

    /**
     * 获得存储的临时物理路径放到tempxia
     * 
     * @param request
     * @return
     */
    public static String[] getTempSavePathByRequest(HttpServletRequest request) {

        String base = request.getParameter("base");// 区分项目的变量 如：恒企、仁和等区分
        String param = request.getParameter("param");// 区分模块的变量 如：考试、商品、课程等区分
        String dateStr = DateUtils.toString(new Date(), "yyyyMMdd");

        if (StringUtils.isEmpty(base)) {
            base = "yizhilu";// 临时，未传次参数的项目统一到yizhilu目录下
        }
        if (StringUtils.isEmpty(param)) {
            param = "common";// 临时，未传的项目统一到common目录下
        }
        // 拼凑 存储物理路径
        String savePath = rootpath + pathfix + "/" + tempPath + "/" + base + "/" + param + "/" + dateStr;
        // 拼凑 url
        String urlPath = "/" + pathfix + "/" + tempPath + "/" + base + "/" + param + "/" + dateStr;

        String[] result = new String[] { savePath, urlPath };
        return result;

    }

    /**
     * 
     * @param photoPath
     *            图片路径
     * @param imageWidth
     *            图片宽地
     * @param imageHeight
     *            图片高度
     * @param cutLeft
     *            左 坐标
     * @param cutTop
     *            顶 坐标
     * @return
     */
    public static JsonObject saveCutImage(String photoPath, int imageWidth, int imageHeight, int cutLeft, int cutTop, int dropWidth, int dropHeight) {
        JsonObject obj = new JsonObject();
       
        Rectangle rec = createPhotoCutRec(imageWidth, imageHeight, cutLeft, cutTop, dropWidth, dropHeight);
        
        File tempPic = new File(rootpath+photoPath);
        // 新文件路径
        String newSavepath=photoPath.substring(0, photoPath.lastIndexOf("/")).replace(tempPath, "customer");
        File isD = new File(newSavepath);
        // 校验如果目录不存在，则创建目录
        if (!isD.isDirectory()) {
            isD.mkdirs();
        }
        if (!isD.exists()) {
            synchronized (FileUtil.class) {
                isD.mkdirs();
            }
        }
        // 新文件名
        String newPhotoName =newSavepath+"/"+getRandomFileNameString(photoPath);
        File file = new File(rootpath+newPhotoName);
        try {

            saveSubImage(tempPic, file, rec, new int[] { imageWidth, imageHeight, cutLeft, cutTop, dropWidth, dropHeight });
            // 返回的地址
            obj.addProperty("url", newPhotoName.replace(rootpath, "/"));
            obj.addProperty("error", 0);
        } catch (IOException e) {
            e.printStackTrace();
            obj.addProperty("error", -1);
            obj.addProperty("message", "上传文件大小不能超过5M");
        }

        return obj;
    };

    private static Rectangle createPhotoCutRec(int imageWidth, int imageHeight, int cutLeft, int cutTop, int dropWidth, int dropHeight) {
        int recX = cutLeft > 0 ? cutLeft : 0;
        int recY = cutTop > 0 ? cutTop : 0;
        int recWidth = dropWidth;
        int recHieght = dropHeight;
        if (cutLeft < 0) {
            // 注意curLeft 是负数
            if (imageWidth - cutLeft > dropWidth) {
                recWidth = dropWidth + cutLeft;
            } else {
                recWidth = imageWidth;
            }
        } else {
            if (imageWidth - cutLeft < dropWidth) {
                recWidth = imageWidth - cutLeft;
            }
        }

        if (cutTop < 0) {
            // 注意curLeft 是负数
            if (imageHeight - cutTop > dropHeight) {
                recHieght = dropHeight + cutTop;
            } else {
                recHieght = imageHeight;
            }
        } else {

            if (imageHeight - cutTop < dropHeight) {
                recHieght = imageHeight - cutTop;
            }
        }
        return new Rectangle(recX, recY, recWidth, recHieght);
    }

    private static void saveSubImage(File srcImageFile, File descDir, Rectangle rect, int[] intParms) throws IOException {
        ImageHelper.cut(srcImageFile, descDir, rect, intParms);
    }

    /**
     * 读取文件内容
     * 
     * @param path
     * @return
     */
    public static String getFileContent(String path) {
        File myFile = new File(path);
        if (!myFile.exists()) {
            System.err.println("Can't Find " + path);
        }
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(myFile), "UTF-8");
            in = new BufferedReader(read);
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 文件写入
     * 
     * @param content
     * @param path
     * @return
     */
    public static File writeFileContent(String content, String path) {
        File file = new File(path);
        FileOutputStream fileout = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileout = new FileOutputStream(file);
            fileout.write(content.getBytes("utf-8"));
            fileout.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileout != null) {
                try {
                    fileout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 删除图片，限定格式gif,jpg,jpeg,png,bmp
     * 
     * @param filePath
     * @return
     */
    public static boolean deleteImageFile(String filePath) {
        String realpath = rootpath + filePath;// 真实物理路径
        realpath.replaceAll("//", "/");// 防止双符号
        File file = new File(realpath);
        // 只删除图片。防止误删除
        if (checkFileName(filePath)) {
            if (file.exists()) {
                file.delete();
            }
            return true;
        } else {
            return false;
        }

    };

    /**
     * 存储网络图片到服务器
     * 
     * @param urlString
     * @return
     */
    public static  JsonObject saveFile(String urlString, String savepath) {
        JsonObject obj = new JsonObject();
        // 锁结束
        File isD = new File(savepath);
        // 校验如果目录不存在，则创建目录
        if (!isD.isDirectory()) {
            isD.mkdirs();
        }
        if (!isD.exists()) {
            synchronized (FileUtil.class) {
                isD.mkdirs();
            }
        }
        // 新文件名
        String newFileName = getRandomFileNameString("a.jpg");
        String saveFilename = savepath + "/" + newFileName;
        try {
          //new一个URL对象  
            URL url = new URL(urlString);  
            //打开链接  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            //设置请求方式为"GET"  
            conn.setRequestMethod("GET");  
            //超时响应时间为5秒  
            conn.setConnectTimeout(5 * 1000);  
            //通过输入流获取图片数据  
            InputStream inStream = conn.getInputStream();  
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
            byte[] data = readInputStream(inStream);  
            //new一个文件对象用来保存图片，默认保存当前工程根目录  
            File imageFile = new File(saveFilename);  
            //创建输出流  
            FileOutputStream outStream = new FileOutputStream(imageFile);  
            //写入数据  
            outStream.write(data);  
            //关闭输出流  
            outStream.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj.addProperty("url", saveFilename.replace(rootpath, "/"));
        return obj;

    }
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    
    
    /**
     *  上传普通文件,不限制文件名后缀
     * 
     * @param file
     *            文件
     * @param paths
     *            路径 0物理 1 URL
     * @returned JsonObject
     */
    public static JsonObject saveCommonFile(MultipartFile file, String[] paths) {

        JsonObject obj = new JsonObject();
        try {
            // 存储物理路径
            String savePath = paths[0];
            // 返回的url
            String urlPath = paths[1];
            String newFileName = "";// 新的文件名
            String upFileName = file.getOriginalFilename();
            if (StringUtils.isNotEmpty(upFileName)) {
                // 新文件名
                newFileName = getRandomFileNameString(upFileName);
                // 锁结束
                File isD = new File(savePath);
                // 校验如果目录不存在，则创建目录
                if (!isD.isDirectory()) {
                    isD.mkdirs();
                }
                if (!isD.exists()) {
                    synchronized (FileUtil.class) {
                        isD.mkdirs();
                    }
                }
                String saveFilename = savePath + File.separator + newFileName;
                // 保存文件
                file.transferTo(new File(saveFilename));
                obj.addProperty("error", 0);
                urlPath = urlPath + "/" + newFileName;
                obj.addProperty("url", urlPath);
            } else {
                obj.addProperty("error", -1);
                obj.addProperty("message", "文件名为空");
            }

            return obj;

        } catch (Exception e) {
            logger.error("+++upload kindeditor images error", e);
            obj.addProperty("error", -1);
            obj.addProperty("message", "上传异常，请稍后再试");
            return obj;
        }

    }
    
}
