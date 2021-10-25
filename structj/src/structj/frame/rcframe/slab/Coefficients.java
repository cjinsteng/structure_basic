package structj.frame.rcframe.slab;

import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Coefficients {
	// 엑셀에 있는 모멘트 계수 불러오기
	public static void momentcoefficientsAll(int a) { // a : 시트번호

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
	public static double momentcoefficients(int a, int coldex, int rowdex) { // a : 시트번호

		double coeff;

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
			double value1 = 0;
			boolean value2;
			byte value3;

			// 타입별로 내용읽기
			switch (cell.getCellType()) { // 우선은 숫자만 가져옴....엑셀이 숫자 문자 불린 너무 다양해서 작업필요
			case XSSFCell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				value1 = cell.getNumericCellValue();
				break;
			case XSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				value2 = cell.getBooleanCellValue();
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				value3 = cell.getErrorCellValue();
				break;
			}
			System.out.println(value1);
			System.out.println("ok");
			coeff = value1;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("NG");
			coeff = 0;
		}

		return coeff;

	}

	public static void moment(Double xlength, Double ylength, Double deadload, Double liveload, int slabcase,
			int slabtype) {

		System.out.println(momentcoefficients(0, 1, 1));

		double lenRatio = ylength / xlength;
		int a = 3; // 시트갯수 3개(0,1,2) 3시트는 전단
		double[] dbs = new double[3];
		double[] dbslong = new double[3];
		double gap = 0.05;
		double x1;
		double x2;
		double y1;
		double y2;

		if (slabcase == 1) {
			// 1-way슬래브 모멘트 출력
			System.out.println("아직");

		} else if (slabcase == 2) {
			// 2-way슬래브 모멘트 출력

			switch (slabtype) {
			case 1:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;
			case 2:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;
			case 3:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;
			case 4:

				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;

			case 5:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;

			case 6:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;

			case 7:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;
			case 8:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;
			case 9:
				for (int i = 0; i < a; i++) {
					if (lenRatio >= 0.95) {
						x1 = momentcoefficients(i, slabtype, 1);
						x2 = momentcoefficients(i, slabtype, 3);
						y1 = momentcoefficients(i, slabtype, 2);
						y2 = momentcoefficients(i, slabtype, 4);

						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (1.0 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (1.0 * (y2 - y1) / gap));

						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;


					} else if (lenRatio >= 0.9) {
						x1 = momentcoefficients(i, slabtype, 3);
						x2 = momentcoefficients(i, slabtype, 5);
						y1 = momentcoefficients(i, slabtype, 4);
						y2 = momentcoefficients(i, slabtype, 6);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.95 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.95 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.85) {
						x1 = momentcoefficients(i, slabtype, 5);
						x2 = momentcoefficients(i, slabtype, 7);
						y1 = momentcoefficients(i, slabtype, 6);
						y2 = momentcoefficients(i, slabtype, 8);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.9 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.9 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.8) {
						x1 = momentcoefficients(i, slabtype, 7);
						x2 = momentcoefficients(i, slabtype, 9);
						y1 = momentcoefficients(i, slabtype, 8);
						y2 = momentcoefficients(i, slabtype, 10);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.85 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.85 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.75) {
						x1 = momentcoefficients(i, slabtype, 9);
						x2 = momentcoefficients(i, slabtype, 11);
						y1 = momentcoefficients(i, slabtype, 10);
						y2 = momentcoefficients(i, slabtype, 12);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.8 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.8 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.7) {
						x1 = momentcoefficients(i, slabtype, 11);
						x2 = momentcoefficients(i, slabtype, 13);
						y1 = momentcoefficients(i, slabtype, 12);
						y2 = momentcoefficients(i, slabtype, 14);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.75 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.75 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.65) {
						x1 = momentcoefficients(i, slabtype, 13);
						x2 = momentcoefficients(i, slabtype, 15);
						y1 = momentcoefficients(i, slabtype, 14);
						y2 = momentcoefficients(i, slabtype, 16);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.7 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.7 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.6) {
						x1 = momentcoefficients(i, slabtype, 15);
						x2 = momentcoefficients(i, slabtype, 17);
						y1 = momentcoefficients(i, slabtype, 16);
						y2 = momentcoefficients(i, slabtype, 18);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.65 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.65 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
					} else if (lenRatio >= 0.55) {
						x1 = momentcoefficients(i, slabtype, 17);
						x2 = momentcoefficients(i, slabtype, 19);
						y1 = momentcoefficients(i, slabtype, 18);
						y2 = momentcoefficients(i, slabtype, 20);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.6 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.6 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else if (lenRatio >= 0.5) {
						x1 = momentcoefficients(i, slabtype, 19);
						x2 = momentcoefficients(i, slabtype, 21);
						y1 = momentcoefficients(i, slabtype, 20);
						y2 = momentcoefficients(i, slabtype, 22);
						double momentShort = ((x1 - x2) / gap) * lenRatio + (x1 + (0.55 * (x2 - x1) / gap));
						double momentLong = ((y1 - y2) / gap) * lenRatio + (y1 + (0.55 * (y2 - y1) / gap));
						dbs[i] = (double)Math.round(momentShort*1000)/1000;
						dbslong[i] = (double)Math.round(momentLong*1000)/1000;
						
					} else {
						System.out.println("길이잘못입력함.");
					}
				}
				break;

			default:
				System.out.println("SlabCase 입력오류");
				break;
			}
		} else {
			System.out.println("slabcase error");
		}

	}
}