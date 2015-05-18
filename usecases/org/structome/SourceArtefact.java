package org.structome;

import org.structome.core.Artefact;

public final class SourceArtefact implements Artefact {
	private String id;
	private int aNumber;
	
	public SourceArtefact(String _id, int i) {
		id = _id;
		aNumber = i;
	}

	@Override
	public String getId() {
		return id;
	}

	public int getANumber() {
		return aNumber;
	}
}
