package org.structome.core;

import java.util.Collection;

public interface GraphBuilder<N extends Artefact, E extends Relation, S> {

	public Graph<N, E> buildFrom(Collection<S> _sources, ArtefactFactory<N, S> _factory);

}
