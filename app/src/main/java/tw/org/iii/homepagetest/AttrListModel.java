package tw.org.iii.homepagetest;

/**
 * Created by wei-chengni on 2018/4/12.
 */

public class AttrListModel {
    private String name;
    private String address;
    private String toldesc;
    private String opentime;
    private String tel;
    private String description;
    private String imgs;

    public AttrListModel(){
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getToldesc(){
        return toldesc;
    }
    public void setToldesc(String toldesc){
        this.toldesc = toldesc;
    }

    public String getTel(){
        return tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    public String getImgs(){
        return imgs;
    }
    public void setImgs(String imgs){
        this.imgs = imgs;
    }

    public String getOpentime(){
        return opentime;
    }
    public void setOpentime(String opentime){
        this.opentime = opentime;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
