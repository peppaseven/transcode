package PTJ4.transcode.module;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 已经转码的视频
 * @author Vwings
 *
 */
@Entity
@Table(name = "trans_video")
public class TransedVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="tv_id")
	private Integer trsId;
	
	@Column(name ="tv_name",length = 1000)
	private String trsName;
	
	@Column(name ="tv_size")
	private Long trsSize;
	
	@Column(name = "tv_length")
	private Long trsLength;
	
	/**
	 * 转码时间
	 */
	@Column(name = "tv_duration")
	private Long trsDuration;
	
	@Column(name = "tv_width")
	private Integer trsWidth;
	
	@Column(name = "tv_height")
	private Integer trsHeight;
	
	@Column(name="tv_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsFinishTime;
	
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="tv_sv_id")
    private SrcVideo srcVideo;


	public Integer getTrsId() {
		return trsId;
	}


	public void setTrsId(Integer trsId) {
		this.trsId = trsId;
	}


	public String getTrsName() {
		return trsName;
	}


	public void setTrsName(String trsName) {
		this.trsName = trsName;
	}


	public Long getTrsSize() {
		return trsSize;
	}


	public void setTrsSize(Long trsSize) {
		this.trsSize = trsSize;
	}


	public Long getTrsLength() {
		return trsLength;
	}


	public void setTrsLength(Long trsLength) {
		this.trsLength = trsLength;
	}


	public Long getTrsDuration() {
		return trsDuration;
	}


	public void setTrsDuration(Long trsDuration) {
		this.trsDuration = trsDuration;
	}


	public SrcVideo getSrcVideo() {
		return srcVideo;
	}


	public void setSrcVideo(SrcVideo srcVideo) {
		this.srcVideo = srcVideo;
	}


	public Integer getTrsWidth() {
		return trsWidth;
	}


	public void setTrsWidth(Integer trsWidth) {
		this.trsWidth = trsWidth;
	}


	public Integer getTrsHeight() {
		return trsHeight;
	}


	public void setTrsHeight(Integer trsHeight) {
		this.trsHeight = trsHeight;
	}


	public Date getTrsFinishTime() {
		return trsFinishTime;
	}


	public void setTrsFinishTime(Date trsFinishTime) {
		this.trsFinishTime = trsFinishTime;
	}


	public TransedVideo(String trsName) {
		this.trsName = trsName;
	}


	public TransedVideo() {
		
	}
	
	
}
