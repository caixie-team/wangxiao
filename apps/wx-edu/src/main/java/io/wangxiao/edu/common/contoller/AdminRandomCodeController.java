package io.wangxiao.edu.common.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description 验证码生成工具类
 */
@Controller
public class AdminRandomCodeController {

    public static final String RAND_CODE = "COMMON_RAND_CODE";

    /**
     * @param randomCodeName
     * @throws IOException
     */
    @RequestMapping("/ran/adminrandom")
    public void genericRandomCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("image/png");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        CreateImageCode vCode = new CreateImageCode(85, 30, 4, 3);
        session.setAttribute(RAND_CODE, vCode.getCode());
        vCode.write(response.getOutputStream());

        /*int width = 85, height = 28;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 获取图形上下文
        Graphics2D g = image.createGraphics();//(getGraphics();
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(new Color(249, 247, 243));//getRandColor(214, 243, 255
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Microsoft YaHei", Font.ROMAN_BASELINE, 20));
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(44, 85, 110));// 字体颜色
            g.drawString(rand, 13 * i + 16, 23);
        }
        // 将认证码存入SESSION
        session.setAttribute(RAND_CODE, sRand);
        // 图象生效
        g.dispose();
        ServletOutputStream responseOutputStream = response.getOutputStream(); // 输出图象到页面
        ImageIO.write(image, "png", responseOutputStream);
        // 以下关闭输入流！ responseOutputStream.flush();
        responseOutputStream.close();*/
    }

}
