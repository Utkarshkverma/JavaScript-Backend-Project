package com.thinkconstructive.demorestapi.controller;

import com.thinkconstructive.demorestapi.Models.cloudVendor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cloudVendor")
public class CloudApiService
{
    cloudVendor cloudVendor;

    @GetMapping("{vendorId}")
    public cloudVendor getVendorDetails(String vendorId) {
        return new cloudVendor("c1","Rishab",
                "9905893940","Delhi");
    }

    @PostMapping()
    public String createCloudVendorDetails(@RequestBody cloudVendor cloudvendor)
    {
       this.cloudVendor = cloudvendor;
       return "Cloud Vendor Created Successfully\n"+cloudvendor.toString();
    }

    @PutMapping()
    public String updateCloudVendor(@RequestBody cloudVendor cloudvendor)
    {
      var cv = new cloudVendor("c1","Utkarsh","9470225274","Bettiah");
      cv.setVendorId(cloudvendor.getVendorId());
      cv.setVendorAddress(cloudvendor.getVendorAddress());
      cv.setVendorName(cloudvendor.getVendorName());
      cv.setVendorAddress(cloudvendor.getVendorAddress());

      return "Vendor Updated Successfully \n"+cv.toString();
    }

    @DeleteMapping("{vendorId}")
    public String deleteMapping(String vendorId)
    {
        return "Vendor deleted Successfully";
    }
}
