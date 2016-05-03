package com.dormitory.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

public class ExcelDownloadView extends AbstractJExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			WritableWorkbook book, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("data");

		String fileName = "excel.xls";

		response.setHeader("Content-Disposition", "attachment; filename=\""	+ java.net.URLEncoder.encode(fileName, "UTF-8") + "\";charset=\"UTF-8\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");

		makeExcelFile(book, list);
	}

	/* 엑셀 파일 생성 */
	private void makeExcelFile(WritableWorkbook book,
			List<Map<String, Object>> list) {
		// 시트 생성 (시트명, index)
		WritableSheet ws = book.createSheet("외박 신청 목록", 0);

		try {
			// 제목
			setExcelTitle(ws);

			// 내용
			setExcelContent(ws, list);

		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/* 제목 */
	private void setExcelTitle(WritableSheet ws) throws RowsExceededException,
			WriteException {
		ws.addCell(new Label(0, 0, "순번"));
		ws.addCell(new Label(1, 0, "신청자ID"));
		ws.addCell(new Label(2, 0, "시작일자"));
		ws.addCell(new Label(3, 0, "종료일자"));
		ws.addCell(new Label(4, 0, "신청일자"));
		ws.addCell(new Label(5, 0, "승인(반려)일자"));
		ws.addCell(new Label(6, 0, "신청상태"));
		ws.addCell(new Label(7, 0, "관리자 의견"));
	}

	/* 내용 */
	private void setExcelContent(WritableSheet ws,
			List<Map<String, Object>> list) throws RowsExceededException,
			WriteException {
		for (int i = 1; i < list.size()+1; i++) {
			ws.addCell(new Label(0, i, list.get(i-1).get("SEQ_NO") + ""));
			ws.addCell(new Label(1, i, list.get(i-1).get("ID") + ""));
			ws.addCell(new Label(2, i, list.get(i-1).get("START_DATE") + ""));
			ws.addCell(new Label(3, i, list.get(i-1).get("END_DATE") + ""));
			ws.addCell(new Label(4, i, list.get(i-1).get("REQ_DATE") + ""));
			ws.addCell(new Label(5, i, list.get(i-1).get("APP_DATE") + ""));
			ws.addCell(new Label(6, i, list.get(i-1).get("STATE") + ""));
			ws.addCell(new Label(7, i, list.get(i-1).get("EXP") + ""));
		}
	}
}
