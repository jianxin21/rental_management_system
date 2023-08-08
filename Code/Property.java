/**
    class: Property

    name: tamjianxin

*/
public class Property {
    
    private int id;
    private String address;
    private String size;
    private String noRoom;
    private String noBathroom;
    private String facilities;
    private int rentalprice;
    private String propertyType;
    private String status;
    private String rentalrate;
    
    public Property(int id,String address,String size,String noRoom,String noBathroom,String facilities,
                    int rentalprice,String propertyType,String status,String rentalrate) {
        this.id=id;
        this.address=address;
        this.size=size;
        this.noRoom=noRoom;
        this.noBathroom=noBathroom;
        this.facilities=facilities;
        this.rentalprice=rentalprice;
        this.propertyType=propertyType;
        this.status=status;
        this.rentalrate=rentalrate;
    }

    public int getRentalPrice(){
        return rentalprice;
    }
    
    public void setRentalPrice(int rentalprice){
        this.rentalprice=rentalprice;
    }
    
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(String noRoom) {
        this.noRoom = noRoom;
    }

    public String getNoBathroom() {
        return noBathroom;
    }

    public void setNoBathroom(String noBathroom) {
        this.noBathroom = noBathroom;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getRentalrate() {
        return rentalrate;
    }

    public void setRentalrate(String rentalrate) {
        this.rentalrate = rentalrate;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String toCSVString(){
        return(getId() + "," + getAddress() + "," + getSize() + "," + getNoRoom() + "," + getNoBathroom()
                + "," + getFacilities() + "," + getRentalPrice() + "," + getPropertyType() + "," + getStatus() 
                + "," + getRentalrate());
    }
    

}
