package com.example.maven.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    //保存图片的文件夹路径
    private static final String ROOT = "taskImage";

    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    /**
     * 向后端发送文件
     * @param file
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadTaskImage")
    public String uploadTaskImage(
            @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request){
        if(!file.isEmpty()) {
            try{
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
                redirectAttributes.addFlashAttribute("message", "successfully uploaded" + file.getOriginalFilename());
            }catch (IOException | RuntimeException e){
                redirectAttributes.addFlashAttribute("message", "Fail to upload" + file.getOriginalFilename() + "=>" + e.getMessage());
            }
        }else {
            redirectAttributes.addFlashAttribute("message", "Fail to upload" + file.getOriginalFilename() + "=>" + "empty file");
        }

        //return "redirect:/";
        return "testImage.html";
    }

    /**
     * 从后端获得图片
     * @param filename 图片名
     * @return 图片（字节数组）
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getTaskImage/{filename:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getTaskImage(@PathVariable String filename) {
        try{
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
