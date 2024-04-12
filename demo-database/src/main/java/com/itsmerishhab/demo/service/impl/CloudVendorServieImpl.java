package com.itsmerishhab.demo.service.impl;

import com.itsmerishhab.demo.model.CloudVendor;
import com.itsmerishhab.demo.repository.CloudVendorRepository;
import com.itsmerishhab.demo.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServieImpl implements CloudVendorService {
    CloudVendorRepository cloudVendorRepository;
    public CloudVendorServieImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }
    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);

        return "Successfully created cloud vendor";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Successfully updated";
    }

    @Override
    public String deleteCloudVendor(String ID) {
        cloudVendorRepository.deleteById(ID);
        return "user deleted successfully";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }
}
