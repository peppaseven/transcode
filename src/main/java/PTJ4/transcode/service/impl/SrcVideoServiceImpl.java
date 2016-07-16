package PTJ4.transcode.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PTJ4.transcode.dao.SrcVideoDao;
import PTJ4.transcode.module.SrcVideo;
import PTJ4.transcode.module.User;
import PTJ4.transcode.service.SrcVideoService;
import PTJ4.transcode.service.UserService;

@Service
public class SrcVideoServiceImpl implements SrcVideoService{

	@Autowired
	private SrcVideoDao svd;
	
	@Autowired
	private UserService us;
	
	
	@Transactional
	public void addVideo(SrcVideo sv, String username) {
		User uploaduser = us.getByName(username);
		sv.setUser(uploaduser);
		sv.setSrcPostTime(new Date());
		
		svd.save(sv);
	}

}
