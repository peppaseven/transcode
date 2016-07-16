package PTJ4.transcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import PTJ4.transcode.module.VideoFormat;
import PTJ4.transcode.service.VideoFormatService;

@Controller
public class VideoFormatController {
	
	@Autowired
	private VideoFormatService vs;
	
	@RequestMapping("/api/getformats")
	@ResponseBody
	private List<VideoFormat> getVideoFormats()
	{
		return vs.getAll();
	}
}
