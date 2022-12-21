package and;


public class ExamVO {
    private int iVal;
    private String sVal;
    private double dVal;

    public ExamVO(int iVal, String sVal, double dVal) {

			this.iVal = iVal;
			this.sVal = sVal;
			this.dVal = dVal;
		}

		public int getiVal() {
        return iVal;
    }

    public void setiVal(int iVal) {
        this.iVal = iVal;
    }

    public String getsVal() {
        return sVal;
    }

    public void setsVal(String sVal) {
        this.sVal = sVal;
    }

    public double getdVal() {
        return dVal;
    }

    public void setdVal(double dVal) {
        this.dVal = dVal;
    }
}