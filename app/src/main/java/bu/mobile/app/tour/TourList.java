package bu.mobile.app.tour;

public class TourList {
    public boolean isSelected;
    private String image;
    private String image2;
    private String name;
    private String address;
    private String txt;

    public TourList(){

    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() { return image2; }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setSelected(boolean isChecked) {
    }
}

