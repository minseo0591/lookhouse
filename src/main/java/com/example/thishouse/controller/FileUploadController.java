package com.example.thishouse.controller;

import com.example.thishouse.domain.house.House_picture;
import com.example.thishouse.service.RealEstateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
public class FileUploadController {
    // 이미지 업로드 경로
    private final String uploadPath = "src/main/resources/static/upload/";

    private final RealEstateService realEstateService;

    public FileUploadController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    // 파일 업로드 페이지 뷰
    @GetMapping("/upload")
    public String handleFileUploadView() {
        return "test_kong/uploadFile";
    }

    // 파일 업로드 처리
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("files") List<MultipartFile> files , HttpServletRequest request) {
//        String uploadPath = request.getServletContext().getRealPath("/upload/");
        List<String> filePaths = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 업로드된 파일 정보 저장
                    String originalFilename = file.getOriginalFilename();
                    String saveFilename = generateSaveName(originalFilename);
                    String filePath = uploadPath + saveFilename;

                    // 파일 경로 수정 시작
                    Path serverPath = Paths.get(uploadPath);
                    if (!Files.exists(serverPath)) {
                        Files.createDirectories(serverPath);
                    }

                    Files.write(Paths.get(filePath), file.getBytes());
                    // 파일 경로 수정 끝

                    filePaths.add(filePath);
                    System.out.println("파일 경로: " + filePath);
                    System.out.println("오리지널 파일 이름: " + originalFilename);
                    System.out.println("저장 파일 이름: " + saveFilename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 업로드된 파일 정보를 DB에 저장
        for (int i = 0; i < filePaths.size(); i++) {
            String filePath = filePaths.get(i);
            String originalFilename = files.get(i).getOriginalFilename();

            // 파일 이름만 추출하여 저장
            String saveFilename = extractFileNameFromPath(filePath);

            House_picture house_picture = new House_picture();
            house_picture.setFilePath(filePath);
            house_picture.setOriginal_name(originalFilename);
            house_picture.setSave_name(saveFilename);

            realEstateService.insert_picture(house_picture);

            System.out.println("파일 경로를 활용한 처리: " + filePath);
        }

        return "redirect:/images";
    }

    private String generateSaveName(String original_name) {
        String uuid = UUID.randomUUID().toString();
        String extension = original_name.substring(original_name.lastIndexOf("."));
        String saveName = uuid + extension;

        // 파일명 중복 방지를 위해 파일명에 UUID 추가
        String filePath = uploadPath + saveName;
        while (Files.exists(Paths.get(filePath))) {
            uuid = UUID.randomUUID().toString();
            saveName = uuid + extension;
            filePath = uploadPath + saveName;
        }

        return saveName;
    }
    // 저장 파일명 생성
    private String extractFileNameFromPath(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }
    // 업로드된 이미지 목록 페이지 뷰
    @GetMapping("/images")
    public String viewImages(Model model) {
        List<House_picture> housePictures = realEstateService.getHousePictures();
        model.addAttribute("housePictures", housePictures);
//        model.addAttribute("uploadPath", uploadPath);
//
//        // 파일명만 변수에 할당
//        String save_name = extractFileNameFromPath(uploadPath);
//        model.addAttribute("save_name", save_name);

        return "test_kong/imageGallery";
    }

    // 이미지 파일 전달
//    @GetMapping("/images/{filePath}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filePath) throws FileNotFoundException {
//        Resource file = realEstateService.loadFileAsResource(filePath);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }


}
