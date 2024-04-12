package com.itsmerishhab.demo.controller;

import com.itsmerishhab.demo.model.CloudVendor;
import com.itsmerishhab.demo.service.CloudVendorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cloudVendor")
public class CloudVendorController {

    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }


    // ! Specific cloud vendors
    @GetMapping("{vendorId}")
    public CloudVendor getCloudVendor(@PathVariable("vendorId") String vendorId)
    {
        return cloudVendorService.getCloudVendor(vendorId);
    }

    // Get all cloud vendor Id
    @GetMapping()
    public List<CloudVendor> getAllCloudVendor()
    {
        return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping
    public String createCloudVendor(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.createCloudVendor(cloudVendor);
        return "CloudVendor created Successfully \n";
    }

    @PutMapping
    public String updateCloudVendor(@RequestBody CloudVendor cloudvendor)
    {

       cloudVendorService.updateCloudVendor(cloudvendor);
        return "Vendor Updated Successfully \n";
    }

    @DeleteMapping("{vendorId}")
    public String deleteMapping(@PathVariable("vendorId") String vendorId)
    {
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Vendor deleted Successfully";
    }


}

