package org.structome.core;

public class RelationArtefactPair<E extends Relation, N extends Artefact> {
	private E edge;
	private N destination;
	
	public RelationArtefactPair(final E _edge, final N _dest) {
		edge = _edge;
		destination = _dest;
	}
	
	public final E getRelation() {
		return edge;
	}
	
	public final N getArtefact() {
		return destination;
	}
}
