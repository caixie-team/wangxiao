package io.wangxiao.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrxOrderDTO  extends Trxorder implements  Serializable{
	private static final long serialVersionUID = 1L;
	private List<TrxorderDetailDTO> trxorderDetailList;
}
