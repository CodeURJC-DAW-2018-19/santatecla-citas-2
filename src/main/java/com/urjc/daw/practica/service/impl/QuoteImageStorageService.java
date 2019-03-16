package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.exception.FileStorageException;
import com.urjc.daw.practica.exception.MyFileNotFoundException;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.QuoteImage;
import com.urjc.daw.practica.repository.ImageRepository;
import com.urjc.daw.practica.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class QuoteImageStorageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private QuoteRepository quotes;

	public QuoteImage storeImage(MultipartFile file) throws FileStorageException, IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if (fileName.contains("..")) {

			throw new FileStorageException("invalid path sequence");
		}

		QuoteImage quoteImage = new QuoteImage(fileName, file.getBytes());
		return imageRepository.save(quoteImage);

	}

	public QuoteImage getFile(Long quoteId) throws MyFileNotFoundException {
		Optional<Quote> optional = quotes.findById(quoteId);
		if(optional.isPresent()) {
			if(optional.get().getImageId()!= null) {
				Optional<QuoteImage> imgOpt = imageRepository.findById(optional.get().getImageId());
				if (imgOpt.isPresent()) {
					return imgOpt.get();
				}
			}
		}
		throw new MyFileNotFoundException("Image not found");

	}

    public QuoteImage save(QuoteImage storage) {
        return imageRepository.save(storage);
    }

	public void removeImage(Long imageId) {

		imageRepository.delete(imageRepository.findById(imageId).get());
	}
}
