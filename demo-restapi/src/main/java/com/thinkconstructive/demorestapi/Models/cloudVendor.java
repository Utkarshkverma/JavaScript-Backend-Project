package com.thinkconstructive.demorestapi.Models;

public class cloudVendor {
    private String vendorId;
    private String vendorName;
    private String vendorPhoneNumber;
    private String vendorAddress;

    public cloudVendor(String vendorId, String vendorName, String vendorPhoneNumber, String vendorAddress) {
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

    public cloudVendor() {
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

