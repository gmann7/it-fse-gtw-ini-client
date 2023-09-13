package it.finanze.sanita.fse2.ms.iniclient.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import it.finanze.sanita.fse2.ms.iniclient.client.IConfigClient;
import it.finanze.sanita.fse2.ms.iniclient.enums.EdsStrategyEnum;
import it.finanze.sanita.fse2.ms.iniclient.service.IConfigSRV;
import it.finanze.sanita.fse2.ms.iniclient.utility.StringUtility;

@Service
public class ConfigSRV implements IConfigSRV {

	private static final Long DELTA_MS = 300000L;

	@Autowired
	private IConfigClient configClient;

	private String edsStrategy;

	private long lastUpdate;

	@Async
	@EventListener(ApplicationStartedEvent.class)
	void initialize() {
		refreshEdsStrategy();
	}

	private void refreshEdsStrategy() {
		synchronized (this) {
			edsStrategy = configClient.getEDSStrategy();
			lastUpdate = new Date().getTime();
		}
	}

	@Override
	public String getEdsStrategy() {
		long passedTime = new Date().getTime() - lastUpdate;
		if (passedTime >= DELTA_MS) {
			synchronized (this) {
				refreshEdsStrategy();
			}
		}
		return edsStrategy;
	}

	@Override
	public boolean isNoFhirEds() {
		// Trigger refresh if necessary
		String out = getEdsStrategy();
		// Evaluate
		return StringUtils.isNotBlank(out) && EdsStrategyEnum.NO_FHIR_EDS.name().equalsIgnoreCase(out);
	}

	//Se la strategy è null si setta come default no_eds (quindi non verranno emesse loggate)
	@Override
	public boolean isNoEds() {
		String out = getEdsStrategy();
		return StringUtility.isNullOrEmpty(out) || EdsStrategyEnum.NO_EDS.name().equalsIgnoreCase(out);
	}
}
