package com.atdld.os.app.entity.app;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AppUpdate  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String kType;//android, ios
	private String downloadUrl;//下载链接
	private String versionNo;//版本号
	private String depict;//更新说明

}
