package it.finanze.sanita.fse2.ms.iniclient.repository.mongo;

import it.finanze.sanita.fse2.ms.iniclient.repository.entity.AuditIniETY;

public interface IAuditIniRepo {

	AuditIniETY findByWii(String wii);
	void insert(AuditIniETY ety);
	void updateResponseByWii(String wii, String response);
}
