package rikkeisoft.nguyenducdung.com.contactrecycleview.model;

public class ItemContact {
    private String phoneContact;
    private String nameContact;

    public ItemContact(String phoneContact, String nameContact) {
        this.phoneContact = phoneContact;
        this.nameContact = nameContact;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }
}
