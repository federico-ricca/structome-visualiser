package org.structome.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

import org.structome.core.Artefact;
import org.structome.core.ArtefactStringRepresentationFactory;
import org.structome.core.Graph;
import org.structome.core.GraphWriter;
import org.structome.core.Relation;

public class CSVGraphWriter<N extends Artefact, E extends Relation> implements GraphWriter<N, E> {

	@Override
	public void write(Graph<N, E> _graph, File _file,
			ArtefactStringRepresentationFactory<N> _representationFactory) throws IOException {
		PrintStream _printStream = new PrintStream(_file);

		Collection<N> _artefacts = _graph.artefacts();

		for (Artefact _anArtefact : _artefacts) {
			final String _source = _representationFactory.createStringRepresentationFor((N) _anArtefact);

			if (_graph.hasRelations(_anArtefact.getId())) {
				for (Artefact _relatedArtefact : _graph.getRelationsFor(_anArtefact.getId())) {
					String _target = _representationFactory
							.createStringRepresentationFor((N) _relatedArtefact);

					_printStream.println(_source + "," + _target);
				}
			}
		}

		_printStream.close();
	}

}
