package notice;

import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticePageVO extends PageVO {

	//공지글 10건을 담을 목록 생성
	private List<NoticeVO> list;
	
	//작성후 service 클래스로 감.
}
