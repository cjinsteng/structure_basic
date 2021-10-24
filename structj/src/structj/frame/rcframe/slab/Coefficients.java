package structj.frame.rcframe.slab;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Coefficients {
	// 엑셀에 있는 모멘트 계수 불러오기
	public static void momentcofficientsAll(int a) { // a : 시트번호

		try {
			FileInputStream file = new FileInputStream(
					"C:/java/workspace/structj/src/structj/frame/rcframe/slab/momentCoefficients.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			int rowindex = 0;
			int columnindex = 0;
			// 시트갯수
			XSSFSheet sheet = workbook.getSheetAt(a); // 읽을 시트
			// 행의 수
			int rows = sheet.getPhysicalNumberOfRows();
			for (rowindex = 0; rowindex < rows; rowindex++) {
				// 행을 읽는다.
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					// 셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						// 셀값을 읽기
						XSSFCell cell = row.getCell(columnindex);
						String value = "";

						if (cell == null) { // 셀에 값이 없을경우에도 계속진행
							continue;
						} else {
							// 타입별로 내용읽기
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = cell.getBooleanCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							}
						}
						System.out.println(columnindex);
						System.out.println(rowindex);
						System.out.println(value);

					}
				}
			}
			System.out.println("OK");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("NG");
		}

	}

	// 엑셀에 있는 모멘트 계수 불러오기
	public static void momentcofficients(int a, int rowdex, int coldex) { // a : 시트번호

		try {
			FileInputStream file = new FileInputStream(
					"C:/java/workspace/structj/src/structj/frame/rcframe/slab/momentCoefficients.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			int rowindex = rowdex;
			int columnindex = coldex;
			// 시트갯수
			XSSFSheet sheet = workbook.getSheetAt(a); // 읽을 시트
			// 행의 수
			XSSFRow row = sheet.getRow(rowindex);
			XSSFCell cell = row.getCell(columnindex);
			String value = "";
			// 타입별로 내용읽기
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				value = cell.getNumericCellValue() + "";
				break;
			case XSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue() + "";
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				value = cell.getBooleanCellValue() + "";
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				value = cell.getErrorCellValue() + "";
				break;
			}
			System.out.println(columnindex);
			System.out.println(rowindex);
			System.out.println(value);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("NG");
		}
	}
}