package com.leroy.ronan.api.labels;

import java.util.Optional;

public class About {

	private String contact;
	private String version;
			
	public About() {
		this.contact = "ronan@leroy.com";
		this.version = Optional.ofNullable(About.class.getPackage().getImplementationVersion()).orElse("DEVELOPPEMENT");
	}

	public String getContact() {
		return contact;
	}

	public String getVersion() {
		return version;
	}

}
