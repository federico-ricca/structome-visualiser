package org.structome.core;

public class RelationImpl<N extends Artefact> implements Relation<N> {

	private N source;
	private N destination;

	@Override
	public N getSource() {
		return source;
	}

	@Override
	public N getDestination() {
		return destination;
	}

	public void setSource(N _source) {
		source = _source;
	}

	public void setDestination(N _dest) {
		destination = _dest;
	}
}
