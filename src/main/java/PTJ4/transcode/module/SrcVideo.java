package PTJ4.transcode.module;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Vwings
 * 上传的视频类
 * 上传的原视频
 *
 */
@Entity
@Table(name="src_video")
public class SrcVideo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="sv_id")
	private Integer srcVideoId;
	
	/**
	 * 上传视频名(用户的命名)
	 */
	@Column(name = "sv_name",length = 1000)
	private String srcName;

	/**
	 * 上传视频的类型
	 */
	@Column(name = "sv_type")
	private String srcType;
	
	/**
	 * 视频大小
	 * 单位 : byte
	 */
	@Column(name = "sv_size")
	private Long srcSize;
	
	/**
	 * 视频长度
	 * 单位： 秒
	 */
	@Column(name = "sv_length")
	private Long srcLength;
	
	@Column(name = "sv_width")
	private Integer srcWidth;
	
	@Column(name = "sv_height")
	private Integer srcHeight;

	@Column(name="sv_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date srcPostTime;
	
	
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="sv_u_id")
    private User user;
	
	
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
    ,fetch = FetchType.LAZY ,mappedBy = "srcVideo")
	private Set<TransedVideo> transedVideos = new HashSet<TransedVideo>();

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public Long getSrcSize() {
		return srcSize;
	}

	public void setSrcSize(Long srcSize) {
		this.srcSize = srcSize;
	}

	public Long getSrcLength() {
		return srcLength;
	}

	public void setSrcLength(Long srcLength) {
		this.srcLength = srcLength;
	}

	public Set<TransedVideo> getTransedVideos() {
		return transedVideos;
	}

	public void setTransedVideos(Set<TransedVideo> transedVideos) {
		this.transedVideos = transedVideos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public Date getSrcPostTime() {
		return srcPostTime;
	}

	public void setSrcPostTime(Date srcPostTime) {
		this.srcPostTime = srcPostTime;
	}

	public Integer getSrcVideoId() {
		return srcVideoId;
	}

	public void setSrcVideoId(Integer srcVideoId) {
		this.srcVideoId = srcVideoId;
	}

	public Integer getSrcWidth() {
		return srcWidth;
	}

	public void setSrcWidth(Integer srcWidth) {
		this.srcWidth = srcWidth;
	}

	public Integer getSrcHeight() {
		return srcHeight;
	}

	public void setSrcHeight(Integer srcHeight) {
		this.srcHeight = srcHeight;
	}

	
	public SrcVideo() {
		
	}

	public SrcVideo(String srcName, String srcType, Long srcSize) {
		
		this.srcName = srcName;
		this.srcType = srcType;
		this.srcSize = srcSize;
	}
	
}
