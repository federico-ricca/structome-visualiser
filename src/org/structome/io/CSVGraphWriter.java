/*************************************************************************** 
   Copyright 2015 Federico Ricca

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ***************************************************************************/
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

public class CSVGraphWriter<N extends Artefact, E extends Relation<N>> implements GraphWriter<N, E> {

	@Override
	public void write(Graph<N, E> _graph, File _file,
			ArtefactStringRepresentationFactory<N> _representationFactory) throws IOException {
		PrintStream _printStream = new PrintStream(_file);

		Collection<N> _artefacts = _graph.artefacts();

		for (Artefact _anArtefact : _artefacts) {
			final String _source = _representationFactory.createStringRepresentationFor((N) _anArtefact);

			if (_graph.hasRelations(_anArtefact.getId())) {
				for (Relation<N> _relation : _graph.getRelationsFor(_anArtefact.getId())) {
					String _target = _representationFactory.createStringRepresentationFor(_relation
							.getDestination());

					_printStream.println(_source + "," + _target);
				}
			}
		}

		_printStream.close();
	}

}
