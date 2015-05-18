package org.structome;

public class SourceArtefactRepresentation {

	private String number;
	private String id;

	public SourceArtefactRepresentation(SourceArtefact _artefact) {
		number = String.valueOf(_artefact.getANumber());
		id = _artefact.getId();
	}

	public String getNumber() {
		return number;
	}

	public String getId() {
		return id;
	}

}
