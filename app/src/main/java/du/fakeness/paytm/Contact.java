package du.fakeness.paytm;

/**
 * Created by admin on 20-03-2017.
 */

public class Contact {
    //private variables
    int _id;
    String _name;
    String _phone_number;
    String _merchantName;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    // constructor
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contact (String merchant_name){
        this._merchantName = merchant_name;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

    // getting phone number
    public String getMerchantName(){
        return this._merchantName;
    }

    // setting phone number
    public void setMerchantName(String merchantName){
        this._merchantName = merchantName;
    }
}
