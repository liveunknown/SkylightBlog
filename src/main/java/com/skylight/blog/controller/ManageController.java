package com.skylight.blog.controller;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.Category;
import com.skylight.blog.model.Label;
import com.skylight.blog.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
//@RequestMapping("/admin")
public class ManageController {

    @Autowired
    ManageService manageService;

    // Category
    @RequestMapping("/addCategory")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addCategory(String name) {
        return manageService.addCategory(name);
    }

    @RequestMapping("/deleteCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteCategory(Long id) {
        return manageService.deleteCategory(id);
    }

    @RequestMapping("/getCategory")
    @ResponseBody
    public Category getCategoryById(Long id){
        return manageService.getCategoryById(id);
    }

    @RequestMapping("/updateCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    /*@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GOD')")*/
    public boolean updateCategory(Category category){
        return manageService.updateCategory(category);
    }

    // Label
    @RequestMapping("/addLabel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addLabel(String name){
        return manageService.addLabel(name);
    }

    @RequestMapping("/deleteLabel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteLabel(Long id){
        return manageService.deleteLabel(id);
    }

    @RequestMapping("/getLabel")
    @ResponseBody
    public Label getLabelById(Long id){
        return manageService.getLabelById(id);
    }

    @RequestMapping("/updateLabel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean updateLabel(Label label){
        return manageService.updateLabel(label);
    }

    // Article
    @RequestMapping("/addArticle")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addArticle(String title,String summary,Long categoryId,String content,String labels,int isOriginal){

        JSONObject json = new JSONObject();
        List<Long> labelList = json.parseArray(labels,Long.class);
        Long[] ids = labelList.toArray(new Long[labelList.size()]);

        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(title);
        articleInfo.setSummary(summary);
        articleInfo.setCategoryId(categoryId);
        articleInfo.setViews(0);
        articleInfo.setIsOriginal(isOriginal);
        articleInfo.setIsDeleted(0);

        ArticleContent articleContent = new ArticleContent();
        articleContent.setContent(content);

        return manageService.addArticle(articleInfo,articleContent,ids);
    }

    @RequestMapping("/deleteArticle")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteArticle(Long id){
        return manageService.deleteArticle(id);
    }

    @RequestMapping("/updateArticle")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean updateArticle(Long articleId, String title, String summary, Long categoryId,int isOriginal,Long contentId, String content){
        ArticleInfo articleInfo = new ArticleInfo();
        ArticleContent articleContent = new ArticleContent();
        articleInfo.setId(articleId);
        articleInfo.setTitle(title);
        articleInfo.setSummary(summary);
        articleInfo.setCategoryId(categoryId);
        articleInfo.setIsOriginal(isOriginal);

        articleContent.setId(contentId);
        articleContent.setContent(content);
        return manageService.updateArticle(articleInfo, articleContent);
    }

    @RequestMapping("/addLabelByArticleId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addLabelByArticleId(Long articleId,Long labelId){
        return manageService.addLabelByArticleId(articleId, labelId);
    }

    @RequestMapping("/deleteArticleLabel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteArticleLabel(Long id){
        return manageService.deleteArticleLabel(id);
    }


    //UploadImage
    @RequestMapping(value="/uploadImage",method=RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Map<String,Object> imageUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            request.setCharacterEncoding("utf-8");
            //设置返回头后页面才能获取返回url
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            System.out.println("进入上传图片方法了: "+(file!=null));
            System.out.println("输出file: "+file);

            // 文件保存路径(服务器存放文件的地址)
            //     /usr/local/MyBlog/images
            //String Root = "C:\\Users\\Air\\Desktop\\images\\";
            String Root = "/usr/local/MyBlog/images/";

            String path = "";
            //String Url = "http://localhost:8080/image/";
            String Url = "http://www.baidurex.com/image/";

            System.out.println("Root: "+Root);
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            path=uuid+"."+imageName;
            //转存文件到服务器上
            System.out.println("path: "+path);
            file.transferTo(new File(Root+path));

            resultMap.put("success", 1);
            resultMap.put("message", "上传成功");
            //resultMap.put("url",Root+path);
            resultMap.put("url",Url+path);

        } catch (Exception e) {
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return resultMap;
    }
}
