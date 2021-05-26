package com.runecast.demo.dao;

import com.runecast.demo.domainobject.VolumeDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumeDAO extends JpaRepository <VolumeDO, Long> {
}
