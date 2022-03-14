package com.yohan.imageupload;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
public class FileUploadController {

    // folder from project which the images are uploaded to
    private static final String UPLOADED_FOLDER = "\\public\\";

    /**
     * Gets an image by searching for file name
     *
     * @param fileName name of file
     * @return returns html response with PNG image
     * @throws IOException if the image does not exist
     */
    @GetMapping(
            value = "/images/{fileName}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] getImage(@PathVariable String fileName) throws IOException {
        final InputStream in = new FileInputStream(System.getProperty("user.dir") + UPLOADED_FOLDER + fileName);
        return IOUtils.toByteArray(in);
    }

    /**
     * Uploads an image by file
     *
     * @param file file to upload
     * @return the file name if successes.
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Date timeNow = new Date();
            String fileName = "image-" +  timeNow.getTime() + ".png";
            Path path = Paths.get(System.getProperty("user.dir") + UPLOADED_FOLDER + fileName);
            Path parentDir = Paths.get(System.getProperty("user.dir") + UPLOADED_FOLDER);
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(path, bytes);
            System.out.println("here");
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "should not reach here";
    }
}
