package com.runecast.demo.service;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.runecast.demo.dao.ECInstanceDAO;
import com.runecast.demo.dao.SecurityGrpDAO;
import com.runecast.demo.dao.VolumeDAO;
import com.runecast.demo.domainobject.ECInstanceDO;
import com.runecast.demo.domainobject.SecurityGrpDO;
import com.runecast.demo.domainobject.VolumeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ECDemoService {

    @Autowired
    private AmazonEC2 ec2Client;

    @Autowired
    private ECInstanceDAO ecInstanceDAO;

    @Autowired
    private SecurityGrpDAO securityGrpDAO;

    @Autowired
    private VolumeDAO volumeDAO;

    public List<ECInstanceDO> findAllEcInstRelations() {
        return ecInstanceDAO.findAll();
    }

    @PostConstruct
    public void authenticate() {

        //Instance, Security Group, Volume
        identifyInstanceSecGrpRel();
        identifyInstanceVolRel();
    }

    private void identifyInstanceSecGrpRel() {
        DescribeInstancesResult instancesResult = ec2Client.describeInstances();
        instancesResult.getReservations().stream().forEach(reservation -> {
            List<Instance> instanceList = reservation.getInstances();
            instanceList.stream().forEach(instance -> {
                instance.getSecurityGroups();
                String instaceId = instance.getInstanceId();
                ECInstanceDO ecInstanceDO = new ECInstanceDO();
                ecInstanceDO.setEc2Id(instaceId);
                ecInstanceDO = ecInstanceDAO.save(ecInstanceDO);
                saveSecurityGrpDO(instance, ecInstanceDO);
            });
        });
    }

    private void identifyInstanceVolRel() {
        DescribeVolumesResult describeVolumesResult = ec2Client.describeVolumes();
        List<Volume> volumes = describeVolumesResult.getVolumes();
        volumes.stream().forEach(volume -> {
            if (!volume.getAttachments().isEmpty()) {
                Set<ECInstanceDO> ecInstanceDOSet = new HashSet<>();
                volume.getAttachments().stream().forEach(volumeAttachment -> {
                    String instanceId = volumeAttachment.getInstanceId();
                    ECInstanceDO ecInstanceDO = ecInstanceDAO.findByEc2Id(instanceId);
                    if (volumeAttachment.getInstanceId().equals(instanceId)) {
                        VolumeDO volumeDO = new VolumeDO();
                        volumeDO.setVolumeId(volume.getVolumeId());
                        volumeDO.setEcInstanceDO(ecInstanceDO);
                        volumeDAO.save(volumeDO);
                    }
                });
            }
        });
    }

    private void saveSecurityGrpDO(Instance instance, ECInstanceDO ecInstanceDO) {
        instance.getSecurityGroups().stream().forEach(securityGrp -> {
            SecurityGrpDO securityGrpDO = new SecurityGrpDO();
            securityGrpDO.setGroupId(securityGrp.getGroupId());
            securityGrpDO.setGroupName(securityGrp.getGroupName());
            securityGrpDO.setEcInstanceDO(ecInstanceDO);
            securityGrpDAO.save(securityGrpDO);
        });
    }
}
