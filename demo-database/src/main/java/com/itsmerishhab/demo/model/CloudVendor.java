package com.itsmerishhab.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // todo :  It denotes the class JPA
@Table(name="cloud_vendor_info") // todo : I want table to be given this name
public class CloudVendor {
    @Id // todo : It makes table know that it is the id
    private String vendorId; // vendor_id
    private String vendorName;
    private String vendorPhoneNumber;
    private String vendorAddress;

    public CloudVendor(String vendorId, String vendorName, String vendorPhoneNumber, String vendorAddress) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.vendorAddress = vendorAddress;
    }

    @Override
    public String toString() {
        return "cloudVendor{" +
                "vendorId='" + vendorId + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", vendorPhoneNumber='" + vendorPhoneNumber + '\'' +
                ", vendorAddress='" + vendorAddress + '\'' +
                '}';
    }

    public CloudVendor() {
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
}
