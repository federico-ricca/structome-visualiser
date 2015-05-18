package org.structome.core;

import java.util.Collection;

public interface ArtefactProcessor<T extends Artefact, S> {

	Collection<S> process(Collection<T> _artefacts);

}
