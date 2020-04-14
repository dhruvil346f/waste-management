package application.example.wastemanagement;

import androidx.annotation.NonNull;

class UserProfile {
    public String Username,Password,Email,Address,Phone_no;

    public UserProfile(String Username, String Password, String Email, String Address, String Phone_no) {

        this.Username=Username;
        this.Password=Password;
        this.Email=Email;
        this.Address=Address;
        this.Phone_no=Phone_no;
    }
}
