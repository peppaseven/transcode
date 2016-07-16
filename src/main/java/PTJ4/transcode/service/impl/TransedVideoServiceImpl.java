package PTJ4.transcode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PTJ4.transcode.dao.TransedVideoDao;
import PTJ4.transcode.module.SrcVideo;
import PTJ4.transcode.module.TransedVideo;
import PTJ4.transcode.service.TransedVideoService;

@Service
public class TransedVideoServiceImpl implements TransedVideoService{

	@Autowired
	private TransedVideoDao tvd;
	
	@Transactional
	public void addTransedVideo(TransedVideo tv, SrcVideo sv) {
		tv.setSrcVideo(sv);
		tvd.save(tv);
	}

}
