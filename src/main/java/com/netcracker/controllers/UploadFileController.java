package com.netcracker.controllers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.netcracker.model.Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
    @GetMapping("/upload-file")
    public String findUserForm(Model model) {
        model.addAttribute("file", new Uploader());
        return "upload-file";
    }
    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "";
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                logger.info("uploaded: " + uploadedFile.getAbsolutePath());
                List<String> as = new ArrayList<>();
                Files.lines(Paths.get(uploadedFile.getAbsolutePath()), StandardCharsets.UTF_8).forEach(as::add);
                Pattern pattern = Pattern.compile("firstName:([^;]*); middleName:([^;]*); lastName:([^;]*); age:([^;]*); salary:([^;]*); job:([^;]*); email:([^;]*)");
                try (FileWriter writer = new FileWriter("users_data.txt", true)) {
                    for(String i:as){
                        Matcher matcher = pattern.matcher(i);
                        if(matcher.matches()) {
                            writer.write(i);
                            writer.append('\n');
                        }
                    }
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
                return "upload-file-result";

            } catch (Exception e) {
                return "upload-file-failed";
            }
        } else {
            return "upload-file-failed";
        }
    }

}
