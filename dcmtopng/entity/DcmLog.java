package entity;

public class DcmLog {

    private int dcmId;
    private String dcmName;
    private String patientName;
    private int dcmLR;
    private String dcmCC;
    private String dcmAge;
    private int dcmSex;
    private String dcmDate;
    private String dcmTime;
//    private String dcmPath;
    private double dcmRate;
    private int dcmBm;

    public int getDcmId() {
        return dcmId;
    }

    public void setDcmId(int dcmId) {
        this.dcmId = dcmId;
    }

    public String getDcmName() {
        return dcmName;
    }

    public void setDcmName(String dcmName) {
        this.dcmName = dcmName;
    }

    public int getDcmLR() {
        return dcmLR;
    }

    public void setDcmLR(int dcmLR) {
        this.dcmLR = dcmLR;
    }

    public String getDcmCC() {
        return dcmCC;
    }

    public void setDcmCC(String dcmCC) {
        this.dcmCC = dcmCC;
    }

    public String getDcmAge() {
        return dcmAge;
    }

    public void setDcmAge(String dcmAge) {
        this.dcmAge = dcmAge;
    }

    public int getDcmSex() {
        return dcmSex;
    }

    public void setDcmSex(int dcmSex) {
        this.dcmSex = dcmSex;
    }

    public String getDcmDate() {
        return dcmDate;
    }

    public void setDcmDate(String dcmDate) {
        this.dcmDate = dcmDate;
    }



    public double getDcmRate() {
        return dcmRate;
    }

    public void setDcmRate(double dcmRate) {
        this.dcmRate = dcmRate;
    }

    public int getDcmBm() {
        return dcmBm;
    }

    public void setDcmBm(int dcmBm) {
        this.dcmBm = dcmBm;
    }

    public String getDcmTime() {
        return dcmTime;
    }

    public void setDcmTime(String dcmTime) {
        this.dcmTime = dcmTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"dcmId\":")
                .append(dcmId);
        sb.append(",\"dcmName\":\"")
                .append(dcmName).append('\"');
        sb.append(",\"patientName\":\"")
                .append(patientName).append('\"');
        sb.append(",\"dcmLR\":")
                .append(dcmLR);
        sb.append(",\"dcmCC\":\"")
                .append(dcmCC).append('\"');
        sb.append(",\"dcmAge\":\"")
                .append(dcmAge).append('\"');
        sb.append(",\"dcmSex\":")
                .append(dcmSex);
        sb.append(",\"dcmDate\":\"")
                .append(dcmDate).append('\"');
        sb.append(",\"dcmTime\":\"")
                .append(dcmTime).append('\"');
        sb.append(",\"dcmRate\":")
                .append(dcmRate);
        sb.append(",\"dcmBm\":")
                .append(dcmBm);
        sb.append('}');
        return sb.toString();
    }
}

