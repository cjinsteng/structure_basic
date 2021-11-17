package structj.frame.rcframe.slab;

public class CalculateSlab {
	
	String strName;
	String strCon;
	String strRebar1;
	String strRebar2;
	
	public CalculateSlab(String[] strArr) {
		this.strName = strArr[0];
		this.strCon = strArr[1];
		this.strRebar1 = strArr[2];
		this.strRebar2 = strArr[3];
	}

	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrCon() {
		return strCon;
	}
	public void setStrCon(String strCon) {
		this.strCon = strCon;
	}
	public String getStrRebar1() {
		return strRebar1;
	}
	public void setStrRebar1(String strRebar1) {
		this.strRebar1 = strRebar1;
	}
	public String getStrRebar2() {
		return strRebar2;
	}
	public void setStrRebar2(String strRebar2) {
		this.strRebar2 = strRebar2;
	}

	
	



}
