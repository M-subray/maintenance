package zerobase.maintenance.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zerobase.maintenance.exception.FileCopyException;
import zerobase.maintenance.type.ErrorCode;

@Service
public class StorageService {

  @Value("${spring.file.upload-dir}")
  private String uploadDir;

  public String store(MultipartFile file) {
    if (file.getSize() > 10 * 1024 * 1024) {
      throw new FileCopyException(ErrorCode.FILE_MAX_SIZE_EXCEED);
    }

    Path directory = Paths.get(uploadDir);
    String fileName = generateFileName(Objects.requireNonNull(file.getOriginalFilename()));
    Path filePath = directory.resolve(fileName);

    try {
      Files.copy(file.getInputStream(), filePath);
    } catch (IOException e) {
      throw new FileCopyException(ErrorCode.FILE_COPY_FAILED);
    }

    return filePath.toString();
  }

  private String generateFileName(String originalFileName) {
    String uuid =
        UUID.randomUUID().toString();
    String extension =
        originalFileName.substring(originalFileName.lastIndexOf("."));

    return uuid + extension;
  }
}
