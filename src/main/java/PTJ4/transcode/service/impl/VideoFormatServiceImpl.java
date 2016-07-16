package PTJ4.transcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PTJ4.transcode.dao.VideoFormatDao;
import PTJ4.transcode.module.VideoFormat;
import PTJ4.transcode.service.VideoFormatService;

@Service
public class VideoFormatServiceImpl implements VideoFormatService{

	@Autowired
	private VideoFormatDao videoFormatDao;
	
	@Override
	public List<VideoFormat> getAll() {
		
		return videoFormatDao.getAll("VideoFormat");
	}

}
