/**
 * CREATE Script for init of DB
 */

/*CREATE TABLE `EC2_Details` (
  `id` number (10) unsigned NOT NULL AUTO_INCREMENT,
  `ec2_id` varchar (30),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;*/

/*CREATE TABLE `Security_grp_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ec2_id` varchar(30) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ec2_id` (`ec2_id`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`ec2_id`) REFERENCES `ECInstanceDO` (`ec2_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;*/