package com.spaceZ.spaceZBE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class ImageFAOimpl implements ImageFAO {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ServletContext context;
	
	@Override
	public String getImageName(MultipartFile multipartFile) {
		//파일명을 난수화하기 위해 random으로 돌린다.
		String imgname = UUID.randomUUID().toString();
		
		//파일이 비어있지 않을 때, 파일 저장 후 파일명을 return 한다.
		if (multipartFile.getSize() > 0) {
			logger.info("{}", multipartFile.getOriginalFilename());
			imgname += multipartFile.getOriginalFilename();

			String dir_path = context.getRealPath("resources/upload");
			logger.info("{}", dir_path);

			File saveFile = new File(dir_path, imgname);

			String format_name = imgname.substring(imgname.lastIndexOf(".") + 1);
			logger.info("formatName:{}", format_name);

			try {
				multipartFile.transferTo(saveFile); // 원본이미지 저장

				//// create thumbnail image/////////
				BufferedImage original_buffer_img = ImageIO.read(saveFile);
				BufferedImage thumb_buffer_img = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D graphic = thumb_buffer_img.createGraphics();
				graphic.drawImage(original_buffer_img, 0, 0, 100, 100, null);

				File thumbNail_File = new File(dir_path, "thumb_" + imgname);
				ImageIO.write(thumb_buffer_img, format_name, thumbNail_File); // 썸네일이미지 저장

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { //파일이 비어있을 때는 기본이미지로 저장한다.
			if (imgname.equals("")) {
				imgname = "img_0001.png"; //기본이미지
			}
		} // 파일이 넘어왔을때만 처리하는 분기.

		return imgname;
	}

}
