package cc.lijingbo.springboot.domain;

public class BodyAndHeaderParams {

    private String bodyName;

    private String bodySex;

    private String headertoken;

    private String headerdevice;

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getBodySex() {
        return bodySex;
    }

    public void setBodySex(String bodySex) {
        this.bodySex = bodySex;
    }

    public String getHeadertoken() {
        return headertoken;
    }

    public void setHeadertoken(String headertoken) {
        this.headertoken = headertoken;
    }

    public String getHeaderdevice() {
        return headerdevice;
    }

    public void setHeaderdevice(String headerdevice) {
        this.headerdevice = headerdevice;
    }
}
