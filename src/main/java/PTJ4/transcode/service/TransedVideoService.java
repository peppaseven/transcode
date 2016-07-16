package PTJ4.transcode.service;

import PTJ4.transcode.module.SrcVideo;
import PTJ4.transcode.module.TransedVideo;

public interface TransedVideoService {

	void addTransedVideo(TransedVideo tv , SrcVideo sv);
}
