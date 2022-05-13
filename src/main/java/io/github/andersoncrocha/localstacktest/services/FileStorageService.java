package io.github.andersoncrocha.localstacktest.services;

import io.github.andersoncrocha.localstacktest.dtos.FileDTO;

public interface FileStorageService {

    String uploadFile(FileDTO fileDTO);

    byte[] getFileByName(String storedFilename);

}
