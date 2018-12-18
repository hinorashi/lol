package com.framgia.hino.controller;

import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.EXPIRES;
import static org.springframework.http.HttpHeaders.PRAGMA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	/**
	 * test a simple upload
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {

		File convertFile = new File(file.getOriginalFilename());
		convertFile.createNewFile();

		FileOutputStream fos = new FileOutputStream(convertFile);
		fos.write(file.getBytes());
		fos.close();

		return new ResponseEntity<String>(file.getOriginalFilename() + " uploaded!", HttpStatus.CREATED);
	}

	/**
	 * test a simple download
	 *
	 * @return
	 * @throws FileNotFoundException
	 */
	@GetMapping("/download")
	public ResponseEntity<Object> download() throws FileNotFoundException {

		File file = new File("Nguyen Trung Quan B.jpg");
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add(CACHE_CONTROL, "no-cache, no-store, must-revalidate");
		headers.add(PRAGMA, "no-cache");
		headers.add(EXPIRES, "0");

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(isr);

	}
}
