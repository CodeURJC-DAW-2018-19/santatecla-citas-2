package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.exception.FileStorageException;
import com.urjc.daw.practica.exception.MyFileNotFoundException;
import com.urjc.daw.practica.model.QuoteImage;
import com.urjc.daw.practica.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class QuoteImageStorageService {

	@Autowired
	private ImageRepository imageRepository;

	public QuoteImage storeImage(MultipartFile file) throws FileStorageException, IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if (fileName.contains("..")) {

			throw new FileStorageException("invalid path sequence");
		}

		QuoteImage quoteImage = new QuoteImage(fileName, file.getBytes());
		return imageRepository.save(quoteImage);

	}

	public QuoteImage getFile(Long fileId) throws MyFileNotFoundException {
		return imageRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File  not found with id" + fileId));

	}

    public QuoteImage save(QuoteImage storage) {
        return imageRepository.save(storage);
    }

	public void removeImage(Long imageId) {

		imageRepository.delete(imageRepository.findById(imageId).get());
	}
}
