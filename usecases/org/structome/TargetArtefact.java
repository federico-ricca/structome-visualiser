package org.structome;

import org.structome.core.Artefact;

public final class TargetArtefact implements Artefact {
	private String id;
	private String numberAsString;
	
	public TargetArtefact(String _id, String _number) {
		id = _id;
		numberAsString = _number;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getNumberAsString() {
		return numberAsString;
	}
}
