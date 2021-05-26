package com.runecast.demo.dao;

import com.runecast.demo.domainobject.SecurityGrpDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityGrpDAO extends JpaRepository <SecurityGrpDO , Long> {
}
