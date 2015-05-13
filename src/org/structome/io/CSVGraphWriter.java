package org.structome.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

import org.structome.core.Artefact;
import org.structome.core.ArtefactRepresentationFactory;
import org.structome.core.Graph;
import org.structome.core.GraphWriter;

public class CSVGraphWriter<T extends Artefact> implements GraphWriter<T> {

	@Override
	public void write(Graph _graph, File _file, ArtefactRepresentationFactory<T> _representationFactory)
			throws IOException {
		PrintStream _printStream = new PrintStream(_file);

		Collection<Artefact> _artefacts = _graph.artefacts();

		for (Artefact _anArtefact : _artefacts) {
			final String _source = _representationFactory.createRepresentationFor((T) _anArtefact);

			if (_graph.hasRelations(_anArtefact.getId())) {
				for (Artefact _relatedArtefact : _graph.getRelationsFor(_anArtefact.getId())) {
					String _target = _representationFactory.createRepresentationFor((T) _relatedArtefact);

					_printStream.println(_source + "," + _target);
				}
			}
		}

		_printStream.close();
	}

}
