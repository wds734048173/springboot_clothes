package org.lanqiao.clothes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: WDS
 * @Date: 2018/12/28 19:19
 * @Description:
 */
@Controller
public class FileUploadController {
    //异步请求上传文件
    @RequestMapping("/manager/ajaxUpload.do")
    @ResponseBody
    public String ajaxUpload(MultipartFile[] photo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        for(MultipartFile ph : photo){
            if(!ph.isEmpty()){
                String fileName = ph.getOriginalFilename();
                System.out.println(fileName);
                String path = request.getSession().getServletContext().getRealPath("/imgs");

                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,fileName);
                try {
                    ph.transferTo(file);
                } catch (IOException e) {
                    System.out.println("文件["+ fileName +"]上传失败");
                    e.printStackTrace();
                    return "上传失败 ";
                }
            }
        }
        return "上传成功";
    }
}
