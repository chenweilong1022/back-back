package com.ozygod.api.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-01-02
 */
@Slf4j
@RequestMapping("/api/files")
@RestController
@Api(value = "/api/files", description = "文件管理")
public class FileApiController implements Serializable {
    private static final long serialVersionUID = -6987791929197226491L;

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileApiController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${files_url}")
    private String filesUrl;

    // 图片原图
    private static final String PREFIX_IMAGE = "images";
    // 图片缩略图
    private static final String PREFIX_THUMB = "thumb";

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/images", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO uploadImage(@RequestParam("image") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix == null || !(".jpg".equals(suffix.toLowerCase()) || ".jpeg".equals(suffix.toLowerCase())
                || ".png".equals(suffix.toLowerCase()))) {
            return new ResponseBO(ResponseCode.P003.getCode(), "不合法的上传文件");
        }
        String newFileName = this.storeFile(file, PREFIX_IMAGE);
        ResponseBO responseBO = new ResponseBO();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("downloadUrl", "/api/files/download/" + PREFIX_IMAGE + "/" + newFileName);
        resultMap.put("showUrl", "/api/files/show/" + PREFIX_IMAGE + "/" + newFileName);
        resultMap.put("thumbUrl", "/api/files/show/" + PREFIX_THUMB + "/" + newFileName);
        responseBO.setData(resultMap);
        return responseBO;
    }

    /**
     * 下载图片
     * @param fileName
     * @param response
     * @return
     */
    @RequestMapping(value = "/download/images/{fileName:.+}", method = RequestMethod.GET)
    public ResponseBO downloadImage(@PathVariable String fileName, HttpServletResponse response) {
        File file = new File(filesUrl + PREFIX_IMAGE + "/" + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[fileInputStream.available()];
                int i = bufferedInputStream.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
                return new ResponseBO("下载成功");
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } finally {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                }
            }
        }

        return new ResponseBO("下载失败");
    }

    /**
     * 读取图片
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/show/images/{fileName:.+}", method = RequestMethod.GET)
    public void showImage(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        Resource resource = resourceLoader.getResource("file:" + filesUrl + PREFIX_IMAGE + "/" + fileName);

        this.showImage(resource, request, response);
    }

    /**
     * 读取缩略图
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/show/thumb/{fileName:.+}", method = RequestMethod.GET)
    public void showThumb(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        Resource resource = resourceLoader.getResource("file:" + filesUrl + PREFIX_THUMB + "/" + fileName);

        this.showImage(resource, request, response);
    }

    /**
     * 显示图片
     * @param resource
     * @param request
     * @param response
     */
    private void showImage(Resource resource, HttpServletRequest request, HttpServletResponse response) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            response.setContentType(contentType);
            StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 存储文件
     * @param file
     * @param prefix
     * @return
     */
    private String storeFile(MultipartFile file, String prefix) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = prefix + "_" + System.currentTimeMillis() +suffix;
        try {
            String storeFilePath = filesUrl + prefix + "/" + newFileName;
            File storeFile = new File(storeFilePath);
            // 判断目录是否存在，不存在则创建
            if (!storeFile.getParentFile().exists()) {
                boolean result = storeFile.getParentFile().mkdirs();
                if (!result) {
                    log.error("创建失败");
                }
            }
            file.transferTo(storeFile);

            // 如果是图片就压缩一个缩略图
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            if ("jpg".equals(extension) || "jpeg".equals(extension) || "png".equals(extension)) {
                File thumbFile = new File(filesUrl + PREFIX_THUMB);
                if (!thumbFile.exists()) {
                    if (!thumbFile.mkdirs()) {
                        log.error("创建失败");
                    }
                }
                Thumbnails.of(storeFilePath).size(80, 80).toFile(filesUrl + PREFIX_THUMB + "/" + newFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return newFileName;
    }
}